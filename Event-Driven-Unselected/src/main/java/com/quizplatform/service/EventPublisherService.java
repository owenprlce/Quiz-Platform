package com.quizplatform.service;

import com.quizplatform.event.QuestionAnsweredEvent;
import com.quizplatform.event.QuizStartEvent;
import com.quizplatform.event.QuizEndEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

    private final ApplicationEventPublisher eventPublisher;

    // Constructor-based dependency injection
    public EventPublisherService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Publish a QuestionAnsweredEvent
     * @param event QuestionAnsweredEvent instance
     */
    public void publishQuestionAnsweredEvent(QuestionAnsweredEvent event) {
        eventPublisher.publishEvent(event);
        System.out.println("Published QuestionAnsweredEvent: " + event);
    }

    /**
     * Publish a QuizStartEvent
     * @param event QuizStartEvent instance
     */
    public void publishQuizStartEvent(QuizStartEvent event) {
        eventPublisher.publishEvent(event);
        System.out.println("Published QuizStartEvent: " + event);
    }

    /**
     * Publish a QuizEndEvent
     * @param event QuizEndEvent instance
     */
    public void publishQuizEndEvent(QuizEndEvent event) {
        eventPublisher.publishEvent(event);
        System.out.println("Published QuizEndEvent: " + event);
    }
}
