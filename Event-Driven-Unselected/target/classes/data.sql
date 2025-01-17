INSERT INTO QUIZ (id, title, description, time_limit, created_at) VALUES
                                                                      ('1', 'Java Basics', 'Test your Java fundamentals', 30, CURRENT_TIMESTAMP),
                                                                      ('2', 'Spring Boot', 'Spring Boot core concepts', 45, CURRENT_TIMESTAMP),
                                                                      ('3', 'Web Development', 'HTML, CSS and JavaScript', 60, CURRENT_TIMESTAMP);

INSERT INTO QUESTION (id, quiz_id, text, correct_answer) VALUES
                                                             ('1', '1', 'What is a JVM?', 'Java Virtual Machine'),
                                                             ('2', '1', 'What is inheritance?', 'A mechanism where one class inherits properties of another class'),
                                                             ('3', '2', 'What is Spring Boot?', 'A framework to create stand-alone Spring applications');