package com.quizplatform.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuizStartEvent {
    private Long quizId;
    private Long sessionId;
    private final Long userId;
    private LocalDateTime startTime;

    public QuizStartEvent(Long quizId, Long sessionId, Long userId, LocalDateTime startTime) {
        this.quizId = quizId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.startTime = startTime;
    }
}
