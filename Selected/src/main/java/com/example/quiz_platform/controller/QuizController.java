package com.example.quiz_platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.quiz_platform.service.QuizService;
import com.example.quiz_platform.service.UserService;
import com.example.quiz_platform.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/quizzes")
@CrossOrigin(origins = "http://localhost:3000/")
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;

    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.viewQuizzes());
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable String quizId) {
        Quiz quizToGet = quizService.findQuiz(quizId);
        if (quizToGet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(quizToGet);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz, @RequestParam String teacherId) {
        try {
            User teacher = validateTeacher(teacherId);
            quizService.createQuiz(teacher, teacherId, quiz.getTitle(), quiz.getQuestions());
            return ResponseEntity.ok("Created Quiz!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{quizId}/add-question")
    public ResponseEntity<String> addQuestion(@PathVariable String quizId, @RequestBody Question question, @RequestParam String teacherId) {
        try {
            User teacher = validateTeacher(teacherId);
            quizService.addQuestion(teacher, quizId, question);
            return ResponseEntity.ok("Added Question to Quiz");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{quizId}/delete-question")
    public ResponseEntity<String> deleteQuestion(@PathVariable String quizId, @RequestParam int index, @RequestParam String teacherId) {
        try {
            User teacher = validateTeacher(teacherId);
            quizService.deleteQuestion(teacher, quizId, index);
            return ResponseEntity.ok("Deleted Question: #" + index + " From Quiz");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{quizId}/update")
    public ResponseEntity<String> updateQuiz(@PathVariable String quizId, @RequestBody Quiz update, @RequestParam String teacherId) {
        try {
            User teacher = validateTeacher(teacherId);
            quizService.updateQuiz(teacher, quizId, update.getTitle(), update.getQuestions());
            return ResponseEntity.ok("Successfully Updated Quiz");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{quizId}/delete")
    public ResponseEntity<String> deleteQuiz(@PathVariable String quizId, @RequestParam String teacherId) {
        try {
            User teacher = validateTeacher(teacherId);
            quizService.deleteQuiz(teacher, quizId);
            return ResponseEntity.ok("Deleted Quiz");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{quizId}/take")
    public ResponseEntity<String> takeQuiz(@PathVariable String quizId, @RequestParam String studentId, @RequestBody List<String> answers) {
        try {
            User student = userService.getUserById(studentId);
            String quizResult = quizService.takeQuiz(student, quizId, answers);
            return ResponseEntity.ok("Your Score For Quiz: " + quizId + " Was: " + quizResult);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.createUser(user.getUsername(), user.getPassword(), user.getRole());
            return ResponseEntity.ok("User Registered Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
    User existingUser = userService.getUserByUsername(user.getUsername());
    if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully Logged In");
        response.put("role", existingUser.getRole().toString());
        response.put("id", existingUser.getId()); // Add the user ID to the response
        return ResponseEntity.ok(response);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    private User validateTeacher(String teacherId) {
        System.out.println("Validating teacherId: " + teacherId); // Debug log

        if (teacherId == null || teacherId.isEmpty()) {
            throw new IllegalArgumentException("teacherId is null or empty");
        }

        User teacher = userService.getUserById(teacherId);
        if (teacher == null || !"TEACHER".equals(teacher.getRole())) {
            throw new IllegalArgumentException("Invalid or unauthorized teacher");
        }
        return teacher;
    }

    
}