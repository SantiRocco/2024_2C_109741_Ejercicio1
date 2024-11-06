package memo1.ejercicio1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class TransactionManagerTest {
    
    @Test
    void transactionsManagerShouldSaveAllDataCorrectlyForDeposit() {
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.DEPOSIT;
        double amount = 100.0;
        Long accountCbu = 100111222L;

        TransactionsManager manager = new TransactionsManager();

        int transactionId = manager.saveTransaction(date, hour, type, amount, accountCbu);

        Transaction transaction = manager.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(type, transaction.getTypeOfOperation());
        assertEquals(amount, transaction.getAmount());
        assertEquals(accountCbu, transaction.getStarterAccountCbu());
        assertEquals(accountCbu, transaction.getTargetAccountCbu());
    }

    @Test
    void transactionsManagerShouldSaveAllDataCorrectlyForWithdrawal() {
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.WITHDRAWAL;
        double amount = 100.0;
        Long accountCbu = 100111222L;

        TransactionsManager manager = new TransactionsManager();

        int transactionId = manager.saveTransaction(date, hour, type, amount, accountCbu);

        Transaction transaction = manager.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(type, transaction.getTypeOfOperation());
        assertEquals(amount, transaction.getAmount());
        assertEquals(accountCbu, transaction.getStarterAccountCbu());
        assertEquals(accountCbu, transaction.getTargetAccountCbu());
    }

    @Test
    void transactionsManagerShouldSaveAllDataCorrectlyForTransfer() {
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 3);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long starterCbu = 100111222L;
        Long targetCbu = 111222333L;

        TransactionsManager manager = new TransactionsManager();

        int transactionId = manager.saveTransaction(date, hour, type, amount, starterCbu, targetCbu);

        Transaction transaction = manager.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(type, transaction.getTypeOfOperation());
        assertEquals(amount, transaction.getAmount());
        assertEquals(starterCbu, transaction.getStarterAccountCbu());
        assertEquals(targetCbu, transaction.getTargetAccountCbu());
    }

    @Test
    void transactionsManagerShouldGetCorrectlyVariousTransactions() {
        LocalDate date1 = LocalDate.of(2024, Month.JANUARY, 4);
        LocalTime hour1 = LocalTime.of(13, 0, 0);
        TransactionType type1 = TransactionType.TRANSFER;
        double amount1 = 100.0;
        Long starterCbu1 = 100111222L;
        Long targetCbu1 = 111222333L;

        LocalDate date2 = LocalDate.of(2024, Month.JANUARY, 5);
        LocalTime hour2 = LocalTime.of(13, 0, 0);
        TransactionType type2 = TransactionType.TRANSFER;
        double amount2 = 100.0;
        Long starterCbu2 = 100111222L;
        Long targetCbu2 = 111222333L;

        TransactionsManager manager = new TransactionsManager();

        int transaction1Id = manager.saveTransaction(date1, hour1, type1, amount1, starterCbu1, targetCbu1);
        int transaction2Id = manager.saveTransaction(date2, hour2, type2, amount2, starterCbu2, targetCbu2);

        Transaction transaction1 = manager.getTransaction(transaction1Id);
        Transaction transaction2 = manager.getTransaction(transaction2Id);

        assertEquals(transaction1Id, transaction1.getId());
        assertEquals(date1, transaction1.getDateOfOperation());
        assertEquals(hour1, transaction1.getHourOfOperation());
        assertEquals(type1, transaction1.getTypeOfOperation());
        assertEquals(amount1, transaction1.getAmount());
        assertEquals(starterCbu1, transaction1.getStarterAccountCbu());
        assertEquals(targetCbu1, transaction1.getTargetAccountCbu());

        assertEquals(transaction2Id, transaction2.getId());
        assertEquals(date2, transaction2.getDateOfOperation());
        assertEquals(hour2, transaction2.getHourOfOperation());
        assertEquals(type2, transaction2.getTypeOfOperation());
        assertEquals(amount2, transaction2.getAmount());
        assertEquals(starterCbu2, transaction2.getStarterAccountCbu());
        assertEquals(targetCbu2, transaction2.getTargetAccountCbu());
    }

    @Test
    void transactionsManagerShouldThrowExceptionWhenTryingToGetNonExistentTransaction() {
        int nonExistentId = 1000;
        
        TransactionsManager manager = new TransactionsManager();

        assertThrows(IllegalArgumentException.class, () -> manager.getTransaction(nonExistentId));       
    }
}
