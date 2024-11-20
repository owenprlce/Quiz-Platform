import axios from "axios";

const API_URL = "http://localhost:4000/api";

export const getQuizzes = () => axios.get(`${API_URL}/quizzes`);
export const getQuiz = (id: string) => axios.get(`${API_URL}/quizzes/${id}`);
export const createQuiz = (data: any) => axios.post(`${API_URL}/quizzes`, data);
