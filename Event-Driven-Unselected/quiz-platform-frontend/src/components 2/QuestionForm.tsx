import React from "react";
import { useForm, Controller, SubmitHandler } from "react-hook-form";

interface QuestionFormValues {
    question: string; // Define the expected shape of the form data
}

interface QuestionFormProps {
    onSubmit: SubmitHandler<QuestionFormValues>; // Use SubmitHandler for type safety
}

export const QuestionForm: React.FC<QuestionFormProps> = ({ onSubmit }) => {
    const { control, handleSubmit } = useForm<QuestionFormValues>({
        defaultValues: {
            question: "", // Ensure the default value matches the expected shape
        },
    });

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="mb-4">
                <Controller
                    name="question"
                    control={control}
                    render={({ field }) => (
                        <input
                            {...field}
                            placeholder="Enter your question"
                            className="input"
                        />
                    )}
                />
            </div>
            <button type="submit" className="btn">
                Add Question
            </button>
        </form>
    );
};
