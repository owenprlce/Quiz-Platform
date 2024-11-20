import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useUser } from "./context/UserContext"; // Import useUser

const StudentHome = () => {
    const { user, logoutUser } = useUser(); // Access user context and logout function
    const [quizzes, setQuizzes] = useState([]);
    const [error, setError] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        // Redirect to login if user is not logged in
        if (!user || !user.id) {
            navigate("/login");
            return;
        }

        // Fetch quizzes for the logged-in student
        const fetchQuizzes = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/quizzes?studentId=${user.studentId}`);
                setQuizzes(response.data);
            } catch (error) {
                console.error("Error fetching quizzes:", error.response?.data || error.message); // Debugging
                setError("Failed to fetch quizzes. Please try again later.");
            }
        };

        fetchQuizzes();
    }, [user, navigate]);

    // Handle logout
    const handleLogout = () => {
        logoutUser(); // Clear user data from context and localStorage
        navigate("/login"); // Redirect to login
    };

    return (
        <div>
            <h1>Welcome, {user.studentId ? `Student ${user.studentId}` : "Student"}!</h1>
            <button onClick={handleLogout}>Logout</button> {/* Logout Button */}
            <p>Here are the available quizzes:</p>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <ul>
                {quizzes.length > 0 ? (
                    quizzes.map((quiz) => (
                        <li key={quiz.quizId}>
                            <strong>{quiz.title}</strong>
                            <button onClick={() => navigate(`/take-quiz/${quiz.quizId}`)}>
                                Take Quiz
                            </button>
                        </li>
                    ))
                ) : (
                    <p>No quizzes available at the moment.</p>
                )}
            </ul>
        </div>
    );
};

export default StudentHome;