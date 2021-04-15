# Codefellowship

This is an app designed to showcase the use of Spring MVC to produce a full-fledged web application with user
authentication and database usage. This app provides a platform for students at Codefellows to log in, create and share
posts, and follow fellow users.

# Usage

Ensure a PostgreSQL database is available. Postgres user credentials are set in the `application.properties` file.

```shell
./gradlew bootRun
```

# API

The API is built to accept inputs via POST, PUT, and DELETE requests and responds with statically served HTML
content. We currently have the following routes:

- `GET /` Serves the home page
- `GET /login` Serves a login page
- `GET /signup` Serves a signup page
- `GET /error` Serves a page for 401 and other errors
- `GET /users` A page that lists the users of the application
- `GET /post` A page that lists all the posts in the application
- `GET /feed` A page that lists all the posts that the current user is following
- `POST /users` Provides the means to create a new user after the user is authenticated by Spring Security
- `PUT /users/{id}` Provides a way to update a user's information
- `DELETE /users/{id}` Currently unimplemented
- `PUT /users/{id}/followers` Adds the current user to the referenced user's followers.
- `DELETE /users/{id}/followers` Removes the current user from the referenced user's followers
- `POST /posts` Allows the current user to make a new post
- `PUT /posts/{id}` Allows the current user to edit a post they made