package com.quizplatform.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionAnsweredEvent {
    private Long quizId;
    private Long sessionId;
    private Long questionId;
    private Long userId;
    private String answer;
    private boolean isCorrect;

    public QuestionAnsweredEvent(Long quizId, Long sessionId, Long questionId, Long userId, String answer, boolean isCorrect) {
        this.quizId = quizId;
        this.sessionId = sessionId;
        this.questionId = questionId;
        this.userId = userId;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

}




