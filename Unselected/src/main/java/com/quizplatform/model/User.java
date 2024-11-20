package com.quizplatform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum UserRole {
    STUDENT,
    INSTRUCTOR,
    ADMIN
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz_user") // Avoiding 'user' table name conflicts
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
