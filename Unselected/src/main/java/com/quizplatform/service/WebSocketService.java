package com.quizplatform.service;

import com.quizplatform.event.QuestionAnsweredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendQuizEvent(QuestionAnsweredEvent event) {
        messagingTemplate.convertAndSend(
                "/topic/quiz/" + event.getQuizId(),
                event
        );
    }
}
