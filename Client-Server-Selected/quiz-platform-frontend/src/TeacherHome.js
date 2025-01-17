import React, { useState } from "react";
import axios from "axios";
import { useUser } from "./context/UserContext"; // Import UserContext
import { useNavigate } from "react-router-dom";

const TeacherHome = () => {
    const [quizTitle, setQuizTitle] = useState("");
    const [questions, setQuestions] = useState([]);
    const [newQuestion, setNewQuestion] = useState({
        questionInfo: "",
        answer: "",
        questionType: "MULTIPLE_CHOICE",
        options: { A: "", B: "", C: "", D: "" }, // For multiple choice
    });
    const [message, setMessage] = useState("");

    const { user, logoutUser } = useUser(); // Access user data and logout function
    const teacherId = user?.id; // Get the teacher's ID
    const navigate = useNavigate(); // For redirection after logout

    // Add a new question to the list
    const handleAddQuestion = () => {
        if (!newQuestion.questionInfo) {
            setMessage("Question text is required.");
            return;
        }

        if (newQuestion.questionType === "MULTIPLE_CHOICE") {
            const { A, B, C, D } = newQuestion.options;
            if (!A || !B || !C || !D || !newQuestion.answer) {
                setMessage(
                    "All options (A, B, C, D) and a correct answer are required for multiple choice."
                );
                return;
            }
        }

        if (newQuestion.questionType === "TRUE_FALSE" && !newQuestion.answer) {
            setMessage("Please select True or False.");
            return;
        }

        // Add question to the list
        setQuestions([...questions, newQuestion]);
        setNewQuestion({
            questionInfo: "",
            answer: "",
            questionType: "MULTIPLE_CHOICE",
            options: { A: "", B: "", C: "", D: "" },
        });
        setMessage(""); // Clear any error message
    };

    // Handle quiz creation
    const handleCreateQuiz = async (e) => {
        e.preventDefault();

        if (!quizTitle) {
            setMessage("Quiz title is required.");
            return;
        }

        if (questions.length === 0) {
            setMessage("At least one question is required.");
            return;
        }

        try {
            const response = await axios.post(
                `http://localhost:8080/quizzes/create?teacherId=${teacherId}`,
                {
                    quizId: "",
                    title: quizTitle,
                    questions: questions.map((q) => {
                        if (q.questionType === "MULTIPLE_CHOICE") {
                            return { ...q, options: q.options };
                        }
                        const { options, ...rest } = q;
                        return rest;
                    }),
                }
            );

            setMessage("Quiz created successfully!");
            setQuizTitle("");
            setQuestions([]);
        } catch (error) {
            const errorMessage = error.response?.data || "Failed to create quiz. Please try again.";
            setMessage(typeof errorMessage === "string" ? errorMessage : "An unexpected error occurred.");
        }
    };

    // Handle logout
    const handleLogout = () => {
        logoutUser(); // Clear user data from context and localStorage
        navigate("/login"); // Redirect to login page
    };

    if (!user) {
        return <div>Loading...</div>; // Show loading state while user data is being fetched
    }

    return (
        <div>
            <h1>Welcome, {user.username}!</h1>
            <button onClick={handleLogout}>Logout</button> {/* Logout Button */}
            <p>Create a new quiz below:</p>

            {/* Create Quiz Form */}
            <form onSubmit={handleCreateQuiz}>
                <div>
                    <label>Quiz Title:</label>
                    <input
                        type="text"
                        value={quizTitle}
                        onChange={(e) => setQuizTitle(e.target.value)}
                        placeholder="Enter quiz title"
                        required
                    />
                </div>

                {/* Add Question Functionality */}
                <div>
                    <h3>Add Questions:</h3>
                    <input
                        type="text"
                        value={newQuestion.questionInfo}
                        onChange={(e) =>
                            setNewQuestion({ ...newQuestion, questionInfo: e.target.value })
                        }
                        placeholder="Question text"
                    />
                    {newQuestion.questionType === "MULTIPLE_CHOICE" && (
                        <div>
                            <input
                                type="text"
                                value={newQuestion.options.A}
                                onChange={(e) =>
                                    setNewQuestion({
                                        ...newQuestion,
                                        options: { ...newQuestion.options, A: e.target.value },
                                    })
                                }
                                placeholder="Option A"
                            />
                            <input
                                type="text"
                                value={newQuestion.options.B}
                                onChange={(e) =>
                                    setNewQuestion({
                                        ...newQuestion,
                                        options: { ...newQuestion.options, B: e.target.value },
                                    })
                                }
                                placeholder="Option B"
                            />
                            <input
                                type="text"
                                value={newQuestion.options.C}
                                onChange={(e) =>
                                    setNewQuestion({
                                        ...newQuestion,
                                        options: { ...newQuestion.options, C: e.target.value },
                                    })
                                }
                                placeholder="Option C"
                            />
                            <input
                                type="text"
                                value={newQuestion.options.D}
                                onChange={(e) =>
                                    setNewQuestion({
                                        ...newQuestion,
                                        options: { ...newQuestion.options, D: e.target.value },
                                    })
                                }
                                placeholder="Option D"
                            />
                            <input
                                type="text"
                                value={newQuestion.answer}
                                onChange={(e) =>
                                    setNewQuestion({ ...newQuestion, answer: e.target.value })
                                }
                                placeholder="Correct option (A, B, C, or D)"
                            />
                        </div>
                    )}
                    {newQuestion.questionType === "TRUE_FALSE" && (
                        <div>
                            <label>
                                <input
                                    type="radio"
                                    name="trueFalse"
                                    value="True"
                                    checked={newQuestion.answer === "True"}
                                    onChange={() =>
                                        setNewQuestion({ ...newQuestion, answer: "True" })
                                    }
                                />
                                True
                            </label>
                            <label>
                                <input
                                    type="radio"
                                    name="trueFalse"
                                    value="False"
                                    checked={newQuestion.answer === "False"}
                                    onChange={() =>
                                        setNewQuestion({ ...newQuestion, answer: "False" })
                                    }
                                />
                                False
                            </label>
                        </div>
                    )}
                    {newQuestion.questionType === "SHORT_ANSWER" && (
                        <input
                            type="text"
                            value={newQuestion.answer}
                            onChange={(e) =>
                                setNewQuestion({ ...newQuestion, answer: e.target.value })
                            }
                            placeholder="Correct answer"
                        />
                    )}
                    <select
                        value={newQuestion.questionType}
                        onChange={(e) =>
                            setNewQuestion({ ...newQuestion, questionType: e.target.value })
                        }
                    >
                        <option value="MULTIPLE_CHOICE">Multiple Choice</option>
                        <option value="TRUE_FALSE">True/False</option>
                        <option value="SHORT_ANSWER">Short Answer</option>
                    </select>
                    <button type="button" onClick={handleAddQuestion}>
                        Add Question
                    </button>
                </div>

                {/* Display Added Questions */}
                <div>
                    <h3>Questions Added:</h3>
                    <ul>
                        {questions.map((q, index) => (
                            <li key={index}>
                                <strong>{q.questionInfo}</strong> - {q.answer} ({q.questionType})
                                {q.questionType === "MULTIPLE_CHOICE" && (
                                    <ul>
                                        <li>A: {q.options.A}</li>
                                        <li>B: {q.options.B}</li>
                                        <li>C: {q.options.C}</li>
                                        <li>D: {q.options.D}</li>
                                    </ul>
                                )}
                            </li>
                        ))}
                    </ul>
                </div>

                <button type="submit">Create Quiz</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
};

export default TeacherHome;