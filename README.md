# Library Management System
<a name="readme-top"></a>
### Project Description
The Library Management System allows librarians to manage books, patrons, and borrowing records. This project is built using Spring Boot.
#### Requirements:
##### Entities
1.	Book Attributes: id, title, author, publicationYear, isbn
2.	Patron Attributes: id, name, contactInformation
3.	Borrowing Record Attributes: id, book_Id, patron_Id, borrowDate, returnDate, isReturned
##### Features
*	#### Book Management:

    • Retrieve a list of all books (GET /api/books)
    
    • Retrieve details of a specific book by ID (GET /api/books/{id})
    
    •	Add a new book to the library (POST /api/books)
    
    •	Update an existing book's information (PUT /api/books/{id})
    
    •	Remove a book from the library (DELETE /api/books/{id})

*	#### Patron Management:

    •	Retrieve a list of all patrons (GET /api/patrons)
    
    •	Retrieve details of a specific patron by ID (GET /api/patrons/{id})
    
    •	Add a new patron to the system (POST /api/patrons)
    
    •	Update an existing patron's information (PUT /api/patrons/{id})
    
    •	Remove a patron from the system (DELETE /api/patrons/{id})

* #### Borrowing Management:

    •	Allow a patron to borrow a book (POST /api/borrow/{bookId}/patron/{patronId})
    
    •	Record the return of a borrowed book by a patron (PUT /api/return/{bookId}/patron/{patronId})

##### Data Storage
•	A relational database (MySQL) is used to persist book, patron, and borrowing record details.

•	Proper relationships between entities.

##### Validation and Error Handling
•	Input validation for API requests

•	exception handling with appropriate HTTP status codes and error message

##### Transaction Management:
•	Declarative transaction management using Spring's @Transactional annotation

##### Caching:
● Utilize Spring to cache frequently accessed data, such as book details or patron information, to improve system performance.

##### Testing:
•  Unit tests are added to each Controller. You can run these tests using your IDE or from the command line:
```sh
mvn test
```

## Setting Up the Project
•	Java 22

•	Maven

•	A database (MySQL)

The widely used three-layer architecture in this Spring Boot application is implemented:
1.	The Controller Layer, which is responsible for defining all the REST API endpoints, and it injects services into the controller classes.
2.	The Service Layer, where we manage all the business logic, and it injects repositories into service classes.
3.	The Repository Layer, which is responsible for communication with the database, and all database transactions are managed by Hibernate, which is an implementation of Spring Data JPA."

### Running the Application
1.	Clone the repository:
```sh
git clone https://github.com/tassneam/libraryManagementSystem.git
```
2.	Configure the database:

Create a mySQL database called “libraryManagementSystemDB” or configure your own database but make sure to update the “application.properties” file configuration.

3.	Run the Application.

### Postman Testing
Provided a postman.json file (Library Management System.postman_collection.json), import it into postman to access and test the application endpoints.

<p align="right">(<a href="#readme-top">back to top</a>)</p>




