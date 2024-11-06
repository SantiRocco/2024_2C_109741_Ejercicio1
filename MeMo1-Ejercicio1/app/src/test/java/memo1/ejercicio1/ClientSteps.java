package memo1.ejercicio1;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class ClientSteps {
    private BankSystem system = BankSystem.getInstance();
    private int dni;
    private Client client;
    private boolean operationResult;

    @Given("A client with DNI {int}, surname {string}, name {string}, born on {string} and with address {string}")
    public void createClient(int dni, String surname, String name, String birthDateString, String address) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        system.addClient(dni, surname, name, birthDate, address);

        client = system.getClient(dni);
    }

    @Given("A client with DNI {int}, surname {string}, name {string}, born on {string} and with address {string} as a co-owner of the account with CBU {long}, created by branch {int}")
    public void createClient(int dni, String surname, String name, String birthDateString, String address, Long cbu, int branchNumber) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        system.addClient(dni, surname, name, birthDate, address);

        Account account = system.getAccount(cbu);
        account.setNewCoOwner(system.getClient(dni));

        client = system.getClient(dni);
    }

    @Given("A non-existent client DNI like {int}")
    public void nonExistentClientDni(int nonExistingClientDni) {
        dni = nonExistingClientDni;
    }
    
    @When("I try to create a new client with DNI {int}, surname {string}, name {string}, born on {string} and with address {string}")
    public void tryToCreateClient(int dni, String surname, String name, String birthDateString, String address) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        
        try {
            system.addClient(dni, surname, name, birthDate, address);
            client = system.getClient(dni);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I change the name of the client to {string}")
    public void changeNameOfClient(String newName) {
        client.setName(newName);
    }

    @When("I change the surname of the client to {string}")
    public void changeSurnameOfClient(String newSurname) {
        client.setSurname(newSurname);
    }

    @When("I change the address of the client to {string}")
    public void changeAddressOfClient(String newSurname) {
        client.setAddress(newSurname);
    }

    @When("I delete the client")
    public void deleteClient() {
        system.deleteClient(client.getDni());
    }

    @When("I try to delete the client")
    public void tryToDeleteClient() {
        try {
            system.deleteClient(client.getDni());
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to delete the non-existent client")
    public void tryToDeleteNonExistentClient() {
        try {
            system.deleteClient(dni);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @Then("The surname of the client should be {string}")
    public void checkCorrectSurname(String surname) {
        assertEquals(surname, client.getSurname());
    }

    @Then("The name of the client should be {string}")
    public void checkCorrectName(String name) {
        assertEquals(name, client.getName());
    }

    @Then("The birth date of the client should be {string}")
    public void checkCorrectBirthDate(String birthDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        assertEquals(birthDate, client.getBirthDate());
    }

    @Then("The address of the client should be {string}")
    public void checkCorrectAddress(String address) {
        assertEquals(address, client.getAddress());
    }

    @Then("The operation should be denied due to the client already being registered in the system")
    public void verifyOperationDeniedDueToAlreadyRegisteredClient() {
        assertFalse(operationResult);
    }

    @Then("The operation should be denied due to the client having associated accounts")
    public void verifyOperationDeniedDueToClientHavingAssociatedAccounts() {
        assertFalse(operationResult);
        assertTrue(client.getNumberOfRelatedAccounts() > 0);
    }

    @Then("The operation should be denied due to the client not existing")
    public void verifyOperationDeniedDueToNonExistentClient() {
        assertFalse(operationResult);
    }

    @Then("The client with DNI {int} should have {int} related account\\/s")
    public void clientShouldHaveNumberOfRelatedAccounts(int dni, int relatedAccounts) {
        assertEquals(relatedAccounts, system.getClient(dni).getNumberOfRelatedAccounts());
    }

    @Then("The client with DNI {int} should not be found anymore")
    public void clientShouldNotBeFoundAnymore(int dni) {
        assertThrows(IllegalArgumentException.class, () -> system.getClient(dni) );
    }

    @After
    public void clearEntireSystem(){
        BankSystem.getInstance().clearEntireSystem();
    }
}
