# language: en

  Feature: Counter from 3 to 0 to start any job

    @Counter
    Scenario Outline: BackendCallsCounter
      Given the clients needs to start one specific job
      When the client calls endpoint <endpoint> passing argument value <value>
      Then response status code is <statusCode>
      And return string should be <expected>

      Examples:
      | endpoint        | value | statusCode | expected   |
      | "/api/counter/" | "3"   |        200 | "Three..." |
      | "/api/counter/" | "2"   |        200 | "Two..."   |
      | "/api/counter/" | "1"   |        200 | "One..."   |
      | "/api/counter/" | "0"   |        200 | "Go !"     |

