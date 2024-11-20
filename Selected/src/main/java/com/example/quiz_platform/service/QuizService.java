package com.example.quiz_platform.service;

import com.example.quiz_platform.model.QuizResult;
import org.springframework.stereotype.Service;

import com.example.quiz_platform.repository.QuizRepository;
import com.example.quiz_platform.model.User;
import com.example.quiz_platform.model.User.Role;
import com.example.quiz_platform.model.Quiz;
import com.example.quiz_platform.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private QuizRepository quizRepository;
    private final List<QuizResult> quizResults = new ArrayList<>(); // Store quiz results in memory

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> viewQuizzes() {
        return quizRepository.listOfQuizzes();
    }

    public Quiz findQuiz(String quizId) {
        return quizRepository.findQuizById(quizId);
    }

    public void saveQuizResult(String quizId, User student, String score) {
        Quiz quiz = quizRepository.findQuizById(quizId);
        if (quiz == null) {
            throw new IllegalArgumentException("Quiz does not exist with ID: " + quizId);
        }

        QuizResult result = new QuizResult();
        result.setId(UUID.randomUUID().toString());
        result.setStudentId(student.getId());
        result.setStudentName(student.getUsername());
        result.setQuizId(quizId);
        result.setQuizTitle(quiz.getTitle());
        result.setScore(score);

        quizResults.add(result);
    }

    public List<QuizResult> getAllQuizResults() {
        return new ArrayList<>(quizResults); // Return all stored results
    }

    public List<QuizResult> getResultsByTeacher(String teacherId) {
        return quizResults.stream()
                .filter(result -> {
                    Quiz quiz = quizRepository.findQuizById(result.getQuizId());
                    return quiz != null && quiz.getTeacherId().equals(teacherId);
                })
                .collect(Collectors.toList());
    }

    public List<QuizResult> getResultsByQuiz(String quizId) {
        return quizResults.stream()
                .filter(result -> result.getQuizId().equals(quizId))
                .collect(Collectors.toList());
    }


    // will it be necesarry to include any checks for multiplicity amongst quizzes?
    public void createQuiz(User user, String teacherId, String title, List<Question> questions) {
        if (user.getRole() != User.Role.TEACHER) {
            throw new IllegalArgumentException("Only teachers can create quizzes.");
        }
    
        String quizId = UUID.randomUUID().toString();
    
        // check multiple-choice questions here to be safe
        for (Question question : questions) {
            if (question.getQuestionType() == Question.QuestionType.MULTIPLE_CHOICE) {
                if (question.getOptions() == null || question.getOptions().size() != 4 ||
                    !question.getOptions().containsKey("A") || 
                    !question.getOptions().containsKey("B") ||
                    !question.getOptions().containsKey("C") || 
                    !question.getOptions().containsKey("D")) {
                    throw new IllegalArgumentException("Multiple-choice questions must have exactly 4 options labeled A, B, C, and D.");
                }
            }
        }
    
        Quiz newQuiz = new Quiz(quizId, teacherId, title, questions);
        quizRepository.createQuiz(user, newQuiz);
    }

    public void addQuestion(User user, String quizId, Question question) {

        if (user.getRole() != Role.TEACHER) {
            throw new IllegalArgumentException("User Cannot Add Question To Quiz" + quizId);
        }

        Quiz quiz = quizRepository.findQuizById(quizId);

        if (quiz == null) {
            throw new IllegalArgumentException("Quiz Does Not Exist: " + quizId);
        }

        if (!quiz.getTeacherId().equals(user.getId())) {
            throw new IllegalArgumentException("Permission Denied: Adding Question");
        }

        quizRepository.addQuestion(quizId, question);
    }

    public void deleteQuestion(User user, String quizId, int index) {

        if (user.getRole() != Role.TEACHER) {
            throw new IllegalArgumentException("User Cannot Delete Question From Quiz" + quizId);
        }

        Quiz quiz = quizRepository.findQuizById(quizId);

        if (quiz == null) {
            throw new IllegalArgumentException("Quiz Does Not Exist: " + quizId);
        }

        if (!quiz.getTeacherId().equals(user.getId())) {
            throw new IllegalArgumentException("Permission Denied: Deleting Question");
        }

        quizRepository.deleteQuestion(quizId, index);
    }

    public void deleteQuiz(User user, String quizId) {

        if (user.getRole() != Role.TEACHER) {
            throw new IllegalArgumentException("User Cannot Delete Quiz" + quizId);
        }

        Quiz quiz = quizRepository.findQuizById(quizId);

        if (quiz == null) {
            throw new IllegalArgumentException("Quiz Does Not Exist: " + quizId);
        }

        if (!quiz.getTeacherId().equals(user.getId())) {
            throw new IllegalArgumentException("Permission Denied: Deleting Quiz");
        }

        quizRepository.deleteQuiz(user, quizId);
    }

    public boolean updateQuiz(User user, String quizId, String newTitle, List<Question> newQuestions) {
        
        if (user.getRole() != Role.TEACHER) {
            throw new IllegalArgumentException("User Cannot Update Quiz" + quizId);
        }

        Quiz quizToUpdate = findQuiz(quizId);

        if (!quizToUpdate.getTeacherId().equals(user.getId())) {
            throw new IllegalArgumentException("Permission Denied: Deleting Quiz");
        }

        if(quizToUpdate != null) {
            quizToUpdate.setTitle(newTitle);
            quizToUpdate.setQuestions(newQuestions);

            return quizRepository.updateQuiz(quizToUpdate);
        }

        return false;
    }

    public String takeQuiz(User student, String quizId, List<String> answers) {
        if (student.getRole() != User.Role.STUDENT) {
            throw new IllegalArgumentException("Only students can take quizzes.");
        }

        Quiz quizToTake = quizRepository.findQuizById(quizId);
        if (quizToTake == null) {
            throw new IllegalArgumentException("Quiz does not exist with ID: " + quizId);
        }

        String score = gradeQuiz(quizToTake, answers);

        return score;
    }

    public String gradeQuiz(Quiz quiz, List<String> answers) {
        int score = 0;
        List<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); ++i) {
            Question question = questions.get(i);
            String studentAnswer = answers.get(i);
    
            // USING QUESTIONS ISANSWERVALID METHOD
            if (question.isAnswerValid(studentAnswer)) {
                score++;
            }
        }
    
        return "Quiz Score: " + score + "/" + questions.size();
    }

    

}
