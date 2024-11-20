import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Signup from "./Signup";
import StudentHome from "./StudentHome";
import TeacherHome from "./TeacherHome";
import TakeQuiz from "./TakeQuiz";
import AllQuizResults from "./AllQuizResults"; // Import the AllQuizResults component

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/student-home" element={<StudentHome />} />
                <Route path="/teacher-home" element={<TeacherHome />} />
                <Route path="/take-quiz/:quizId" element={<TakeQuiz />} />
                <Route path="/quiz-results" element={<AllQuizResults />} /> {/* Add route for quiz results */}
                <Route path="/" element={<Login />} />
            </Routes>
        </Router>
    );
};

export default App;