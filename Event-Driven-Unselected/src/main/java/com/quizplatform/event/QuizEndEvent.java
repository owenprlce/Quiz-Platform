package com.quizplatform.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuizEndEvent {
    private Long quizId;
    private Long userId;
    private Long sessionId;
    private String message;
    private LocalDateTime endTime;

    public QuizEndEvent(Long quizId, Long sessionId, Long userId, String message, LocalDateTime endTime) {
        this.quizId = quizId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.message = message;
        this.endTime = endTime;
    }


}
