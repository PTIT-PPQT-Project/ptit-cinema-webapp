# PTIT Cinema API Documentation

This document outlines the API endpoints used in the PTIT Cinema Web Application.

## Base URL
`http://localhost:8080/api/v1` (Example)

## Authentication

### Login
*   **Endpoint:** `/auth/login`
*   **Method:** `POST`
*   **Description:** Authenticates a user and returns access tokens.
*   **Request Body:**
    ```json
    {
      "username": "username",
      "password": "password123"
    }
    ```
*   **Response (200 OK):**
    ```json
    {
      "accessToken": "eyJhbGciOiJIUzI1NiIs...",
      "user": {
        "id": 1,
        "username": "user123",
        "email": "user@example.com",
        "fullName": "John Doe",
        "phone": "0123456789",
        "roles": ["CUSTOMER"]
      }
    }
    ```

### Register
*   **Endpoint:** `/auth/register`
*   **Method:** `POST`
*   **Description:** Registers a new user.
*   **Request Body:**
    ```json
    {
      "username": "newuser",
      "email": "new@example.com",
      "password": "password123",
      "fullName": "New User",
      "phone": "0987654321"
    }
    ```
*   **Response (200 OK):**
    ```json
    {
      "accessToken": "eyJhbGciOiJIUzI1NiIs...",
      "user": {
        "id": 2,
        "username": "newuser",
        "email": "new@example.com",
        "fullName": "New User",
        "phone": "0987654321",
        "roles": ["CUSTOMER"]
      }
    }
    ```

### Get Current User Profile
*   **Endpoint:** `/users/me`
*   **Method:** `GET`
*   **Description:** Retrieves the profile of the currently authenticated user.
*   **Headers:** `Authorization: Bearer <accessToken>`
*   **Response (200 OK):**
    ```json
    {
      "id": 1,
      "username": "user123",
      "email": "user@example.com",
      "fullName": "John Doe",
      "phone": "0123456789",
      "roles": ["CUSTOMER"]
    }
    ```

## Movies

### Get All Movies
*   **Endpoint:** `/movies`
*   **Method:** `GET`
*   **Description:** Retrieves a list of all movies.
*   **Response (200 OK):**
    ```json
    [
      {
        "id": 1,
        "title": "Dune: Part Two",
        "genre": "Sci-Fi, Adventure",
        "rating": 8.8,
        "poster": "https://image.tmdb.org/t/p/w500/...",
        "duration": "2h 46m",
        "releaseDate": "March 1, 2024"
      },
      // ...
    ]
    ```

### Get Movie Details
*   **Endpoint:** `/movies/{id}`
*   **Method:** `GET`
*   **Description:** Retrieves detailed information about a specific movie.
*   **Response (200 OK):**
    ```json
    {
      "id": 1,
      "title": "Dune: Part Two",
      "genre": "Sci-Fi, Adventure",
      "rating": 8.8,
      "poster": "https://image.tmdb.org/t/p/w500/...",
      "backdrop": "https://image.tmdb.org/t/p/original/...",
      "duration": "2h 46m",
      "releaseDate": "March 1, 2024",
      "synopsis": "Paul Atreides unites with Chani...",
      "director": "Denis Villeneuve",
      "cast": ["Timothée Chalamet", "Zendaya"],
      "trailerUrl": "https://www.youtube.com/embed/..."
    }
    ```

## Cinemas & Showtimes

### Get All Showtimes
*   **Endpoint:** `/showtimes`
*   **Method:** `GET`
*   **Query Parameters:** 
    * `movieId={id}` (optional)
    * `roomId={id}` (optional)
*   **Description:** Retrieves a list of showtimes, optionally filtered by movie or room.
*   **Response (200 OK):**
    ```json
    [
      {
        "id": 1,
        "movieId": 1,
        "cinemaId": 1,
        "cinema": {
          "id": 1,
          "name": "PTIT Cinema Central",
          "location": "Hanoi Center"
        },
        "date": "2024-11-23",
        "times": ["10:00", "13:30", "17:00", "20:30"],
        "price": 120000,
        "roomId": 1
      }
    ]
    ```

### Get Showtime Details
*   **Endpoint:** `/showtimes/{id}`
*   **Method:** `GET`
*   **Description:** Retrieves detailed information about a specific showtime.
*   **Response (200 OK):**
    ```json
    {
      "id": 1,
      "movieId": 1,
      "cinemaId": 1,
      "cinema": {
        "id": 1,
        "name": "PTIT Cinema Central",
        "location": "Hanoi Center"
      },
      "date": "2024-11-23",
      "times": ["10:00", "13:30", "17:00", "20:30"],
      "price": 120000,
      "roomId": 1
    }
    ```

### Get Seat Map
*   **Endpoint:** `/rooms/{roomId}/seats`
*   **Method:** `GET`
*   **Description:** Retrieves the seat map for a specific room.
*   **Response (200 OK):**
    ```json
    {
      "roomId": 1,
      "rows": 8,
      "seatsPerRow": 12,
      "seats": [
        {
          "id": "A1",
          "row": "A",
          "number": 1,
          "type": "standard", // optional
          "status": "available",
          "price": 100000
        },
        // ...
      ]
    }
    ```

## Bookings

