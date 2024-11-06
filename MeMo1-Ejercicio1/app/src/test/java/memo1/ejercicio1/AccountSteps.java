package memo1.ejercicio1;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class AccountSteps {
    private BankSystem system = BankSystem.getInstance();;
    private Long cbu;
    private String alias;
    private Account account;
    private Account senderAccount;
    private Account receiverAccount;
    private boolean operationResult;

    @Given("An account with CBU {long}, alias {string}, with client of DNI {int} as owner and created by branch {int}")
    public void createAccountWithDefaultBalance(long cbu, String alias, int clientDni, int branchNumber) {
        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(cbu, alias, clientDni);
        account = system.getAccount(cbu);
    }

    @Given("An account with CBU {long}, alias {string}, balance of {double}, with client of DNI {int} as owner and created by branch {int}")
    public void createAccountWithInitialBalance(long cbu, String alias, double balance, int clientDni, int branchNumber) {
        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(cbu, alias, balance, clientDni);
        account = system.getAccount(cbu);
    }

    @Given("A sender account with CBU {long}, alias {string}, balance of {double}, with client of DNI {int} as owner and created by branch {int}")
    public void createSenderAccountWithInitialBalance(long cbu, String alias, double balance, int clientDni, int branchNumber) {
        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(cbu, alias, balance, clientDni);
        senderAccount = system.getAccount(cbu);
    }

    @Given("A receiver account with CBU {long}, alias {string}, balance of {double}, with client of DNI {int} as owner and created by branch {int}")
    public void createReceiverAccountWithInitialBalance(long cbu, String alias, double balance, int clientDni, int branchNumber) {
        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(cbu, alias, balance, clientDni);
        receiverAccount = system.getAccount(cbu);
    }

    @Given("A non-existent account CBU like {long}")
    public void setNonExistentAccountCbu(Long nonExistingAccountCbu) {
        cbu = nonExistingAccountCbu;
    }

    @Given("A non-existent account alias like {string}")
    public void setNonExistentAccountAlias(String nonExistingAccountAlias) {
        alias = nonExistingAccountAlias;
    }

    @Given("The same account as receiver")
    public void theSameAccountAsReceiving() {
        receiverAccount = senderAccount;
    }

    @When("I try to create an account with CBU {long}, alias {string}, with client of DNI {int} as owner and created by branch {int}")
    public void tryToCreateAccountWithInitialBalance(long cbu, String alias, int clientDni, int branchNumber) {        
        try {
            Branch branch = system.getBranch(branchNumber);
            branch.createAccount(cbu, alias, clientDni);
            account = system.getAccount(cbu);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to create an account with CBU {long}, alias {string}, balance of {double}, with client of DNI {int} as owner and created by branch {int}")
    public void tryToCreateAccountWithInitialBalance(long cbu, String alias, double amount, int clientDni, int branchNumber) {        
        try {
            Branch branch = system.getBranch(branchNumber);
            branch.createAccount(cbu, alias, amount, clientDni);
            account = system.getAccount(cbu);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I deposit {double} into the account through CBU")
    public void depositIntoAccountThroughCbu(Double amount) {
        system.deposit(account.getCbu(), amount);
    }

    @When("I deposit {double} into the account through alias")
    public void depositIntoAccountThroughAlias(Double amount) {
        system.deposit(account.getAlias(), amount);
    }

    @When("I deposit {double} into the account, on date {string}, at hour {string}")
    public void depositIntoAccountWithDateAndHour(Double amount, String dateString, String hourString) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatterDate);
        LocalTime hour = LocalTime.parse(hourString, DateTimeFormatter.ofPattern("HH:mm:ss"));

        system.deposit(date, hour, account.getCbu(), amount);
    }

    @When("I try to deposit {double} into the account")
    public void tryToDepositIntoAccount(double amount) {
        try {
            system.deposit(cbu, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to deposit {double} into the non-existent account through CBU")
    public void tryToDepositIntoNonExistingAccountThroughCbu(double amount) {
        try {
            system.deposit(cbu, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to deposit {double} into the non-existent account through alias")
    public void tryToDepositIntoNonExistingAccountThroughAlias(double amount) {
        try {
            system.deposit(alias, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I withdraw {double} from the account through CBU, with DNI {int}")
    public void withdrawFromAccountThroughCbu(double amount, int dni) {
        system.withdraw(dni, account.getCbu(), amount);
    }

    @When("I withdraw {double} from the account through alias, with DNI {int}")
    public void withdrawFromAccountThroughAlias(double amount, int dni) {
        system.withdraw(dni, account.getAlias(), amount);
    }

    @When("I withdraw {double} from the account, with DNI {int}, on date {string}, at hour {string}")
    public void withdrwaFromAccountWithDateAndHour(Double amount, int dni, String dateString, String hourString) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatterDate);
        LocalTime hour = LocalTime.parse(hourString, DateTimeFormatter.ofPattern("HH:mm:ss"));

        system.withdraw(date, hour, dni, account.getCbu(), amount);
    }

    @When("I try to withdraw {double} from the account")
    public void tryToWithdrawFromAccount(double amount) {
        try {
            // system.withdraw(111 ,account.getCbu(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to withdraw {double} from the account through CBU, with DNI {int}")
    public void tryToWithdrawFromAccountThroughCbu(double amount, int dni) {
        try {
            system.withdraw(dni, account.getCbu(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to withdraw {double} from the account through alias, with DNI {int}")
    public void tryToWithdrawFromAccountThroughAlias(double amount, int dni) {
        try {
            system.withdraw(dni, account.getAlias(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to withdraw {double} from the account, with DNI {int}")
    public void tryToWithdrawFromAccount(double amount, int dni) {
        try {
            system.withdraw(dni, account.getAlias(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to withdraw {double} from the non-existent account through CBU, with DNI {int}")
    public void tryToWithdrawIntoNonExistingAccountThroughCbu(double amount, int dni) {
        try {
            system.withdraw(dni, cbu, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to withdraw {double} from the non-existent account through alias, with DNI {int}")
    public void tryToWithdrawIntoNonExistingAccountThroughAlias(double amount, int dni) {
        try {
            system.withdraw(dni, alias, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I transfer {double} to the other account through CBU, with DNI {int}")
    public void transferToOtherAccountThroughCbu(Double amount, int dni) {
        system.transfer(dni, senderAccount.getCbu(), receiverAccount.getCbu(), amount);
    }

    @When("I transfer {double} to the other account through alias, with DNI {int}")
    public void transferToOtherAccountThroughAlias(Double amount, int dni) {
        system.transfer(dni, senderAccount.getAlias(), receiverAccount.getAlias(), amount);
    }
    
    @When("I transfer {double} to the other account through CBU, with DNI {int}, on date {string}, at hour {string}")
    public void transferToOtherAccountWithDateAndHourWithCbu(Double amount, int dni, String dateString, String hourString) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatterDate);
        LocalTime hour = LocalTime.parse(hourString, DateTimeFormatter.ofPattern("HH:mm:ss"));

        system.transfer(date, hour, dni, senderAccount.getCbu(), receiverAccount.getCbu(), amount);
    }

    @When("I transfer {double} to the other account through alias, with DNI {int}, on date {string}, at hour {string}")
    public void transferToOtherAccountWithDateAndHourWithAlias(Double amount, int dni, String dateString, String hourString) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatterDate);
        LocalTime hour = LocalTime.parse(hourString, DateTimeFormatter.ofPattern("HH:mm:ss"));

        system.transfer(date, hour, dni, senderAccount.getAlias(), receiverAccount.getAlias(), amount);
    }

    @When("I try to transfer {double} to the other account through CBU, with DNI {int}")
    public void tryToTransferToOtherAccountThroughCbu(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getCbu(), receiverAccount.getCbu(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} to the other account through alias, with DNI {int}")
    public void tryToTransferToOtherAccountThroughAlias(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getAlias(), senderAccount.getAlias(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} to the other account, with DNI {int}")
    public void tryToTransferToOtherAccount(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getCbu(), receiverAccount.getCbu(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} to the same account through CBU, with DNI {int}")
    public void tryToTransferToSameAccountThroughCbu(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getCbu(), senderAccount.getCbu(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} to the same account through alias, with DNI {int}")
    public void tryToTransferToSameAccountThroughAlias(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getAlias(), receiverAccount.getAlias(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }    
    }

    @When("I try to transfer {double} from the non-existent account through CBU, with DNI {int}")
    public void tryToTransferFromNonExistentAccountThroughCbu(Double amount, int dni) {
        try {
            system.transfer(dni, cbu, senderAccount.getCbu(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} to the non-existent account through CBU, with DNI {int}")
    public void tryToTransferToNonExistentAccountThroughCbu(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getCbu(), cbu, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} from the non-existent account through alias, with DNI {int}")
    public void tryToTransferFromNonExistentAccountThroughAlias(Double amount, int dni) {
        try {
            system.transfer(dni, alias, senderAccount.getAlias(), amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I try to transfer {double} to the non-existent account through alias, with DNI {int}")
    public void tryToTransferToNonExistentAccountThroughAlias(Double amount, int dni) {
        try {
            system.transfer(dni, senderAccount.getAlias(), alias, amount);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I set the client with DNI {int} as co-owner")
    public void setClientAsCoOwner(int dni) {        
        account.setNewCoOwner(system.getClient(dni));
    }

    @When("I try to set the client with DNI {int} as co-owner")
    public void tryToSetClientAsCoOwner(int dni) {        
        try {
            account.setNewCoOwner(system.getClient(dni));
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I remove the co-ownership of account with DNI {int}")
    public void removeClientAsCoOwner(int dni) {        
        account.removeCoOwner(dni);
    }

    @When("I try to remove the co-ownership of account with DNI {int}")
    public void tryToRemoveClientAsCoOwner(int dni) {        
        try {
            account.removeCoOwner(dni);
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
    }

    @When("I delete the account")
    public void deleteAccount() {        
        Branch branch = system.getBranch(account.getBranch());
        branch.deleteAccount(account.getCbu());
    }

    
    @When("I try to delete the account")
    public void tryToDeleteAccount() {       
        try {
            Branch branch = system.getBranch(account.getBranch());
            branch.deleteAccount(account.getCbu());
            operationResult = true;
        } catch(Exception e) {
            operationResult = false;
        }
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

    @Then("The sender account balance should be {double}")
    public void verifyReceiverBalance(double expectedBalance) {
        assertEquals(expectedBalance, senderAccount.getBalance(), 0.01);
    }

    @Then("The receiver account balance should be {double}")
    public void verifySenderBalance(double expectedBalance) {
        assertEquals(expectedBalance, receiverAccount.getBalance(), 0.01);
    }

    @Then("The sender account balance should remain {double}")
    public void verifyReceiverBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, senderAccount.getBalance(), 0.01);
    }

    @Then("The receiver account balance should remain {double}")
    public void verifySenderBalanceRemains(double expectedBalance) {
        assertEquals(expectedBalance, receiverAccount.getBalance(), 0.01);
    }

    @Then("The client with DNI {int} should be the owner of the account")
    public void clientIsOwnerOfAccount(int dni) {
        assertTrue(account.isOwner(dni));
    }

    @Then("The client with DNI {int} should be a co-owner of the account")
    public void clientIsCoOwnerOfAccount(int dni) {
        assertTrue(account.isCoOwner(dni));
    }

    @Then("The account should have {int} co-owner\\/s")
    public void accountShouldHaveNumberOfCoOwners(int expectedNumber) {
        assertEquals(expectedNumber, account.getNumberOfCoOwners());
    }

    @Then("The account should not be found anymore")
    public void verifyAccountNotFoundAnymore() {
        assertThrows( IllegalArgumentException.class, () -> system.getAccount( account.getCbu() ) );
    }

    @After
    public void clearEntireSystem(){
        BankSystem.getInstance().clearEntireSystem();
    }
}
