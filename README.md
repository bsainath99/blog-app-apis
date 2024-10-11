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

## API Reference

### Category Section API Endpoints

| HTTP Method | Endpoint                    | Description                                      | Request Body Type        |
|-------------|-----------------------------|--------------------------------------------------|--------------------------|
| POST        | `/api/categories/create`    | Create a new category.                          | `CategoryDTO` (JSON)     |
| PUT         | `/api/categories/update/{catId}` | Update an existing category by ID.               | `CategoryDTO` (JSON)     |
| DELETE      | `/api/categories/delete/{catId}` | Delete a category by ID.                        | categoryID                   |
| GET         | `/api/categories/get/{catId}`    | Retrieve a category by ID.                      | categoryID                     |
| GET         | `/api/categories/getAll`         | Retrieve all categories.                        | None                     |


### POST Section API ENDPOINTS 

| Method     | End point | Request Body | Description |
| :-------- | :------- | :--------------| :------------------------- |
| POST | /api/posts/user/{userId}/category/{categoryId}/posts	  | UserID, CategoryID | Create a new post under a user and category  |
| GET | /api/posts/user/{userId}/posts  | UserID | Get all posts by a specific user  |
| GET | /api/posts/category/{categoryId}/posts  | CategoryID | Get all posts in a specific category  |
| GET | /api/posts/allPosts  | N/A | Get all posts  |
| GET |/api/posts/post/{postId}  | PostID | Get a post by its ID  |
| DELETE | /api/posts/delete/{postId}  | PostID | Delete a post by its ID  |
| PUT | /api/posts/update/{postId}  | PostID | Update a post by its ID  |
| GET | /api/posts/search/{keywords}  | String |Search for posts by title keywords  |
| POST | 	/api/posts/post/image/upload/{postId}  | MultipartFile (image) | Upload an image for a post  |
| GET | /api/posts/post/image/{imageName}  | imageName |	Download a post's image by filename |


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




Testing
You can test the API endpoints using Postman or any API client of your choice. Ensure you have the necessary authorization headers when making requests to secure endpoints.