### Create Booking
*   **Endpoint:** `/bookings`
*   **Method:** `POST`
*   **Description:** Creates a new booking.
*   **Headers:** `Authorization: Bearer <accessToken>`
*   **Request Body:**
    ```json
    {
      "movieId": 1,
      "showtimeId": 1,
      "cinemaName": "PTIT Cinema Central",
      "movieTitle": "Dune: Part Two",
      "date": "2024-11-23",
      "time": "10:00",
      "seats": ["A1", "A2"],
      "totalPrice": 200000,
      "paymentInfo": {
        "method": "credit_card",
        "cardNumber": "**** **** **** 1234",
        "cardHolder": "JOHN DOE"
      }
    }
    ```
*   **Response (201 Created):**
    ```json
    {
      "id": "BK1716...",
      "userId": 1,
      "movieId": 1,
      "showtimeId": 1,
      "cinemaName": "PTIT Cinema Central",
      "movieTitle": "Dune: Part Two",
      "date": "2024-11-23",
      "time": "10:00",
      "seats": ["A1", "A2"],
      "totalPrice": 200000,
      "status": "confirmed",
      "createdAt": "2024-11-23T10:00:00Z",
      "qrCode": "PTIT_CINEMA_BK1716..."
    }
    ```

### Get User Bookings
*   **Endpoint:** `/bookings/my-bookings`
*   **Method:** `GET`
*   **Description:** Retrieves all bookings for the authenticated user.
*   **Headers:** `Authorization: Bearer <accessToken>`
*   **Response (200 OK):**
    ```json
    [
      {
        "id": "BK1716...",
        "userId": 1,
        "movieId": 1,
        "showtimeId": 1,
        "cinemaName": "PTIT Cinema Central",
        "movieTitle": "Dune: Part Two",
        "date": "2024-11-23",
        "time": "10:00",
        "seats": ["A1", "A2"],
        "totalPrice": 200000,
        "status": "confirmed",
        "createdAt": "2024-11-23T10:00:00Z",
        "qrCode": "PTIT_CINEMA_BK1716..."
      }
    ]
    ```

## Manager / Admin

### Create Movie
*   **Endpoint:** `/movies`
*   **Method:** `POST`
*   **Description:** Creates a new movie.
*   **Headers:** `Authorization: Bearer <accessToken>` (Role: MANAGER/ADMIN)
*   **Request Body:**
    ```json
    {
      "title": "New Movie",
      "genre": "Action",
      "rating": 0,
      "poster": "https://example.com/poster.jpg",
      "backdrop": "https://example.com/backdrop.jpg",
      "duration": "2h",
      "releaseDate": "2024-01-01",
      "synopsis": "Movie description",
      "director": "Director Name",
      "cast": ["Actor 1", "Actor 2"],
      "trailerUrl": "https://youtube.com/..."
    }
    ```
*   **Response (201 Created):**
    ```json
    {
      "id": 101,
      "title": "New Movie",
      // ... other fields
    }
    ```

### Update Movie
*   **Endpoint:** `/movies/{id}`
*   **Method:** `PUT`
*   **Description:** Updates an existing movie.
*   **Headers:** `Authorization: Bearer <accessToken>` (Role: MANAGER/ADMIN)
*   **Request Body:**
    ```json
    {
      "title": "Updated Title",
      // ... fields to update
    }
    ```
*   **Response (200 OK):**
    ```json
    {
      "id": 101,
      "title": "Updated Title",
      // ...
    }
    ```

### Delete Movie
*   **Endpoint:** `/movies/{id}`
*   **Method:** `DELETE`
*   **Description:** Deletes a movie.
*   **Headers:** `Authorization: Bearer <accessToken>` (Role: MANAGER/ADMIN)
*   **Response (204 No Content)**

### Create Showtime
*   **Endpoint:** `/showtimes`
*   **Method:** `POST`
*   **Description:** Creates a new showtime.
*   **Headers:** `Authorization: Bearer <accessToken>` (Role: MANAGER/ADMIN)
*   **Request Body:**
    ```json
    {
      "movieId": 1,
      "cinemaId": 1,
      "date": "2024-11-25",
      "times": ["09:00", "14:00"],
      "price": 100000,
      "roomId": 1
    }
    ```
*   **Response (201 Created):**
    ```json
    {
      "id": 501,
      "movieId": 1,
      // ...
    }
    ```

### Update Showtime
*   **Endpoint:** `/showtimes/{id}`
*   **Method:** `PUT`
*   **Description:** Updates an existing showtime.
*   **Headers:** `Authorization: Bearer <accessToken>` (Role: MANAGER/ADMIN)
*   **Request Body:**
    ```json
    {
      "date": "2024-11-26",
      "times": ["10:00"]
    }
    ```
*   **Response (200 OK):**
    ```json
    {
      "id": 501,
      // ...
    }
    ```

### Delete Showtime
*   **Endpoint:** `/showtimes/{id}`
*   **Method:** `DELETE`
*   **Description:** Deletes a showtime.
*   **Headers:** `Authorization: Bearer <accessToken>` (Role: MANAGER/ADMIN)
*   **Response (204 No Content)**

### Get All Rooms
*   **Endpoint:** `/rooms`
*   **Method:** `GET`
*   **Description:** Retrieves a list of all cinema rooms.
*   **Response (200 OK):**
    ```json
    [
      {
        "id": 1,
        "name": "Room 1",
        "cinemaId": 1
      }
    ]
    ```
