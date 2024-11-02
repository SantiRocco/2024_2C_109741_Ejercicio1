package memo1.ejercicio1;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class AccountSteps {
    private Account account;
    private Account transferToAccount;
    private boolean operationResult;

    @Given("I create an account with CBU {long} and alias {string}")
    public void createAccountWithDefaultBalance(long cbu, String alias) {
        //account = new Account();
        //account.setCbu(cbu);
        //account.setAlias(alias);
    }

    @Given("I create an account with CBU {long}, alias {string} and a balance of {double}")
    public void createAccountWithInitialBalance(long cbu, String alias, double balance) {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");     
        account = new Account(cbu, alias, balance, client, 1);
    }

    @Given("An account with CBU {long}, alias {string} and a balance of {double}")
    public void anAccountWithCBUAndBalance(long cbu, String alias, double balance) {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");     
        account = new Account(cbu, alias, balance, client, 1);
    }

    @Given("A sender account with CBU {long}, alias {string} and a balance of {double}")
    public void aSenderAccountWithCBUAndBalance(long cbu, String alias, double balance) {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");     
        account = new Account(cbu, alias, balance, client, 1);
    }

    @Given("A receiver account with CBU {long}, alias {string} and a balance of {double}")
    public void aReceiverAccountWithCBUAndBalance(long cbu, String alias, double balance) {
                LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");     
        transferToAccount = new Account(cbu, alias, balance, client, 1);
    }

    @Given("A receiver account with no CBU and alias {string}")
    public void anAccountWithNoCBU(String alias) {
        //transferToAccount = new Account(null, alias);
    }

    @Given("A receiver account with CBU {long} and no alias")
    public void anAccountWithNoCBU(Long cbu) {
        //transferToAccount = new Account(cbu, null);
    }

    @Given("The same account as receiver")
    public void theSameAccountAsReceiving() {
        transferToAccount = account;
    }

    @When("I deposit {double} into the account")
    public void depositIntoAccount(double amount) {
        //operationResult = account.deposit(amount);
    }

    @When("I try to deposit {double} into the account")
    public void tryToDepositIntoAccount(double amount) {
        //operationResult = account.deposit(amount);
    }

    @When("I withdraw {double} from the account")
    public void withdrawFromAccount(double amount) {
        //operationResult = account.withdraw(amount);
    }

    @When("I try to withdraw {double} from the account")
    public void tryToWithdrawFromAccount(double amount) {
        //operationResult = account.withdraw(amount);
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
