import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getQuiz } from "../utils/api";

export const QuizDetail: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [quiz, setQuiz] = useState<any>(null);

    useEffect(() => {
        if (!id) {
            console.error("Quiz ID is undefined");
            return;
        }

        getQuiz(id)
            .then((response) => setQuiz(response.data))
            .catch((error) => console.error("Error fetching quiz details:", error));
    }, [id]);

    if (!quiz) return <p>Loading...</p>;

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl font-bold mb-4">{quiz.title}</h2>
            {quiz.questions.map((question: any) => (
                <div key={question.id} className="mb-4">
                    <p className="text-lg">{question.text}</p>
                    <div className="flex gap-2">
                        {question.options.map((option: string, idx: number) => (
                            <button key={idx} className="btn">
                                {option}
                            </button>
                        ))}
                    </div>
                </div>
            ))}
        </div>
    );
};
