import React from "react";
import { Box, Heading, Text, Button } from "@chakra-ui/react";
import { Link } from "react-router-dom";

interface QuizCardProps {
    id: number;
    title: string;
    description: string;
}

export const QuizCard: React.FC<QuizCardProps> = ({ id, title, description }) => (
    <Box borderWidth="1px" borderRadius="lg" overflow="hidden" p="6">
        <Heading size="md">{title}</Heading>
        <Text mt="4">{description}</Text>
        <Link to={`/quiz/${id}`}>
            <Button mt="4" colorScheme="blue">Take Quiz</Button>
        </Link>
    </Box>
);
