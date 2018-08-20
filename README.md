
The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC with Spring Boot
* Database H2 (In-Memory)
* Maven

    Conventions used are :

 * All new entities have an ID with type of Long and a date_created with type of ZonedDateTime.
 * The architecture of the web service is built with the following components:
   * DataTransferObjects: Objects which are used for outside communication via the API
   * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
   * Service: Implements the business logic and handles the access to the DataAccessObjects.
   * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
   * DomainObjects: Functional Objects which might be persisted in the database.
 * TestDrivenDevelopment is a good choice, but it's up to you how you are testing your code.

## Task 1
### Car Controller
   * CRUD opertions on entity Car.

### Driver Controller
 * CRUD opertions on entity Driver.
 * Drivers can be mapped to a car.
 * Drivers can be removed from a car.

 ```
 Endpoints :
 mapDriverToCar => POST -> /v1/drivers/{driverId}/{carId}
 Parameters - driverId, carId
 Exception class : CarAlreadyInUseException ,DriverMappedToACarException, EntityNotFoundException

 removeDriverToCarMap => DELETE -> /v1/drivers/{driverId}/{carId}
 Parameters - driverId, carId
 Exception class : CarAlreadyInUseException , EntityNotFoundException

 findDriversByStatus => GET -> /v1/drivers/
 Parameters - onlineStatus

 updateDriverLocation => PUT -> /{driverId}
 Parameters - driverId, longitude, latitude

 updateDriverStatus =>  POST /{driverId}
 Parameters - driverId, onlineStatus

 ```

## Task 2
First come first serve: A car can be selected by exactly one ONLINE Driver.
Ans: If a second driver tries to select a already used car through DriverController.mapDriverToCaryou it throws a CarAlreadyInUseException.

## Task 3
Making use of the filter pattern to implement an endpoint in the DriverController to get a list of drivers with specific characteristics.
Ans: Used filter pattern and implemented an endpoint in the DriverController to get a list of drivers with specific search criteria.
 ```
 Endpoints :
 searchDrivers => GET -> /v1/drivers/search/{searchValue}
 Parameters - searchType, searchParameter, searchValue

 searchType is an ENUM having values => Should be either SearchType.CAR or SearchType.DRIVER.
 searchParameter is an ENUM having values => Should be either of the following : SearchParameters.CAR_MANUFACTURER, SearchParameters.CAR_RATING, SearchParameters.CAR_LICENSENO, SearchParameters.CAR_CARTYPE, SearchParameters.CAR_TOTALSEAT, SearchParameters.DRIVER_ONLINESTATUS, SearchParameters.DRIVER_USERNAME
 searchValue can be any valid searchValue present with respect to searchParameter selected.
 Exmple : searchType = CAR, searchParameter = SearchParameters.CAR_CARTYPE, searchValue = ELECTRIC
 Exception class : NoResultFoundException, InvalidSearchCriteria

 ```
## Securing the API
Security: secure the API. It's up to you how you are going to implement the security.
 ```
 It is not implemented. Currently in my office project we are using JWT token to implement security.

 ```

## Run unit tests :
```
./mvnw clean verify
```

## Run the application :
```
./mvnw spring-boot:run
```
