import React, { useEffect, useState } from "react";
import { QuizCard } from "../components/QuizCard";
import { getQuizzes } from "../utils/api";

export const Home: React.FC = () => {
    const [quizzes, setQuizzes] = useState([]);

    useEffect(() => {
        getQuizzes()
            .then((response) => setQuizzes(response.data))
            .catch((error) => console.error("Error fetching quizzes:", error));
    }, []);

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl font-bold mb-4">Available Quizzes</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                {quizzes.map((quiz: any) => (
                    <QuizCard key={quiz.id} id={quiz.id} title={quiz.title} description={quiz.description} />
                ))}
            </div>
        </div>
    );
};
