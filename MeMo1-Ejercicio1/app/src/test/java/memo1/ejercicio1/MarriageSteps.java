package memo1.ejercicio1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MarriageSteps {
    private BankSystem system = BankSystem.getInstance();
    private boolean operationResult;
    private LocalDate dateOfMarriage;

    @Given("A new marriage on date {string} between DNI {int} and DNI {int}")
    public void newMarriage(String dateOfMarriageString, int spouse1Dni, int spouse2Dni) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateOfMarriage = LocalDate.parse(dateOfMarriageString, formatter);

        system.newMarriage(dateOfMarriage, spouse1Dni, spouse2Dni);
    }

    @When("I register a new marriage on date {string} between DNI {int} and DNI {int}")
    public void registerNewMarriage(String dateOfMarriageString, int spouse1Dni, int spouse2Dni) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateOfMarriage = LocalDate.parse(dateOfMarriageString, formatter);
        
        system.newMarriage(dateOfMarriage, spouse1Dni, spouse2Dni);
    }

    @When("I try to register a new marriage on date {string} between DNI {int} and DNI {int}")
    public void tryToRegisterNewMarriage(String dateOfMarriageString, int spouse1Dni, int spouse2Dni) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateOfMarriage = LocalDate.parse(dateOfMarriageString, formatter);

        try {
            system.newMarriage(dateOfMarriage, spouse1Dni, spouse2Dni);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I delete the marriage of DNI {int}")
    public void deleteMarriage(int dni) {
        system.deleteMarriage(dni);
    }

    @Then("The date of the marriage should be {string}")
    public void checkCorrectDateOfMarriage(String expectedDateOfMarriageString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expectedDateOfMarriage = LocalDate.parse(expectedDateOfMarriageString, formatter);

        assertEquals( expectedDateOfMarriage, dateOfMarriage );
    }
    

    @Then("The client with DNI {int} should be married to the client with DNI {int}")
    public void checkCorrectSpouseOfClient(int client1Dni, int client2Dni) {
        assertTrue(system.isMarried(client1Dni));
        assertEquals(client2Dni, system.getSpouseOfMarriedClientDni(client1Dni));
    }

    @Then("The client with DNI {int} should not be married to the client with DNI {int}")
    public void checkClientNotMarriedWithOtherSpecifiedClient(int client1Dni, int client2Dni) {
        assertFalse(system.isMarried(client1Dni));
                assertThrows(IllegalArgumentException.class, () -> system.getSpouseOfMarriedClientDni(client1Dni) );

    }

    @Then("The client with DNI {int} should be married")
    public void CheckThatClientIsMarried(int clientDni) {
        assertTrue(system.isMarried(clientDni));
    }

    @Then("The client with DNI {int} should not be married")
    public void CheckThatClientIsNotMarried(int clientDni) {
        assertFalse(system.isMarried(clientDni));
    }
   
    @Then("The operation should be denied due to non-existent spouse")
    public void verifyOperationResultWhenSpouseNotExisting() {
        assertFalse(operationResult);
    }

    @Then("The operation should be denied due to a client involved already being married")
    public void verifyOperationResultWhenOneClientIsAlreadyMarried() {
        assertFalse(operationResult);
    }

    @After
    public void clearEntireSystem(){
        BankSystem.getInstance().clearEntireSystem();
    }
}
