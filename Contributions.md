# Quiz Platform

This is a quiz platform built with a **Client-Server Layered Architecture** designed for scalability, modularity, and ease of development. The system is split into distinct layers to ensure separation of concerns, making it easier to maintain and expand as it grows.

## Architecture Overview

### Client-Server Layered Design
The **Client-Server layered architecture** was chosen for the project due to its clear advantages in scalability, modularity, and maintainability. This approach enables efficient debugging, easy feature additions, and a well-structured development process.

### Layers Breakdown

1. **Model Layer**  
   Defines the core data components of the system, including:
   - **User**: Represents the users of the platform.
   - **Quiz**: Contains the quiz structure.
   - **Question**: Holds the individual questions within a quiz.

2. **Repository Layer**  
   Acts as the intermediary between the data model and storage. Uses **FasterXML Jackson** for data serialization and persistence in JSON format.

3. **Service Layer**  
   Contains the main business logic of the application, including:
   - **Creating Quizzes**
   - **Viewing Quiz Results**
   - **Taking Quizzes**  
   These features depend on the user's role in the system.

4. **Controller Layer**  
   Handles HTTP requests from the front-end (built in React) and communicates with the back-end. It ensures that user actions are processed correctly between the UI and server.

### My Contributions

I was responsible for developing the **backend logic** for the **Client-Server Layered (CSL) architecture**. This included implementing the core functionality for managing users, quizzes, and results, ensuring smooth interactions with the front-end and reliable data storage. Inside of the 'Selected' folder contains all necesarry information to compile and run the platform.

### Event-Driven Architecture (Alternative Feature)
An alternative **Event-Driven Architecture** was explored by one of my groupmates. This architecture focuses on asynchronous communication between services, which enables the system to react to events in real-time. This approach was not selected for the final implementation but highlights the flexibility of our design choices.

## Key Features
- Create and manage quizzes
- Take quizzes and view results
- User roles with specific access permissions

## Conclusion
The **Client-Server Layered Architecture** ensures a scalable, maintainable, and efficient quiz platform. By separating responsibilities across distinct layers, the system remains flexible and easy to develop as new features are added. My work on the backend logic played a key role in making this system function seamlessly, while the event-driven architecture concept provided a promising alternative. While the UI is simple, the primary goal of this project was to explore different software architecture styles, and implement them precisely.