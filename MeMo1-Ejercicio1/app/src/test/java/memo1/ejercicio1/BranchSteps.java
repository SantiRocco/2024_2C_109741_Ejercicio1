package memo1.ejercicio1;

import static org.junit.Assert.*;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class BranchSteps {
    private BankSystem system;
    private Branch branch;
    private int numberOfBranch;
    private boolean operationResult;

    @Given("A new branch with name {string} and address {string}")
    public void createBranchWithNameAndAddress(String name, String address) {
        system = BankSystem.getInstance();      
        numberOfBranch = system.createBranch(name, address);
        branch = system.getBranch(numberOfBranch);
    }

    @When("I change the name of the branch to {string}") 
    public void changeBranchName(String name){
        branch.changeName(name);
    }

    @When("I change the address of the branch to {string}") 
    public void changeBranchAddress(String address){
        branch.changeAddress(address);
    }

    @When("I deactivate the branch")
    public void deactivateBranch() {
        branch.inactivate();
        operationResult = true;
    }

    @When("I try to deactivate the branch")
    public void tryToDeactivateBranch() {
        try {
            branch.inactivate();
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @Then("The name of the branch should be {string}")
    public void verifyNameInitialization(String name) {
        assertEquals(name, branch.getName());
    }

    @Then("The address of the branch should be {string}")
    public void verifyAddressInitialization(String address) {
        assertEquals(address, branch.getAddress());
    }

    @Then("The number of branch should be {int}")
    public void verifyNumberOfBranchInitialization(int numberOfBranch) {
        assertEquals(numberOfBranch, branch.getNumberOfBranch());
    }

    @Then("The new name of the branch should be {string}")
    public void verifyNameChange(String name) {
        assertEquals(name, branch.getName());
    }

    @Then("The new address of the branch should be {string}")
    public void verifyAddressChange(String address) {
        assertEquals(address, branch.getAddress());
    }

    @Then("The branch should be inactive")
    public void verifyBranchDeletion() {
        assertTrue(operationResult);
        assertFalse(branch.isActive());
    }


    @Then("The operation should be denied due to it having associated accounts")
    public void verifyOperationDenied() {
        assertFalse(operationResult);
    }

    @After
    public void clearEntireSystem(){
        BankSystem.getInstance().clearEntireSystem();
    }
}
