import React from 'react';
import { Button } from '../components/ui/button';
import { Card, CardContent, CardHeader, CardTitle } from '../components/ui/card';
import { Alert, AlertDescription } from '../components/ui/alert';

type Question = {
    id: string;
    text: string;
    options: string[];
};

type Quiz = {
    id: string;
    title: string;
    questions: Question[];
};

type QuizProps = {
    quiz: Quiz;
    onSubmit: (answers: Record<string, string>) => void;
};

const Quiz: React.FC<QuizProps> = ({ quiz, onSubmit }) => {
    const [currentQuestion, setCurrentQuestion] = React.useState(0);
    const [answers, setAnswers] = React.useState<Record<string, string>>({});
    const [error, setError] = React.useState<string | null>(null);

    const handleAnswerSelect = (questionId: string, answer: string) => {
        setAnswers(prev => ({ ...prev, [questionId]: answer }));
    };

    const handleNext = () => {
        if (!answers[quiz.questions[currentQuestion].id]) {
            setError('Please select an answer before proceeding.');
            return;
        }
        setError(null);
        setCurrentQuestion(prev => Math.min(prev + 1, quiz.questions.length - 1));
    };

    const handlePrevious = () => {
        setError(null);
        setCurrentQuestion(prev => Math.max(prev - 1, 0));
    };

    const handleSubmit = () => {
        if (!answers[quiz.questions[currentQuestion].id]) {
            setError('Please select an answer before submitting.');
            return;
        }
        setError(null);
        onSubmit(answers);
    };

    return (
        <div className="min-h-screen bg-gray-100 py-8">
            <div className="container mx-auto p-4">
                {error && (
                    <Alert variant="destructive" className="mb-4">
                        <AlertDescription>{error}</AlertDescription>
                    </Alert>
                )}

                <Card>
                    <CardHeader>
                        <CardTitle>{quiz.title}</CardTitle>
                    </CardHeader>
                    <CardContent>
                        <div className="space-y-4">
                            <p className="text-lg">{quiz.questions[currentQuestion].text}</p>
                            <div className="grid gap-2">
                                {quiz.questions[currentQuestion].options.map((option, index) => (
                                    <Button
                                        key={index}
                                        className={`w-full justify-start ${
                                            answers[quiz.questions[currentQuestion].id] === option
                                                ? 'bg-blue-500 text-white'
                                                : ''
                                        }`}
                                        onClick={() =>
                                            handleAnswerSelect(quiz.questions[currentQuestion].id, option)
                                        }
                                    >
                                        {option}
                                    </Button>
                                ))}
                            </div>
                        </div>

                        <div className="flex justify-between mt-4">
                            <Button variant="outline" onClick={handlePrevious} disabled={currentQuestion === 0}>
                                Previous
                            </Button>
                            {currentQuestion === quiz.questions.length - 1 ? (
                                <Button onClick={handleSubmit}>Submit</Button>
                            ) : (
                                <Button onClick={handleNext}>Next</Button>
                            )}
                        </div>
                    </CardContent>
                </Card>
            </div>
        </div>
    );
};

export default Quiz;
