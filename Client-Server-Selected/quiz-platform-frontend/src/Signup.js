import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Signup = () => {
    const [username, setUsername] = useState(""); // intialize state
    const [password, setPassword] = useState(""); // intiailize state
    const [role, setRole] = useState("STUDENT"); // default role
    const [message, setMessage] = useState(""); // for success/error messages
    const [loading, setLoading] = useState(false); // loading state
    const navigate = useNavigate();

    const handleSignup = async (e) => {
        e.preventDefault();
        setMessage("");

        // client-side validation
        if (!username || !password) {
            setMessage("Username and password are required.");
            return;
        }

        setLoading(true); // show its loading 

        try {
            const response = await axios.post("http://localhost:8080/quizzes/register", {
                username,
                password,
                role,
            });
            setMessage(response.data);
            setUsername(""); // clear input fields
            setPassword("");
        } catch (error) {
            setMessage(error.response?.data || "Signup failed. Please try again.");
        } finally {
            setLoading(false); // reset loading state
        }
    };

    return (
        <div>
            <h1>Sign Up</h1>
            <form onSubmit={handleSignup}>
                <input
                    type="text"
                    placeholder="Username"
                    value={username} // controlled input
                    onChange={(e) => setUsername(e.target.value)} // updates state
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password} // controlled input
                    onChange={(e) => setPassword(e.target.value)} // updates state
                    required
                />
                <select value={role} onChange={(e) => setRole(e.target.value)}>
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                </select>
                <button type="submit" disabled={loading}>
                    {loading ? "Signing Up.." : "Sign Up"}
                </button>
            </form>
            <p>{message}</p>
            <button onClick={() => navigate("/login")}>Go Back to Login</button>
        </div>
    );
};

export default Signup;