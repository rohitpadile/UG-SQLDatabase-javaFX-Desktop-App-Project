# Student Management Application

## Overview

This is a local desktop application designed to manage student information at COEP Technological University. The application supports two profiles: Admin and User, each with specific functionalities.

## Profiles and Functionalities

### User Profile

- **Find a Student**: Allows the user to search for student information.
- **Add a Student**: Permits the user to add new student details to the system.

### Admin Profile

- **Delete a Student**: Grants the admin the ability to remove a student's information from the system.
- **Edit a Student's Details**: Enables the admin to modify existing student details.

## Validations

Validations are in place according to the student profile requirements at COEP Technological University to ensure data integrity and consistency.

## Evolution of the Project

### Initial Implementation

- The initial version of the application was developed on the `main` branch.
- It utilized a `HashMap` for data storage and CSV files for persistence.

### Database Integration

- A new branch named `database` was created to enhance the application's data management capabilities.
- The `HashMap` and CSV file approach was replaced with a SQLite database.
- Implemented a Singleton class to manage database connections efficiently.
- Prepared statements are used for executing queries to ensure security and performance.
- A separate utility class was created to handle database operations.

As I learn more, I am eager to add additional features to enhance the functionality and user experience of this application.

## Contribution

Contributions are welcome! Please fork the repository and submit a pull request for review.

## License

This project is licensed under the GNU General Public LICENSE. See the `LICENSE` file for more details.