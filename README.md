# CSV Reader service

A web service to work with CSV via HTTP REST API.The service stores all the data in CSV file with column names 
with other works such as finding records with code and delete all data


### Conditions
* The CSV file, should have at least 1 valid row for insert and have 
* A Z-index is a unique sequence common to all widgets that
  determines the order of widgets (regardless of their coordinates).
  Gaps are allowed. The higher the value, the higher the widget
  lies on the plane.
    
### Considerations  
* Separating entity from request/response model with  MapStruct

### Run
`$ mvn spring-boot:run`  Runs the application with in Memory storage profile

The API guide could be found http://127.0.0.1:8080/swagger-ui.html
  
### Technologies  
    Java 17
    Spring boot & Rest
    Junit5
    Mockito And MockMVC
    Apache commons
    Lombok
    MapStruct
    Swagger
    Log4j2

