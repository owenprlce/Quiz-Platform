CREATE TABLE IF NOT EXISTS QUIZ (
                                    id BIGINT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
                                    description VARCHAR(255),
                                    time_limit INT,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS QUESTION (
                                        id BIGINT PRIMARY KEY,
                                        quiz_id BIGINT,
                                        text VARCHAR(255),
                                        correct_answer VARCHAR(255),
                                        FOREIGN KEY (quiz_id) REFERENCES QUIZ(id)
);