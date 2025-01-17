package com.example.quiz_platform.model;

public class QuizResult {
    private String id; // Unique identifier for the result
    private String studentId;
    private String quizId;
    private String studentName; // Optional, for display purposes
    private String quizTitle; // Optional, for display purposes
    private String score;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getQuizId() {
        return quizId;
    }
    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getQuizTitle() {
        return quizTitle;
    }
    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
}