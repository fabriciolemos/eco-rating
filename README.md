# RValue Rating application

## Design decisions
The code is structure in the packages `application`, `adapter` and `model`, following DDD [convention](https://github.com/VaughnVernon/IDDD_Samples)

## Running the application
Run the main method on `com.eco.rating.Rating`.

## Building the application
You can use Maven to build the application with `mvn clean install`. As part of the build, the unit tests and the end to end tests are executed. Because it consumes more time, the load test is disabled by default and can be enabled by removing the `@Ignore` annotation from the test class.

## Load test
The load test is executed with the input of 1.000.000 users with one query for each user.

Test output on local laptop:
```
Process input operation - time taken: 4471 milliseconds
Process query operation - time taken: 3580 milliseconds
```
