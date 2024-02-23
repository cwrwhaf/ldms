## Design choices
I would expect these schedules to be attached to a policy but as the spec didn't ask for it i didn't assume it.
Built on an MVC model with the controller layer mapping from entities to dtos and the calculations separated off from the service layer for easy unit testing.
create schedule in service layer is transactional so we dont end up in a half state if anything fails

Used lombok to reduce boilerplate code, started using records but became pretty complicated very quickly

Most apis ive worked on return the object created on a post but the spec for post said to return location so thats what i went with

Used postman to test included the collection in the resources section

## Proposed Changes

### Logging

### Database structure
I wouldve liked the entities LoanDetails and PaymentSummary to exist as part of the Schedule table as a flat structure in the database but exist as separate entites in the application but i couldn't get it to work in the time.
I separated the entities to make the application easier to read
didnt see the need for seed data in the db but obviously would use flyway/liquibase to version the db

### Controllers
- Dto validation - non null values etc with meaningful error messages
- a global @ExceptionHandler with correct response codes for errors. Eg the applicationException would be a 404

### Converters
- null checking
- more reuse of the mappings

### Tests
- Integration tests on the Controllers with mockMvc (or rest assured? Never used) and dbunit so we can input real data 
and test the schema of the object returned and status codes for errors. (ive never seen the value of this until recently when we upgraded a boot version and the json returned had changed)
- unit tests for the converters to make sure the right value is converted

### Double
- there has to be a better data type than a double? theres a lot of rounding to decimal places, or possibly find a better place to round?