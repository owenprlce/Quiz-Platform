import React from "react";
import { Box, Flex, Heading, Button } from "@chakra-ui/react";
import { Link } from "react-router-dom";

export const Navbar: React.FC = () => (
    <Box bg="gray.100" px="4" py="2" boxShadow="md">
        <Flex justify="space-between" align="center">
            <Heading as="h1" size="lg" fontWeight="bold">
                Quiz Platform
            </Heading>
            <Flex gap="4">
                <Link to="/">
                    <Button variant="ghost" colorScheme="blue">
                        Home
                    </Button>
                </Link>
                <Link to="/create">
                    <Button colorScheme="blue">Create Quiz</Button>
                </Link>
            </Flex>
        </Flex>
    </Box>
);
