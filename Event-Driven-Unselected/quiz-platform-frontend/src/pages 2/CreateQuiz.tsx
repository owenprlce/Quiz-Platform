import React from "react";
import { useForm, Controller } from "react-hook-form";
import { createQuiz } from "../utils/api";

export const CreateQuiz: React.FC = () => {
    const { control, handleSubmit } = useForm();

    const onSubmit = (data: any) => {
        createQuiz(data)
            .then(() => alert("Quiz created successfully!"))
            .catch((error) => console.error("Error creating quiz:", error));
    };

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl font-bold mb-4">Create Quiz</h2>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-4">
                    <Controller
                        name="title"
                        control={control}
                        defaultValue=""
                        render={({ field }) => <input {...field} placeholder="Quiz Title" />}
                    />
                </div>
                <div className="mb-4">
                    <Controller
                        name="description"
                        control={control}
                        defaultValue=""
                        render={({ field }) => <textarea {...field} placeholder="Quiz Description" />}
                    />
                </div>
                <button type="submit" className="btn">
                    Create Quiz
                </button>
            </form>
        </div>
    );
};
