import React, { useState } from 'react';

const Instructor: React.FC = () => {
    const [quizTitle, setQuizTitle] = useState('');
    const [questions, setQuestions] = useState<string[]>([]);

    const handleAddQuestion = () => {
        setQuestions([...questions, `Question ${questions.length + 1}`]);
    };

    const handleCreateQuiz = () => {
        console.log('Quiz Created:', { title: quizTitle, questions });
    };

    return (
        <div className="text-center">
            <h1 className="text-3xl font-bold">Instructor Dashboard</h1>
            <div className="mt-4">
                <input
                    type="text"
                    placeholder="Quiz Title"
                    value={quizTitle}
                    onChange={(e) => setQuizTitle(e.target.value)}
                    className="border p-2 rounded-md w-full mb-4"
                />
                <button
                    onClick={handleAddQuestion}
                    className="px-4 py-2 bg-blue-500 text-white rounded-md"
                >
                    Add Question
                </button>
                <ul className="mt-4 space-y-2">
                    {questions.map((q, index) => (
                        <li key={index} className="text-lg">
                            {q}
                        </li>
                    ))}
                </ul>
                <button
                    onClick={handleCreateQuiz}
                    className="mt-4 px-6 py-3 bg-green-500 text-white rounded-md"
                >
                    Create Quiz
                </button>
            </div>
        </div>
    );
};

export default Instructor;
