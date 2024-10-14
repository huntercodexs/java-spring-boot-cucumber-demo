# language: en

  # comment: This Spec show how to use a cucumber + selenium to make tests on web applications
  Feature: User login test
    Perform user login on Practice Test Automation website

    @Login
    Scenario Outline: CheckLoginStatus
      Given user is on login page
      When user login with <username> and <password>
      Then login status should be <status>

      Examples:
      | username   | password      | status |
      | "student"  | "Password123" | true   |
      | "student"  | "Password444" | false  |
      | "stud3nt"  | "Password123" | false  |
