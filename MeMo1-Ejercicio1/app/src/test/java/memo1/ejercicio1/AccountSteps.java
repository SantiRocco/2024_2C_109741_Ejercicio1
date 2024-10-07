package memo1.ejercicio1;

import static org.junit.Assert.*;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class AccountSteps {
    private Account account;
    private Account transferToAccount;
    private boolean operationResult;

    @Given("I create an account with CBU {long}")
    public void createAccountWithDefaultBalance(long cbu) {
        account = new Account();
        account.setCbu(cbu);
    }

    @Given("I create an account with CBU {long} and a balance of {double}")
    public void createAccountWithInitialBalance(long cbu, double balance) {
        account = new Account(cbu, balance);
    }

    @Given("An account with CBU {long} and a balance of {double}")
    public void anAccountWithCBUAndBalance(long cbu, double balance) {
        account = new Account(cbu, balance);
    }

    @Given("A sender account with CBU {long} and a balance of {double}")
    public void aSenderAccountWithCBUAndBalance(long cbu, double balance) {
        account = new Account(cbu, balance);
    }

    @Given("A receiver account with CBU {long} and a balance of {double}")
    public void aReceiverAccountWithCBUAndBalance(long cbu, double balance) {
        transferToAccount = new Account(cbu, balance);
    }

    @Given("A receiver account with no CBU")
    public void anAccountWithNoCBU() {
        transferToAccount = new Account();
    }

    @Given("The same account as receiver")
    public void theSameAccountAsReceiving() {
        transferToAccount = new Account(account.getCbu(), account.getBalance());
    }

    @When("I deposit {double} into the account")
    public void depositIntoAccount(double amount) {
        operationResult = account.deposit(amount);
    }

    @When("I try to deposit {double} into the account")
    public void tryToDepositIntoAccount(double amount) {
        operationResult = account.deposit(amount);
    }

    @When("I withdraw {double} from the account")
    public void withdrawFromAccount(double amount) {
        operationResult = account.withdraw(amount);
    }

    @When("I try to withdraw {double} from the account")
    public void tryToWithdrawFromAccount(double amount) {
        operationResult = account.withdraw(amount);
    }

    @When("I transfer {double} into the other account")
    public void transferIntoSecondAccount(double amount) {
        operationResult = account.transfer(amount, transferToAccount);
    }

    @When("I transfer {double} into the same account")
    public void transferIntoSameAccount(double amount) {
        operationResult = account.transfer(amount, account);
    }


    @Then("The account balance should be {double}")
    public void verifyAccountBalance(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Then("The operation should be denied")
    public void verifyOperationDenied() {
        assertFalse(operationResult);
    }

    @Then("The account balance should remain {double}")
    public void verifyBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Then("The operation should be denied due to insufficient funds")
    public void verifyInsufficientFunds() {
        assertFalse(operationResult);
    }

    @Then("The receiver account balance should be {double}")
    public void verifySenderBalance(double expectedBalance) {
        assertEquals(expectedBalance, transferToAccount.getBalance(), 0.01);
    }

    @Then("The sender account balance should be {double}")
    public void verifyReceiverBalance(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

    @Then("The receiver account balance should remain {double}")
    public void verifySenderBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, transferToAccount.getBalance(), 0.01);
    }

    @Then("The sender account balance should remain {double}")
    public void verifyReceiverBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0.01);
    }

}
