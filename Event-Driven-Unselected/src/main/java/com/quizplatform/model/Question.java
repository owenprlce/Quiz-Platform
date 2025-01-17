package com.quizplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option")
    private List<String> options = new ArrayList<>();

    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY) // Set fetch type to lazy to avoid unnecessary loading
    @JoinColumn(name = "quiz_id")
    @JsonBackReference // Prevent infinite recursion when serializing
    private Quiz quiz;
}