package com.quizplatform.controller;

import com.quizplatform.model.Question;
import com.quizplatform.model.Quiz;
import com.quizplatform.model.QuizSession;
import com.quizplatform.repository.QuizRepository;
import com.quizplatform.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    @Autowired
    private final QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @GetMapping("/getQuizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{quizId}/getQuestions")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Quiz quiz = quizOptional.get();
        List<Question> questions = quiz.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/{quizId}/start")
    public ResponseEntity<QuizSession> startQuiz(
            @PathVariable Long quizId,
            @RequestParam String userId) {
        return ResponseEntity.ok(quizService.startQuiz(quizId, Long.valueOf(userId)));
    }

    @PostMapping("/session/{sessionId}/answer")
    public ResponseEntity<Void> submitAnswer(
            @PathVariable Long sessionId,
            @RequestParam Long questionId,
            @RequestParam String answer) {
        quizService.submitAnswer(sessionId, questionId, answer);
        return ResponseEntity.ok().build();
    }
}
