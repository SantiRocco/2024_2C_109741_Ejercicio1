package memo1.ejercicio1;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

// Pruebas funcionales basadas en los escenarios Gherkin

public class TransactionSteps {
    private BankSystem system = BankSystem.getInstance();

    @Then ("Transaction with id {int} should have {string} as date")
    public void checkCorrectDateCBUInCertainTransaction(int id, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        
        assertEquals(date, system.getTransaction(id).getDateOfOperation());
    }

    @Then ("Transaction with id {int} should have {string} as hour")
    public void checkCorrectHourInCertainTransaction(int id, String hourString) {
        LocalTime hour = LocalTime.parse(hourString, DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        assertEquals(hour, system.getTransaction(id).getHourOfOperation());
    }

    
    @Then ("Transaction with id {int} should have {string} as type of operation")
    public void checkCorrectTypeOfOperationCBUInCertainTransaction(int id, String typeOfOperationString) {
        TransactionType typeOfOperation = system.getTransaction(id).getTypeOfOperation();
        
        if (typeOfOperationString.equals("deposit")) {
            assertEquals(TransactionType.DEPOSIT, typeOfOperation);
        } else if (typeOfOperationString.equals("withdrawal")) {
            assertEquals(TransactionType.WITHDRAWAL, typeOfOperation);
        } else if (typeOfOperationString.equals("transfer")) {
            assertEquals(TransactionType.TRANSFER, typeOfOperation);
        } else {
            throw new IllegalArgumentException("Type of operation not passed correctly");
        }
    }

    @Then ("Transaction with id {int} should have {double} as amount")
    public void checkCorrectAmountInCertainTransaction(int id, Double amount) {
        assertEquals(amount, system.getTransaction(id).getAmount(), 0.01);
    }

    @Then ("Transaction with id {int} should have {long} as starter account CBU")
    public void checkCorrectStarterAccountCBUInCertainTransaction(int id, Long cbu) {
        assertEquals(cbu, system.getTransaction(id).getStarterAccountCbu());
    }

    @Then ("Transaction with id {int} should have {long} as target account CBU")
    public void checkCorrectTargetAccountCBUInCertainTransaction(int id, Long cbu) {
        assertEquals(cbu, system.getTransaction(id).getTargetAccountCbu());
    }

    @Then ("A new transaction record, with id {int}, should be created")
    public void checkThatTransactionWithSomeIdHasBeenCreated(int id) {
        assertEquals(id, system.getTransaction(id).getId());
    }

    @Then ("A new transaction record, with id {int}, should not be created")
    public void checkThatTransactionWithSomeIdHasNotBeenCreated(int id) {
        assertThrows(IllegalArgumentException.class, () -> system.getTransaction(id));
    }

    @After
    public void clearEntireSystem(){
        BankSystem.getInstance().clearEntireSystem();
    }
}

