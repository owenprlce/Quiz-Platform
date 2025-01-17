import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useUser } from "./context/UserContext"; // Import UserContext

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);

    const { loginUser } = useUser(); // Access loginUser from context
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setMessage("");

        if (!username || !password) {
            setMessage("Username and password are required.");
            return;
        }

        setLoading(true);

        try {
            const response = await axios.post("http://localhost:8080/quizzes/login", {
                username,
                password,
            });

            const { id, role, message } = response.data;

            // Store full user data in context and localStorage
            const userData = { id, role, username };
            loginUser(userData); // Call context method to update user state

            setMessage(message);

            // Navigate based on role
            if (role === "STUDENT") {
                navigate("/student-home");
            } else if (role === "TEACHER") {
                navigate("/teacher-home");
            }
        } catch (error) {
            const errorMessage = error.response?.data || "Login failed. Please try again.";
            setMessage(errorMessage);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit" disabled={loading}>
                    {loading ? "Logging in..." : "Login"}
                </button>
            </form>
            <p>{message}</p>
            <button onClick={() => navigate("/signup")}>Sign Up</button>
        </div>
    );
};

export default Login;