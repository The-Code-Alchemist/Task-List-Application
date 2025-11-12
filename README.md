# Task-List-Application
## Technical Characteristics:
1. Java SE 21
   - The application must be able to handle exceptions gracefully and efficiently.
2. Maven 3.9.9
3. Spring Boot 3.5.7
4. Relational DB: PostgreSQL
5. Basic HTML and JavaScript for the frontend, which does not contain any business logic.

## How to Run
1. Start the docker-compose.yml file from the root directory of this project
   - Any terminal such as PowerShell, BASH, or a similar terminal will do 
   - 'docker compose up -d'
   - N.B. you can even click on it in IntelliJ
2. Run this class to Start the application
   - com/codealchemists/tasklist/TaskListApplication.java

## Functional Characteristics
1. Authentication and Authorization
   - Only authenticated users are able to create, update, and delete tasks. 
2. Task Management
   - Users are able to perform the following operations: 
   - Create: Add new tasks. 
   - Update: Modify existing tasks. 
   - Delete: Remove tasks
3. Task Status: 
4. Each task has a status that can be:
   - To Do.
   - In Progress.
   - Review
   - Done.
5. Task Details: Each task contains: 
   - A unique identifier (UUID). 
   - A short description. 
   - A long description.
6. Data Persistence: The application persists data.


