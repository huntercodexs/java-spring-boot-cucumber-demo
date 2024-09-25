# language: en

  Feature: Employee Handling

    Scenario: Create Employee using all required fields

      Given user wants to create an employee with the following attributes
        | id | firstName | lastName | dateOfBirth | startDate  | endDate | employmentType | email                |
        |  1 | John      | Smith    | 1988-08-30  | 2001-11-05 |         | Contract       | john.smith@email.com |

      And with the following address information
        | id | street          | city        | number | state |
        |  1 | Street One Test | New York    |    100 |       |
        |  2 | Street Two Test | Springfield |    200 | Ohio  |

      When user saves the new employee 'WITH ALL REQUIRED FIELDS'
      Then the save 'IS SUCCESSFUL'