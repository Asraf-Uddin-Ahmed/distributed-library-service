# Distributed Library Service

The Distributed Library Service is a web application for sharing books among employees within an organization. It allows users to add/edit books, borrow books by sending transfer requests, accept or reject transfer requests, and view their activity profile. The application is built using Spring Boot, MySQL, Thymeleaf, Bootstrap, JavaScript, and Docker Compose.

## Prerequisites
- Docker installed on your local machine

## Getting Started
1. Clone the project repository to your local machine.
2. Make sure Docker is running on your machine.
3. If you are a developer:
    - Ensure a MySQL instance is running and update the `application.properties` file in the `src/main/resources` directory with your MySQL database configuration. You can set the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your MySQL database settings.
    - Run the Spring Boot application using your preferred IDE or by running the following command:
    ```
    mvn spring-boot:run
    ```
4. If you are a QA:
    - Run the following command to build and run the Docker containers for the application and MySQL database:
    ```
    docker-compose up --build
    ```
5. Once the Docker containers are up and running, you can access the application at `http://localhost:8080` in your web browser.
6. Log in to the system using the default username `user1` and password `111`, or register and log in with your Google account.

## Functionality
The Distributed Library Service provides the following functionality:

### Add/Edit Books
- Users can add new books to the system by providing book information such as title, author, genre, and description.
- Users can also edit existing books in the system to update their information.

### Borrow Books
- Users can send transfer requests to borrow books from other users.
- Users can specify the duration for which they want to borrow a book.
- Users can view their pending transfer requests and cancel them if needed.

### Accept/Reject Transfer Requests
- Users can view transfer requests sent to them by other users.
- Users can accept or reject transfer requests based on their availability to lend the book.

### Activity Profile
- Users can view their activity profile, which includes their borrowed books, lending history, and transfer request history.

## Technologies Used
- Spring Boot: A Java-based framework for building web applications.
- MySQL: A popular open-source relational database management system.
- Thymeleaf: A server-side Java template engine for rendering views in web applications.
- Bootstrap: A popular front-end CSS framework for building responsive web pages.
- JavaScript: A programming language used for adding interactivity to web pages.
- Docker Compose: A tool for defining and running multi-container Docker applications.

## Task Completion Time
- Task 1: 2 hours
- Task 2: 2 hours
- Task 3: 10 hours
- Task 4: 4 hours
- Task 5: 1 hour
- Task 6: 3 hours
- Task 7: 4 hours
- Task 8: 1 hour
- Task 9: 4 hours

## License
This project is licensed under the [MIT License](LICENSE).

## Contributing
Contributions are welcome! Please refer to the [Contributing Guidelines](CONTRIBUTING.md) for more information.
