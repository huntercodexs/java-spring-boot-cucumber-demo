# language: en

  @BackgroundUser
  Feature: User endpoint validation

    Background: User setup in database
      Given the first user is added into database using username "john" and password "1234"

    @InitialValidation
    Scenario: First fields validation for get users endpoint
      Given users endpoint is accessed using get
      And the result is 200 successful http response
      And the http response has all expected fields for get request

    @FinalValidation
    Scenario: Second fields validation for get users endpoint
      Given database is accessed
      Given users endpoint is accessed using get
      And the result is 200 successful http response
      And the response is validated again from get users endpoint request

