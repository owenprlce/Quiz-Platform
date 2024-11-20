import React from 'react';
import { Link } from 'react-router-dom';

const Homepage: React.FC = () => {
    return (
        <div className="text-center">
            <h1 className="text-4xl font-bold">Welcome to the Quiz Platform</h1>
            <p className="text-lg text-gray-600 mt-4">Choose your role to proceed:</p>
            <div className="mt-8 space-y-4">
                <Link to="/student">
                    <button className="px-6 py-3 bg-blue-500 text-white rounded-md shadow-md">
                        Student
                    </button>
                </Link>
                <Link to="/instructor">
                    <button className="px-6 py-3 bg-green-500 text-white rounded-md shadow-md">
                        Instructor
                    </button>
                </Link>
            </div>
        </div>
    );
};

export default Homepage;
