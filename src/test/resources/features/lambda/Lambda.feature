# language: en

  @LambdaTag
  Feature: Lambda

    Scenario: Test different types of data structure

        # List<String> table
      Given list test
        | cow   |
        | horse |
        | sheep |
        | dog   |
        # List<List<String>> table
      And list list test
        | pen    | notebook | car   |
        | ball   | plate    | money |
        | pencil | gun      | shoes |
        # List<Map<String, String>> table
      And list map test
        | fruit  | color  | quantity |
        | apple  | red    | 1        |
        | banana | yellow | 3        |
        | grape  | lilac  | 10       |
        | lime   | green  | 8        |
        # Map<String, String> table
      And map test
        | cod_1000 | one thousand   |
        | cod_2000 | two thousand   |
        | cod_3000 | three thousand |
        # Map<String, List<String>> table
      And map list test
        | suv   | HRV     | BMW X6   | Renegade |
        | hatch | Onix    | HB20     | Focus    |
        | sedan | Corolla | Mercedes | Versa    |
        # Map<String, Map<String, String>> table
      And map map test
        |             | Start      | End        |
        | world war 1 | 10-06-1800 | 10-02-1900 |
        | world war 2 | 11-00-1900 | 10-04-1950 |

      And with the following template string definition
        """
        Test Template String Java
        """