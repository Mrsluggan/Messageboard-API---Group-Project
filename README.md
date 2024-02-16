# Messageboard API - Group Project

## Description

Welcome to the message board API, a platform designed to handle both new users and new posts. With this API, you can create and manage user accounts as well as create and publish new posts.

## Getting Started

### Dependencies
-RESTEasy Classic
-RESTEasy Classic JSON-B
-Hibernate ORM
-Hibernate Validator
-JDBC Driver - PostgreSQL
-Narayana JTA - Transaction manager
-SmallRye OpenAPI
-PrimeFaces

Program: Docker

### Installation

To install and run the API locally, follow these steps:

1. Clone this repository to your local machine.

2. Start Docker.

3. Open the terminal and navigate to the directory where you cloned the project.

4. Run the following command to start the API:

    ```
    ./mvnw quarkus:dev
    ```

5. The API should now be available at http://localhost:8080.

### Executing program

To test the API and interact with it, use an appropriate client capable of sending HTTP requests, such as Postman.

To create a new developer user and get access to a new API key, go to http://localhost:8080/register.xhtml and register.

Once you've obtained your API key, copy and paste it in to the Postman application.

In the Postman application, click on the tab "Headers" and type "API-Key" in the Key section, and in the Value section, paste your newly obtained Api key.

To learn more about the API endpoints, go to http://localhost:8080/q/swagger-ui/ after starting the API. You'll find more detailed documentation.

## Help

### Common Problems and Solutions

**Problem:** The API does not respond when I try to access it locally.

**Solution:** Make sure Docker is properly installed and that no other processes are using port 8080, which the API uses as the default port.


**Problem:** I receive the error message "Authentication failed" when trying a endpoint.

**Solution:** Ensure you provide a correct API key before attempting a fetch.


**Problem:** The command './mvnw quarkus:dev' is not recognized in the terminal.

**Solution:** One solution is to open the project with the Git Bash terminal.


If you encounter other issues or have further questions, feel free to contact the authors below.

## Authors

Brainztew • Collaborator

Lovexd16 • Collaborator

favigx • Collaborator

Mrsluggan • Collaborator

Samback92 • Collaborator

