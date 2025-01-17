package com.quizplatform.repository;

import com.quizplatform.model.Quiz;
import com.quizplatform.model.QuizSession;
import com.quizplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizSessionRepository extends JpaRepository<QuizSession, String> {
    List<QuizSession> findByUser(User user);
    List<QuizSession> findByQuiz(Quiz quiz);
    Optional<QuizSession> findByQuizAndUser(Quiz quiz, User user);
}