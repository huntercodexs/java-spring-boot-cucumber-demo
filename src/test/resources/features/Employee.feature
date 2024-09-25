# language: en

  Feature: Employee Handling

    Scenario: Create Employee using all required fields

      Given user wants to create an employee using the following attributes
        | id | firstName | lastName | dateOfBirth | startDate  | endDate | employmentType | email                |
        |  1 | John      | Smith    | 1988-08-30  | 2001-11-05 |         | Contract       | john.smith@email.com |

      And with the following address information
        | id | street          | city        | number | state |
        |  1 | Street One Test | New York    |    100 |       |
        |  2 | Street Two Test | Springfield |    200 | Ohio  |

      When user try to save the new employee 'using all required fields'
      Then the result is 'successful' and response is ''

    Scenario: Create Employee forgetting firstName

      Given user wants to create an employee using the following attributes
        | id | firstName | lastName | dateOfBirth | startDate  | endDate | employmentType | email                |
        |  1 |           | Smith    | 1988-08-30  | 2001-11-05 |         | Contract       | john.smith@email.com |

      And with the following address information
        | id | street          | city        | number | state |
        |  1 | Street One Test | New York    |    100 |       |
        |  2 | Street Two Test | Springfield |    200 | Ohio  |

      When user try to save the new employee 'using all required fields'
      Then the result is 'failure' and response is 'create.employeeDTO.firstName: must not be empty'