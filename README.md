# Quiz Master

Quiz Master is a Java-based quiz application developed as a second-year mini project.

The application allows users to select a subject, answer multiple-choice questions, and view their score after completing the quiz. It also uses PostgreSQL to store quiz results.

## Features

* Subject selection
* Multiple-choice quiz questions
* 120-second quiz timer
* Score calculation
* Result screen
* Results history
* PostgreSQL database integration
* JavaFX graphical user interface

## Subjects

The current version includes:

* Java
* DBMS
* COA
* Electronics

## Technologies Used

* Java
* JavaFX
* FXML
* PostgreSQL
* JDBC
* Scene Builder
* Git & GitHub

## Project Structure

```text
QuizProject/
├── demo/          # Web version files
├── lib/           # External libraries
└── src/           # Java and FXML source files
```

## Database

The application uses PostgreSQL to store quiz results.

The PostgreSQL JDBC driver is included in the `lib` folder.

Before running the application, the database connection details in `DatabaseConnection.java` need to be configured according to the user's local PostgreSQL setup.

## How to Run

1. Install Java and JavaFX.
2. Install PostgreSQL.
3. Configure the database.
4. Configure the database connection details.
5. Open the project in a Java IDE.
6. Run `Main.java`.

## Project Status

The current version is a working MVP (Minimum Viable Product).

Future improvements may include:

* Adding more questions and subjects
* Improving the user interface
* Adding more quiz levels
* Improving gamification
* Adding additional database functionality

## Authors

Developed as a second-year academic mini project.
