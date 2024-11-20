package com.example.quiz_platform.repository;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.quiz_platform.model.User;
import com.example.quiz_platform.model.User.Role;
import com.example.quiz_platform.model.Quiz;
import com.example.quiz_platform.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class QuizRepository {

    private List<Quiz> quizList = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    private static final String QUIZZES_JSON = "src/main/resources/quizzes.json";

    public void loadQuizJSON() {
        File file = new File(QUIZZES_JSON);

        try {
            quizList = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Quiz.class));
        } catch (IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }

    public void saveQuizList() {
        try {
            mapper.writeValue(new File(QUIZZES_JSON), quizList);
        } catch (IOException e) {
            System.err.println("Cannot save user list: " + e.getMessage());
            // error handling needed here? + in user repo?
        }
    }

    public List<Quiz> listOfQuizzes() {
        loadQuizJSON();
        return quizList;
    }

    // should this find by quizId and teacherId?
    // add different function to do this?
    public Quiz findQuizById(String quizId) {
        loadQuizJSON();
        for (Quiz quiz : quizList) {
            if (quiz.getId().equals(quizId)) {
                return quiz;
            }
        }

        return null;
    }

    public void deleteQuiz(User user, String quizId) {
        loadQuizJSON();

        Quiz toDelete = null;

        for (Quiz quiz : quizList) {
            if (quiz.getId().equals(quizId)) {
                toDelete = quiz;
                break;
            }
        }

        if (toDelete != null && toDelete.getTeacherId().equals(user.getId())) {
            quizList.remove(toDelete);
            saveQuizList();
        }
    }

    public void createQuiz(User user, Quiz quiz) {
        loadQuizJSON();
    
        if (user.getRole() != User.Role.TEACHER) {
            throw new IllegalArgumentException("Only teachers can create quizzes.");
        }
    
        if (!quiz.getTeacherId().equals(user.getId())) {
            throw new IllegalArgumentException("Teacher ID does not match the current user.");
        }
    
        // validate again questions
        for (Question question : quiz.getQuestions()) {
            if (question.getQuestionType() == Question.QuestionType.MULTIPLE_CHOICE) {
                if (question.getOptions() == null || question.getOptions().size() != 4 ||
                    !question.getOptions().containsKey("A") || 
                    !question.getOptions().containsKey("B") ||
                    !question.getOptions().containsKey("C") || 
                    !question.getOptions().containsKey("D")) {
                    throw new IllegalArgumentException("Multiple-choice questions must have exactly 4 options labeled A, B, C, and D.");
                }
            }
        }
    
        quizList.add(quiz);
        saveQuizList();
    }

    public void addQuestion(String quizId, Question question) {
        loadQuizJSON();
        for (Quiz quiz : quizList) {
            if (quiz.getId().equals(quizId)) {
                quiz.addQuestion(question); // added the utility method easier to read
                saveQuizList();
                return;
            }
        }
        throw new IllegalArgumentException("Quiz with ID " + quizId + " not found");
    }

    public void deleteQuestion(String quizId, int index) {
        loadQuizJSON();
        for (Quiz quiz : quizList) {
            if (quiz.getId().equals(quizId)) {
                quiz.removeQuestion(index); // added the utility method easier to read
                saveQuizList();
                return;
            }
        }
        throw new IllegalArgumentException("Quiz with ID " + quizId + " not found");
    }

    public boolean updateQuiz(Quiz quiz) {

        loadQuizJSON();

        Quiz dummyQuiz = null;

        for(Quiz _quiz : quizList) {
            if(_quiz.getId().equals(quiz.getId())) {
                dummyQuiz = _quiz;
                break;
            }
        }
        
        if(dummyQuiz != null) {
            dummyQuiz.setTitle(quiz.getTitle());
            dummyQuiz.setQuestions(quiz.getQuestions());
            dummyQuiz.setTeacherId(quiz.getTeacherId());
        } else {
            System.out.println("Quiz With Id: " + quiz.getId() + " Does Not Exist");
            return false;
        }

        saveQuizList();
        return true;
    }

    



    // implement take quiz functionality!
}
