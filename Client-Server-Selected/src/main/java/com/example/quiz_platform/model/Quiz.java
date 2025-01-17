package com.example.quiz_platform.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {

    //other necesarry attributes quizzes need?
    @JsonProperty("quizId")
    private String quizId;
    private String teacherId;
    private String title;
    private List<Question> questions;

    // default constructor 
    public Quiz() {
        this.quizId = "";
        this.teacherId = "";
        this.title = "";
        this.questions = new ArrayList<>();
    }

    // copy constructor to create modified copies of a Quiz object
    public Quiz(Quiz other) {
        this.quizId = other.quizId;
        this.teacherId = other.teacherId;
        this.title = other.title;
        this.questions = new ArrayList<>(other.questions); // deep copy of questions
    }

    //helps improve readability
    public Quiz(String quizId, String teacherId, String title, List<Question> questions) {
        this.quizId = quizId;
        this.teacherId = teacherId;
        this.title = title;
        this.questions = questions != null ? questions : new ArrayList<>();
    }

    public void setId(String id) {
        this.quizId = id;
    }

    public String getId() {
        return quizId;
    }
    
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    //added an null-safety
    public void setQuestions(List<Question> questions) {
        this.questions = questions != null ? questions : new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        if (this.questions == null) {
            this.questions = new ArrayList<>();
        }
        this.questions.add(question);
    }

    public boolean removeQuestion(int index) {
        if (this.questions != null && index >= 0 && index < this.questions.size()) {
            this.questions.remove(index);
            return true; // Successfully removed
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    
}
