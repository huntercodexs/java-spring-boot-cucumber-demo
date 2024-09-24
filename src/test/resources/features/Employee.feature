# language: en

  Feature: Employee Handling

    Scenario: Create employee using all required fields

      Given user wants to create an employee with the following attributes
        | id | firstName | lastName | dateOfBirth | startDate  | employmentType | email                |
        |  1 | John      | Smith    | 2002-05-10  | 2010-01-01 | Contract       | john.smith@email.com |

      And with the following address information
        | id | street          | city          | number | state    |
        | 1  | Street One Test | New York City | 100    |          |
        | 2  | Street Two Test | Springfield   | 200    | Ohio     |

      When user saves the new employee 'Using all required fields'
      Then the operation is 'success' and the message is ''

    #---------------------------------------------------------------------------------------------------

    Scenario: Create employee omiting firstName in the attributes

      Given user wants to create an employee with the following attributes
        | id | firstName | lastName | dateOfBirth | startDate  | employmentType | email                |
        |  1 |           | Smith    | 2002-05-10  | 2010-01-01 | Contract       | john.smith@email.com |

      And with the following address information
        | id | street          | city          | number | state    |
        | 1  | Street One Test | New York City | 100    |          |
        | 2  | Street Two Test | Springfield   | 200    | Ohio     |

      When user saves the new employee 'Forgot the firstName field'
      Then the operation is 'failure' and the message is 'create.employeeDTO.firstName: must not be empty'

