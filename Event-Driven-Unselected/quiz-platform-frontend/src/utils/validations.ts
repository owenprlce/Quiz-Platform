export const validateQuiz = (quiz: any) => {
    if (!quiz.title || !quiz.description) return false;
    return true;
};
