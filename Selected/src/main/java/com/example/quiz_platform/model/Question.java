package com.example.quiz_platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Question {

    public enum QuestionType {
        MULTIPLE_CHOICE, TRUE_FALSE, SHORT_ANSWER;
    }

    @JsonProperty("questionInfo")
    private String questionInfo;

    private QuestionType questionType;

    private String answer;

    private Map<String, String> options; // ADDED FOR multiple-choice questions types

    // Default constructor
    public Question() {
        this.questionInfo = "";
        this.answer = "";
        this.questionType = QuestionType.SHORT_ANSWER; // Default question type
    }

    // all-args constructor
    public Question(String questionInfo, String answer, QuestionType questionType, Map<String, String> options) {
        if (questionInfo == null || questionInfo.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty");
        }

        this.questionInfo = questionInfo;
        this.answer = answer;
        this.questionType = questionType;

        // valudate options for multiple-choice questions
        if (questionType == QuestionType.MULTIPLE_CHOICE) {
            if (options == null || options.size() != 4 ||
                !options.containsKey("A") || !options.containsKey("B") ||
                !options.containsKey("C") || !options.containsKey("D")) {
                throw new IllegalArgumentException("Multiple-choice questions must have exactly 4 options: A, B, C, D.");
            }
        }
        this.options = options;
    }

    // copy constructor
    public Question(Question other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy a null Question");
        }
        this.questionInfo = other.questionInfo;
        this.answer = other.answer;
        this.questionType = other.questionType;
        this.options = other.options; 
    }

    // getters and setters
    public String getQuestion() {
        return questionInfo;
    }

    public void setQuestion(String questionInfo) {
        if (questionInfo == null || questionInfo.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        this.questionInfo = questionInfo;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty");
        }
        this.answer = answer;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        if (this.questionType == QuestionType.MULTIPLE_CHOICE) {
            if (options == null || options.size() != 4 ||
                !options.containsKey("A") || !options.containsKey("B") ||
                !options.containsKey("C") || !options.containsKey("D")) {
                throw new IllegalArgumentException("Multiple-choice questions must have exactly 4 options: A, B, C, D.");
            }
        }
        this.options = options;
    }

    // validate  the user's answer
    public boolean isAnswerValid(String userAnswer) {
        if (userAnswer == null) {
            throw new IllegalArgumentException("User answer cannot be null");
        }
        return this.answer.equalsIgnoreCase(userAnswer.trim());
    }
}