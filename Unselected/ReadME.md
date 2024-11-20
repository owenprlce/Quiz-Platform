# Event-Driven Implementation 

## 1. Compilation and Implementation 

### **Technologies Used**
- **Backend:**
  - Spring Boot Version 3.2.0
  - Java JDK 17
  - Maven
  - WebSocket for real-time events
  - JWT for authentication
- **Frontend:**
  - React + TypeScript

### **What to Install**
- Node.js (for frontend)
- Java JDK Version 17
- Maven

### **Configuration**
- **Backend Configuration:** `quiz-platform/src/main/resources/application.yml`
- **Frontend Configuration:** `quiz-platform-frontend/.env`

### **Requirements to Compile**
- Java JDK 17
- Maven
- Node.js
- NPM/Yarn


## 2. Compilation

### **Frontend**
cd quiz-platform-frontend
npm install

### **Backend**
cd quiz-platform
mvn clean install -DskipTests

## 3. Execution

### **Frontend and backend**
npm start

## 4. Architecture Details

### **Communication Type**
- Asynchronous: WebSocket connections

### **Features**
- Real-time updates between server and clients
- Events are published and consumed for quiz actions:
  - `QuizStartEvent`
  - `QuestionAnsweredEvent`
  - `QuizCompletedEvent`
- Event handlers process events asynchronously
- State changes are propagated through event notifications

---

## 5. Key Components

### **Backend**
- **Event Publishers:** Publish quiz-related events
- **Event Handlers:** Process events asynchronously
- **WebSocket Controllers:** Handle real-time connections
- **JWT Authentication:** Secure user sessions
- **Models:** Quiz, Question, User, QuizSession

### **Frontend**
- **WebSocket Client:** Maintains real-time connection
- **Event Listeners:** React to server events
- **Student Dashboard:** View and take quizzes
- **Teacher Dashboard:** Create and view results of quizzes
- **Real-time Quiz Interface:** Interactive quiz-taking

---

## 6. Design Rationale

Architecture Decision: Client-Server vs Event-Driven
Initial Analysis
When starting our quiz platform project, we considered two main architectural styles:

Client-Server Layered Architecture
Event-Driven Architecture

Initially, we were drawn to event-driven because its architecture triggers events which invoke specific functionalities. However, after careful consideration of our actual needs and time constraints, we chose to go with the simpler client-server approach.

Why We Chose Client-Server
1. Development Speed
The client-server architecture was much simpler to implement. With standard REST APIs and a straightforward React frontend, we could get the basic quiz functionality working quickly. Event-driven would have required setting up WebSocket connections, event handlers, and dealing with state management across real-time connections - more complexity than we needed.
2. Team Experience
Our team was more familiar with traditional client-server patterns. Most of us had worked with REST APIs and React before, making it easier to divide tasks and work efficiently. Event-driven would have required additional learning and potential delays.
3. Actual Requirements
Looking at our core requirements:

Students need to take quizzes
Students need to get their grade
Teachers need to create quizzes
Teachers need to view results

None of these actually required real-time updates. A simple request-response pattern works perfectly fine for our use case. While real-time updates would be nice, they weren't essential for our MVP.

4. Maintenance
Client-server is easier to maintain because:

Clear separation of concerns
Well-defined data flow
Easier to debug
Simpler testing

5. Resource Efficiency
Our client-server implementation is more resource-efficient for our needs. Event-driven would have required maintaining WebSocket connections and handling real-time events, which would have been overkill for basic quiz functionality.
Learning Experience
Through this process, we learned that sometimes the simpler solution is better. While event-driven architecture has its benefits, it would have been overengineering for our specific needs. The client-server architecture let us focus on delivering core functionality without getting bogged down in unnecessary complexity.
