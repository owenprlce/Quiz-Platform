import React, { createContext, useState, useContext, useEffect } from "react";

const UserContext = createContext();

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        if (storedUser) {
            setUser(JSON.parse(storedUser)); // Load user from localStorage if it exists
        }
    }, []);

    const loginUser = (userData) => {
        setUser(userData); // Update context state
        localStorage.setItem("user", JSON.stringify(userData)); // Persist to localStorage
    };

    const logoutUser = () => {
        setUser(null); // Clear context state
        localStorage.removeItem("user"); // Remove from localStorage
    };

    return (
        <UserContext.Provider value={{ user, loginUser, logoutUser }}>
            {children}
        </UserContext.Provider>
    );
};