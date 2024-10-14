# language: en

  Feature: For List Example

    @Things
    Scenario: Testing List
      # List<String> table
      Given the following animals:
        | cow   |
        | horse |
        | sheep |
        | dog   |
      # List<List<String>> table
      And the following objects:
        | pen    | notebook | car   |
        | ball   | plate    | money |
        | pencil | gun      | shoes |
      # List<Map<String, String>> table
      And the following fruits:
        | fruit  | color  | quantity |
        | apple  | red    | 1        |
        | banana | yellow | 3        |
        | grape  | lilac  | 10       |
        | lime   | green  | 8        |
      # Map<String, String> table
      And the following numbers:
        | cod_1000 | one thousand   |
        | cod_2000 | two thousand   |
        | cod_3000 | three thousand |
      # Map<String, List<String>> table
      And the following cars:
        | suv   | HRV     | BMW X6   | Renegade |
        | hatch | Onix    | HB20     | Focus    |
        | sedan | Corolla | Mercedes | Versa    |
      # Map<String, Map<String, String>> table
      And the following dates:
        |             | Start      | End        |
        | world war 1 | 10-06-1800 | 10-02-1900 |
        | world war 2 | 11-00-1900 | 10-04-1950 |
      Then show all data together
