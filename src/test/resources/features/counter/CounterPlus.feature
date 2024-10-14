# language: en

  Feature: Counter from 3 to 0 to start any job

    Scenario: Backend calling to start a counter
      Given the clients needs to start one specific job
      When the client calls endpoint "/api/counter/" passing argument value "3"
      Then response status code is 200
      And return string should be "Three..."

    Scenario: Backend calling to start a counter
      Given the clients needs to start one specific job
      When the client calls endpoint "/api/counter/" passing argument value "2"
      Then response status code is 200
      And return string should be "Two..."

    Scenario: Backend calling to start a counter
      Given the clients needs to start one specific job
      When the client calls endpoint "/api/counter/" passing argument value "1"
      Then response status code is 200
      And return string should be "One..."

    Scenario: Backend calling to start a counter
      Given the clients needs to start one specific job
      When the client calls endpoint "/api/counter/" passing argument value "0"
      Then response status code is 200
      And return string should be "Go !"
