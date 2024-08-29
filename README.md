# Restaurant Evaluation Website

This project is a full-stack web application designed to help users search, locate, filter, and comment on restaurants. The front-end is built using React, providing a dynamic and responsive user interface. The back-end is implemented in Java with Spring Boot and uses MySQL for database management. Additional functionality includes user authentication and photo uploads via AWS S3.

## Features

- **Dynamic Searching and Filtering:** Users can search for restaurants based on various criteria and filter the results according to their preferences.
  ![Search Filter](/server/images/listing%20page.png "Dynamic Search Filter")
- **User Comments:** Users can leave comments on restaurant pages to share their dining experiences.
  ![User Comments](/server/images/review%20page.png "User Comments")
  ![Post Review](/server/images/post%20review.png "Post Review")
- **Photo Uploads:** Integrated AWS S3 allows users to upload photos along with their comments.
  ![Photo Upload Feature](/server/images/upload%20photo.png "Uploading Photos")
- **Responsive UI:** The front-end is designed to be responsive and user-friendly, adapting to different device screens.
    ![Detail Page](/server/images/detail%20page-1.png "Detail Page")
- **Secure User Authentication:** Implemented using Spring Boot's security features.
  ![User Authentication Feature]( /server/images/login.png "Uploading Photos")

## API Testing with Postman

I use Postman to test and demonstrate the functionality of our RESTful APIs. Below are some screenshots showing different API requests and their responses:

- **Get Restaurants:** This request retrieves all available restaurant's features.
  ![Get Restaurants](/server/images/getRestaurantWithId.png "Get Restaurants API")

- **Add Comment:** This request allows users to add a comment to a restaurant with authorization.
  ![Add Comment](/server/images/review-created.png "Add Comment API")

- **User Login:** This request demonstrates the user authentication process.
  ![User Login](/server/images/postman-login.png "User Login API")

- **User Sign Up:** This request demonstrates the user sign up process.
  ![User SignUp](/server/images/signup.png "User Sign Up API")

These examples provide a clear view of how my APIs interact with the front-end and how they can be tested using Postman.

## Technologies

- **Front-end:** React
- **Back-end:** Java, Spring Boot
- **Database:** MySQL
- **Cloud Storage:** AWS S3
- **Version Control:** Git, hosted on GitHub

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them:

```bash
npm install
java -version (Java 11 or higher)
mysql --version (MySQL 8.0 or higher)
