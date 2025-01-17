package com.quizplatform.controller;

import com.quizplatform.event.QuestionAnsweredEvent;
import com.quizplatform.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final QuizService quizService;

    @MessageMapping("/quiz/{quizId}/answer")
    @SendTo("/topic/quiz/{quizId}")
    public QuestionAnsweredEvent handleAnswer(
            @DestinationVariable String quizId,
            @Payload QuestionAnsweredEvent event) {
        quizService.submitAnswer(
                event.getSessionId(),
                event.getQuestionId(),
                event.getAnswer()
        );
        return event;
    }
}