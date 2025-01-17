import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom"; // Import useNavigate
import { useUser } from "./context/UserContext"; // Import UserContext

const TakeQuiz = () => {
    const { quizId } = useParams(); // Get the quizId from the URL
    const { user } = useUser(); // Access user data and logout function
    const [quiz, setQuiz] = useState(null); // Store quiz data
    const [answers, setAnswers] = useState({}); // Store user answers
    const [message, setMessage] = useState(""); // Store status messages
    const navigate = useNavigate(); // Initialize navigate

    useEffect(() => {
        const fetchQuiz = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/quizzes/${quizId}`);
                console.log("Fetched Quiz Data:", response.data); // Debugging
                setQuiz(response.data);
            } catch (error) {
                console.error("Error fetching quiz:", error.response?.data || error.message);
                setMessage("Failed to load quiz. Please try again.");
            }
        };

        fetchQuiz();
        console.log("User ID (studentId):", user?.id);
    }, [quizId, user.id]);

    const handleInputChange = (questionIndex, value) => {
        setAnswers({ ...answers, [questionIndex]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const payload = Object.values(answers); // Convert answers object to an array

        console.log("Submitting Payload (Request Body):", JSON.stringify(payload, null, 2)); // Debugging

        try {
            const response = await axios.post(
                `http://localhost:8080/quizzes/${quizId}/take?studentId=${user?.id}`, // Add studentId as a query parameter
                payload, // Send answers as the request body
                {
                    headers: { "Content-Type": "application/json" }, // Specify JSON content type
                }
            );
            console.log("Submission Response:", response.data); // Debugging
            setMessage(response.data); // Display success message
        } catch (error) {
            console.error("Error submitting quiz:", error.response?.data || error.message);
            setMessage("Failed to submit quiz. Please try again.");
        }
    };

    if (!quiz) {
        return <p>Loading quiz...</p>;
    }

    return (
        <div>
            <h1>{quiz.title}</h1>
            <form onSubmit={handleSubmit}>
                {quiz.questions.map((question, index) => (
                    <div key={index}>
                        <p>{question.questionInfo}</p>
                        {question.questionType === "MULTIPLE_CHOICE" && question.options && (
                            <div>
                                {Object.entries(question.options).map(([optionKey, optionValue]) => (
                                    <label key={optionKey}>
                                        <input
                                            type="radio"
                                            name={`question-${index}`}
                                            value={optionKey}
                                            onChange={(e) => handleInputChange(index, e.target.value)}
                                        />
                                        {optionKey}: {optionValue}
                                    </label>
                                ))}
                            </div>
                        )}
                        {question.questionType === "TRUE_FALSE" && (
                            <div>
                                <label>
                                    <input
                                        type="radio"
                                        name={`question-${index}`}
                                        value="True"
                                        onChange={(e) => handleInputChange(index, e.target.value)}
                                    />
                                    True
                                </label>
                                <label>
                                    <input
                                        type="radio"
                                        name={`question-${index}`}
                                        value="False"
                                        onChange={(e) => handleInputChange(index, e.target.value)}
                                    />
                                    False
                                </label>
                            </div>
                        )}
                        {question.questionType === "SHORT_ANSWER" && (
                            <input
                                type="text"
                                placeholder="Your Answer"
                                onChange={(e) => handleInputChange(index, e.target.value)}
                            />
                        )}
                    </div>
                ))}
                <button type="submit">Submit Quiz</button>
            </form>
            {message && <p>{message}</p>}

            {/* Add Go Back Button */}
            <button onClick={() => navigate(-1)} style={{ marginTop: "20px" }}>
                Go Back
            </button>
        </div>
    );
};

export default TakeQuiz;