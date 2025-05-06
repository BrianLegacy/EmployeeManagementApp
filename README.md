Employee Management System (EMS)
A Jakarta EE 10 web application with PrimeFaces 13 for performing CRUD operations on employee records.

Features
Create, Read, Update, Delete employee records

Pagination for large datasets

Modern UI with PrimeFaces components

MySQL Database integration

Responsive Design works on desktop and mobile

Prerequisites
Java 17+

Jakarta EE 10 compatible server (GlassFish 7/Payara 6)

MySQL 8.0+

Maven 3.8+

NetBeans/Eclipse IDE (optional)

Database Setup
1. Create Database and User
sql
CREATE DATABASE employeedb;
USE employeedb;

CREATE USER 'ems_user'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON employeedb.* TO 'ems_user'@'localhost';
FLUSH PRIVILEGES;
2. Create Employees Table
sql
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    department VARCHAR(255),
    salary DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
3. Sample Data (Optional)
sql
INSERT INTO employees (first_name, last_name, email, department, salary) VALUES
('John', 'Doe', 'john.doe@company.com', 'Engineering', 75000.00),
('Jane', 'Smith', 'jane.smith@company.com', 'Marketing', 65000.00),
('Robert', 'Johnson', 'robert.j@company.com', 'HR', 60000.00);
Installation
Clone the repository:

bash
git clone https://github.com/yourusername/employee-management.git
cd employee-management
Configure database connection:

Edit src/main/resources/META-INF/persistence.xml

Update JDBC URL, username and password

Build and deploy:

bash
mvn clean package
asadmin deploy target/emapp.war
Application Structure
src/
├── main/
│   ├── java/
│   │   └── com/brian/emapp/
│   │       ├── controller/EmployeeController.java
│   │       ├── model/Employee.java
│   │       └── service/EmployeeService.java
│   ├── resources/
│   │   └── META-INF/persistence.xml
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml
│       │   └── beans.xml
│       └── index.xhtml
Key Technologies
Frontend: PrimeFaces 13, Jakarta Faces

Backend: Jakarta EE 10 (CDI, JPA, EJB)

Database: MySQL with JDBC

Build: Maven

Usage
Access the application at:

http://localhost:8080/emapp/
Operations:

Add Employee: Click "Add Employee" button

Edit: Click "Edit" button on a record

Delete: Select a record and click "Delete Selected"

Pagination: Use controls at table bottom

Troubleshooting
Common Issues
Database Connection Failed:

Verify MySQL is running

Check credentials in persistence.xml

Test connection with:

bash
mysql -u ems_user -p employeedb
GlassFish Deployment Errors:

Clean GlassFish domain:

bash
asadmin stop-domain
rm -rf glassfish7/glassfish/domains/domain1/osgi-cache/
asadmin start-domain
PrimeFaces Components Not Rendering:

Check web.xml for proper servlet mapping

Verify PrimeFaces dependency in pom.xml

License
MIT License - Free for educational and commercial use

