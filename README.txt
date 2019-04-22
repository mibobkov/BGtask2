Running the application:
mvn package && java -jar task2-1.0-SNAPSHOT.jar 

Example requests:

curl -X POST -H "Content-type: application/json" -d "{"""name""":"""Alex""","""surname""":"""Gordon""","""dob""":"""19/05/94""","""email""":"""ag@gmail.com""", """password""":"""123123"""}" localhost:8080/add
curl -X DELETE -H "Content-type: application/json" -d "ag@gmail.com" localhost:8080/delete
curl -X GET -H "Content-type: application/json" -d "ag@gmail.com" localhost:8080/get

Easier to do the requests by using Postman:
https://www.getpostman.com/
