import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AllQuizResults = () => {
    const [results, setResults] = useState([]);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate(); // Hook for navigation

    useEffect(() => {
        const fetchAllResults = async () => {
            try {
                const response = await axios.get("http://localhost:8080/quizzes/results/all");
                setResults(response.data);
            } catch (error) {
                setError("Failed to fetch quiz results.");
                console.error("Error fetching quiz results:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchAllResults();
    }, []);

    if (loading) {
        return <p>Loading quiz results...</p>;
    }

    if (error) {
        return <p style={{ color: "red" }}>{error}</p>;
    }

    if (results.length === 0) {
        return (
            <div>
                <p>No quiz results available.</p>
                <button onClick={() => navigate(-1)}>Back</button> {/* Back button */}
            </div>
        );
    }

    return (
        <div>
            <h1>All Quiz Results</h1>
            <button onClick={() => navigate(-1)} style={{ marginBottom: "10px" }}>
                Back
            </button> {/* Back button */}
            <table border="1" style={{ width: "100%", textAlign: "left" }}>
                <thead>
                <tr>
                    <th>Quiz Title</th>
                    <th>Student Name</th>
                    <th>Score</th>
                </tr>
                </thead>
                <tbody>
                {results.map((result) => (
                    <tr key={result.id}>
                        <td>{result.quizTitle}</td>
                        <td>{result.studentName}</td>
                        <td>{result.score}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default AllQuizResults;