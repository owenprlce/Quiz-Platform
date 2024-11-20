# Quiz Platform Architecture Documentation

## 1) Compilation and Implementation Platform
  
### **Client-Server Layered Architecture**

**USED:**
- Spring Initializr (Back-end):
  - Spring Boot Version 3.3.5, Java JDK 17, Maven, Jar Package Type
- React (Front-end)
   
**WHAT TO INSTALL:**
- Node.js (For node and npm)
- Java JDK Version 17

**HOW TO CONFIGURE:**
- Configuration Settings:
  - quiz-platform\src\main\resources\application.properties
  
**REQUIRED TO COMPILE:**
- Java JDK Version 17 
- Maven Wrapper file used for ease of compilation:
  - 'mvnw.cmd'
- Node.js

### **Event-Driven Architecture**
  
**USED:**
- Spring Boot Version 3.2.0 (Backend)
- Java JDK 17
- Maven
- WebSocket for real-time events
- JWT authentication
- React + TypeScript (Frontend)

**REQUIRED TO COMPILE:**
- Java JDK 17
- Maven
- Node.js
- NPM/Yarn
  
## 2) Compilation

### **Client-Server Layered Architecture**

**TO COMPILE:**
 
**Frontend:**
```bash
cd quiz-platform-frontend
npm install
```

**Backend:**
```bash
cd quiz-platform

# Apple:
./mvnw compile

# Windows:
.\mvnw.cmd compile
```
  
### **Event-Driven Architecture**

**TO COMPILE:**
```bash
# Frontend:
cd quiz-platform-frontend
npm install

# Backend:
cd quiz-platform
mvn clean install -DskipTests
```

## 3) Execution

### **Client-Server Layered Architecture**

**TO EXECUTE:**

```bash
# Front-end:
npm start
   
# Back-end (Windows):
.\mvnw.cmd spring-boot:run

# Back-end (Apple):
./mvnw spring-boot:run
```
  
### **Event-Driven Architecture**

**TO EXECUTE:**
```bash
# Frontend:
npm run dev
   
# Backend:
mvn spring-boot:run
```

## 4) Differences Between Architectural Designs

### **Client-Server Layered Architecture:**
- Users interact as 'clients' with the 'server'
- Through RESTful APIs, users can create requests to the server and receive a response
- Server acts as a data container that provides the responses of information that user agents request
- Communication is synchronous, and done through means of HTTP protocol, either using methods of GET, POST

### **Event-Driven Architecture:**
- Components interact asynchronously through means of emitting and responding to events
- Instead of sequential flow of data, it is more dynamic due to the 'real-time' nature of this type of architecture
- It is beneficial if you are wanting to develop a system with a loose-coupling of components
- Architecture allows for scalability and robustness of the system, two highly important things to consider when developing a system

## 5) Reason For Design Changes

After receiving our project proposal feedback, it was made clear that we should rethink the complexity of our project. In terms of scope, the amount of functionality we would want to implement would be too much, so we decided to scale down our project. While we would not have any major design changes in terms of our prescriptive architecture allowing for a faithful descriptive architecture or 'as implemented' project, we just added necessary features. In terms of features added, the most important functionality a quiz platform should have is an ability to login as either a 'STUDENT' or 'TEACHER' role. And once logged in, depending on the role you would have a set of predefined functionality, or way of interacting with the front-end. If you were logged in as a 'STUDENT' you should be able to view quizzes, and take one that interests you. And once taken, you should be able to receive brief feedback on how well you did. On the other hand, when logged in as a 'TEACHER', you should be able to create quizzes, and view results from students who are eager to take them. While this is a different approach from many other mainstream quiz platforms like Quizlet, and Kahoot, it encapsulates all functionality necessary to be used as a tool to test teachers to test students' knowledge, and receive basic feedback on what they need to change in their teaching style.  

## 6) Information Insightful To Grader For Design Rationale

### **Client-Server Layered:**

The Client-Server layered architecture was chosen primarily due to its advantages in separation of concerns, modularity, and scalability. These three reasons ensure that as the system increases in complexity, or size, that it will remain scalable and highly flexible. In addition to this, by choosing the architectural design of having distinct layers in the application, it highly simplifies the process of development. In terms of development simplification, adding features, and debugging code issues could not be more efficient, and manageable.

Here is what each layer has to offer:
- **Model Layer:** Defines the structure of core data components User, Quiz, and Question
- **Repository:** Acts as the main intermediary for User and Quiz storage in the program. FasterXML Jackson was used for ease of data persistence and serialization into JSON.
- **Service Layer:** Contains the main business logic of the application, imperative in allowing users to interact with the core functionalities of the system. Such functionalities include: Creating Quizzes, Viewing Quiz Results, Taking Quiz all which depend on your User role in the system.
- **Controller Layer:** Is responsible for handling and facilitating HTTP requests from the front-end -> back-end. It is the main intermediary between the User Interface developed in React, and the back-end of the system. 

Overall, the above described capabilities of our system, makes the Client-Server Layered architecture a clear winner.
