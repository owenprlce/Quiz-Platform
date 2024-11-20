package com.quizplatform.service;

import com.quizplatform.event.QuestionAnsweredEvent;
import com.quizplatform.event.QuizEvent;
import com.quizplatform.event.QuizStartEvent;
import com.quizplatform.model.Question;
import com.quizplatform.model.Quiz;
import com.quizplatform.model.QuizSession;
import com.quizplatform.repository.QuizRepository;
import com.quizplatform.repository.QuizSessionRepository;
import com.quizplatform.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    @Autowired
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizSessionRepository sessionRepository;
    private final WebSocketService webSocketService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Transactional
    public QuizSession startQuiz(Long quizId, Long userId) {
        // Fetch the quiz details
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Create a new quiz session
        QuizSession session = new QuizSession();
        session.setQuiz(quiz);
        session.setUser(userRepository.getReferenceById(userId)); // Assuming `userId` is a Long
        session.setStartTime(new Date());
        session = sessionRepository.save(session); // Save the session in the database

        // Publish a QuizStartEvent (optional)
        QuizStartEvent startEvent = new QuizStartEvent(
                quiz.getId(),
                session.getId(),
                userId,
                LocalDateTime.now()
        );
        eventPublisher.publishEvent(startEvent); // Publish event for listeners

        return session; // Return the session for further use
    }

    @Transactional
    public void submitAnswer(Long sessionId, Long questionId, String answer) {
        // Retrieve the session or throw an exception if not found
        QuizSession session = sessionRepository.findById(String.valueOf(sessionId))
                .orElseThrow(() -> new RuntimeException("Session not found with ID: " + sessionId));

        // Add the answer to the session's answer map
        session.getAnswers().put(String.valueOf(questionId), answer);
        sessionRepository.save(session);

        // Find the specific question within the quiz
        Question question = session.getQuiz().getQuestions().stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Question not found with ID: " + questionId));

        // Determine if the answer is correct
        boolean isCorrect = answer.equals(question.getCorrectAnswer());

        // Create and publish a QuestionAnsweredEvent
        QuestionAnsweredEvent event = new QuestionAnsweredEvent(
                session.getQuiz().getId(),
                session.getId(),
                questionId,
                session.getUser().getId(),
                answer,
                isCorrect
        );

        // Send the event through WebSocket
        webSocketService.sendQuizEvent(event);

        // Optionally, log the event for debugging or auditing
        System.out.println("QuestionAnsweredEvent published: " + event);
    }
}