# Blog App APIs

This project is a RESTful API for a blogging application with different profiles sections like categories, comments, posts, users built using Spring Boot. It allows users to create, read, update, and delete blog posts and manage comments. The API is designed to provide a seamless experience for both front-end and back-end developers.


## Features

- **CRUD Operations**: Create, Read, Update, and Delete blog posts and comments.
- **User Authentication**: Secure endpoints with JWT-based authentication.
- **MySQL Database**: Uses MySQL for data storage, with Spring Data JPA for ORM.
- **Swagger Documentation**: Automatically generated API documentation for easier testing and exploration.
  
## Technologies Used

- **Java**: Programming language for building the application.
- **Spring Boot**: Framework for creating the RESTful API.
- **MySQL**: Relational database management system for data persistence.
- **Spring Security**: For securing API endpoints and handling user authentication.
- **Postman**: Tool for testing the API endpoints.

## Getting Started

### Prerequisites

- Java 11 or higher
- MySQL Server
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/bsainath99/blog-app-apis.git
2. Navigate to the project directory:
  cd blog-app-apis
3. Set up the MySQL database and configure your application.properties file with the database credentials.
4. Run the application:
  mvn spring-boot:run

API Endpoints
Endpoint	Method	Description
/api/posts	GET	Retrieve all blog posts
/api/posts/{id}	GET	Retrieve a specific blog post
/api/posts	POST	Create a new blog post
/api/posts/{id}	PUT	Update an existing blog post
/api/posts/{id}	DELETE	Delete a blog post


Testing
You can test the API endpoints using Postman or any API client of your choice. Ensure you have the necessary authorization headers when making requests to secure endpoints.
