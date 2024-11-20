package com.quizplatform.event;

import lombok.Data;

@Data
public class QuizEvent {
    private Long quizId;
    private Long sessionId;

    public QuizEvent(Long quizId, Long sessionId) {
        this.quizId = quizId;
        this.sessionId = sessionId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}

