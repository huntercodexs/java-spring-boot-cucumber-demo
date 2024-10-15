package codexstester.bdd.stepsdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;
import java.util.Map;

public class ListSteps {

    @Given("the following animals:")
    public void theFollowingAnimals(List<String> table) {
        System.out.println("=====> Animals");
        for (String animal : table) {
            System.out.println(animal);
        }
    }

    @And("the following objects:")
    public void theFollowingObjects(List<List<String>> table) {
        System.out.println("=====> Objects");
        for (List<String> object : table) {
            System.out.println(object);
        }
    }

    @And("the following fruits:")
    public void theFollowingFruits(List<Map<String, String>> table) {
        System.out.println("=====> Fruits");
        for (Map<String, String> fruits : table) {
            System.out.println(fruits);
        }
    }

    @And("the following numbers:")
    public void theFollowingNumbers(Map<String, String> table) {
        System.out.println("=====> Numbers");
        table.forEach((k,v)->{
            System.out.println(k+ ": "+v);
        });
    }

    @And("the following cars:")
    public void theFollowingCars(Map<String, List<String>> table) {
        System.out.println("=====> Cars");
        table.forEach((k,v)->{
            System.out.println(k+ ": "+v);
        });
    }

    @And("the following dates:")
    public void theFollowingNames(Map<String, Map<String, String>> table) {
        System.out.println("=====> Dates");
        table.forEach((k,v)->{
            System.out.println(k+ ": "+v);
        });
    }

    @Then("show all data together")
    public void showAllDataTogether() {
        System.out.println("=====> Final");
    }
}
