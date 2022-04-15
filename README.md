# Company In Spring

<a href="https://www.oracle.com/java/technologies/downloads/#java8-windows"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8 321-orange.svg"/></a>
<a href="https://docs.spring.io/spring-boot/docs/2.6.6/reference/html/"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-2.6.6.RELEASE-brightgreen.svg"/></a>
## How it works

The application uses Spring Boot (Web).

The code is organized as this:

1. `controller` is the web layer implemented by Spring RestAPI
2. `service` is the business model including services
3. `entity` is the model including entities
5. `repository` 
4. `exceptions` contains runtime exception classes as the technique details

## Environment

- **JDK 1.8**
- **IntelliJ IDEA ULTIMATE 2022.1 +** (*Note: Please use IDEA and make sure plugin `lombok` installed.*)

### Database
- ***Mysql 8.0***
  It uses a MySQL database, can be changed easily in the `application.properties` for any other database.

## Getting started

You'll need Java 8 installed.

To test that it works, open a browser tab at http://localhost:8080/employees.  
Alternatively, you can run

    curl http://localhost:8080/employees

## URL s
Get all employees
- ****http://localhost:8080/employees****

Get employees by id
- ****http://localhost:8080/employees/1****  (*GET mapping: localhost:8080/employees/{id}*)

Add employee
- ****http://localhost:8080/employees/add**** (*POST mapping*)

Update by id employee
- ****http://localhost:8080/employees/edit/1**** (*PUT mapping: localhost:8080/employees/edit/{id}*)

Delete employee by id
- ****http://localhost:8080/employees/1**** (*DELETE mapping: localhost:8080/employees/{id}*)

Get attendance records by date time and department name
- ****http://localhost:8080/records/2022-04-15 00:00:00/IT****  (*Note: Date time pattern is the `yyyy-MM-dd HH:mm:ss`. *GET mapping: localhost:8080/records/{dateTime}/{name}*)