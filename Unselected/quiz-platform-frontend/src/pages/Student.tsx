import React, { useState, useEffect } from 'react';

interface Quiz {
    id: number;
    title: string;
}

interface Question {
    id: number;
    text: string;
}

const Student: React.FC = () => {
    const [quizzes, setQuizzes] = useState<Quiz[]>([]);
    const [selectedQuiz, setSelectedQuiz] = useState<Quiz | null>(null);
    const [questions, setQuestions] = useState<Question[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        // Fetch all quizzes
        fetch('http://localhost:8080/api/quiz/getQuizzes')
            .then(response => {
                if (!response.ok) throw new Error('Failed to fetch quizzes');
                return response.json();
            })
            .then(data => setQuizzes(data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    }, []);

    const handleQuizClick = (quiz: Quiz) => {
        setSelectedQuiz(quiz);
        setLoading(true);
        setError(null);

        // Fetch questions for the selected quiz
        fetch(`http://localhost:8080/api/quiz/${quiz.id}/getQuestions`)
            .then(response => {
                if (!response.ok) throw new Error('Failed to fetch questions');
                return response.json();
            })
            .then(data => setQuestions(data))
            .catch(err => setError(err.message))
            .finally(() => setLoading(false));
    };

    if (loading) return <div className="text-center">Loading...</div>;
    if (error) return <div className="text-center text-red-500">Error: {error}</div>;

    return (
        <div className="text-center">
            <h1 className="text-3xl font-bold">Student Dashboard</h1>
            {!selectedQuiz ? (
                <>
                    {quizzes.length > 0 ? (
                        <ul className="mt-4 space-y-2">
                            {quizzes.map((quiz) => (
                                <li key={quiz.id} className="text-lg text-blue-500">
                                    <button
                                        className="hover:underline"
                                        onClick={() => handleQuizClick(quiz)}
                                    >
                                        {quiz.title}
                                    </button>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p className="mt-4 text-gray-500">No quizzes found.</p>
                    )}
                </>
            ) : (
                <div className="mt-4">
                    <button
                        className="mb-4 text-blue-500 hover:underline"
                        onClick={() => setSelectedQuiz(null)}
                    >
                        Back to Quiz List
                    </button>
                    <h2 className="text-2xl font-bold">{selectedQuiz.title}</h2>
                    {questions.length > 0 ? (
                        <ul className="mt-4 space-y-2">
                            {questions.map((question) => (
                                <li key={question.id} className="text-lg">
                                    {question.text}
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p className="mt-4 text-gray-500">No questions found for this quiz.</p>
                    )}
                </div>
            )}
        </div>
    );
};

export default Student;