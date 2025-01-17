import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Homepage from './pages/Homepage';
import Student from './pages/Student';
import Instructor from './pages/Instructor';

export default function App() {
    return (
        <Router>
            <div className="min-h-screen flex items-center justify-center bg-gray-100">
                <Routes>
                    <Route path="/" element={<Homepage />} />
                    <Route path="/student" element={<Student />} />
                    <Route path="/instructor" element={<Instructor />} />
                </Routes>
            </div>
        </Router>
    );
}
