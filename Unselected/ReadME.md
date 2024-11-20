# Event-Driven Quiz Platform Implementation Guide

## 1. Compilation and Implementation Platform

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

---

## 2. Compilation

### **Frontend**
```bash
cd quiz-platform-frontend
npm install

### **Backend**
cd quiz-platform
mvn clean install -DskipTests

## 3. Execution

To execute both the frontend and backend:

npm start

# 4. Architecture Details

### **Communication Type**
- Asynchronous through WebSocket connections

### **Features**
- Real-time updates between server and clients
- Events are published and consumed for quiz actions:
  - `QuizStartEvent`
  - `QuestionAnsweredEvent`
  - `QuizCompletedEvent`
- Event handlers process events asynchronously
- State changes are propagated through event notifications
- WebSocket enables bi-directional communication

---

# 5. Key Components

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
- **Teacher Dashboard:** Create and monitor quizzes
- **Real-time Quiz Interface:** Interactive quiz-taking

---

# 6. Design Rationale

### **Real-time Interaction**
- Immediate feedback for quiz answers
- Live updates for quiz progress
- Real-time monitoring for teachers

### **Scalability**
- Asynchronous event processing
- Decoupled components
- Independent scaling of services

### **Responsiveness**
- No polling required
- Reduced server load
- Better user experience

### **Flexibility**
- Easy to add new event types
- Simple to extend functionality
- Loose coupling between components

---

The architecture supports both synchronous (REST) and asynchronous (WebSocket) communication, making it ideal for an interactive quiz platform where real-time updates and immediate feedback are crucial for the user experience.

