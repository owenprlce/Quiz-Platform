package com.quizplatform.listener;

import com.quizplatform.event.QuizStartEvent;
import com.quizplatform.event.QuestionAnsweredEvent;
import com.quizplatform.event.QuizEndEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuizEventListener {

    @EventListener
    public void handleQuizStartEvent(QuizStartEvent event) {
        System.out.println("Quiz started with ID: " + event.getQuizId() +
                ", User ID: " + event.getUserId() +
                ", Start Time: " + event.getStartTime());
        // Add custom logic here, such as logging or notifying a service.
    }

    @EventListener
    public void handleQuestionAnsweredEvent(QuestionAnsweredEvent event) {
        System.out.println("Question answered in Quiz ID: " + event.getQuizId() +
                ", Question ID: " + event.getQuestionId() +
                ", Correct: " + event.isCorrect());
        // Process the answered question, e.g., update score or notify analytics.
    }

    @EventListener
    public void handleQuizEndEvent(QuizEndEvent event) {
        System.out.println("Quiz ended with ID: " + event.getQuizId() +
                ", Student ID: " + event.getUserId() +
                ", End Time: " + event.getEndTime());
        // Perform cleanup or save quiz results.
    }
}