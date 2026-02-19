
What I Have Tested:
-GET/POST for adding rating
-GET/POST for editing rating
-GET for index page (fetching data for summary list)
-GET for detail page
-Validation for inputs (e.g. invalid email format, missing name, missing email, etc.)
-GET for deletion confirmation and POST for deletion of rating
-Database Persistence (adding and deleting ratings in database)

To run tests (in terminal):
mvn test


Why Spring Boot Test?
-To test integration of postgressql database with application
-To test if validation works and controller returns the forms with errors
