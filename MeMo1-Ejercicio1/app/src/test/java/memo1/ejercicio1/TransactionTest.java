package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

// Pruebas unitarias

class TransactionTest {

    @Test
    void constructorShouldThrowExceptionIfIdIsNegative() {
        int id = -1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long accountCbu = 100111222L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, accountCbu));
    }

    @Test
    void constructorShouldThrowExceptionIfIdIsZero() {
        int id = 0;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long accountCbu = 100111222L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, accountCbu));
    }

    @Test
    void constructorShouldThrowExceptionIfOneCbuIsNegative() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long starterCbu = -1L;
        Long targetCbu = 111222333L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, starterCbu, targetCbu));
    }

    @Test
    void constructorShouldThrowExceptionIfOneCbuIsZero() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long starterCbu = 0L;
        Long targetCbu = 111222333L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, starterCbu, targetCbu));
    }

    @Test
    void constructorShouldThrowExceptionIfBothCbusAreTheSame() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long starterCbu = 111222333L;
        Long targetCbu = 111222333L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, starterCbu, targetCbu));
    }

    @Test
    void constructorShouldThrowExceptionIfAmountIsNegative() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.DEPOSIT;
        double amount = -1.0;
        Long accountCbu = 111222333L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, accountCbu));
    }

    @Test
    void constructorShouldThrowExceptionIfAmountIsZero() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.DEPOSIT;
        double amount = 0.0;
        Long accountCbu = 111222333L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, accountCbu));
    }

    @Test
    void constructorShouldThrowExceptionWhenTryingToSaveDepositOrWithdrawalWithTwoCbus() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.DEPOSIT;
        double amount = 100.0;
        Long starterCbu = 100111222L;
        Long targetCbu = 111222333L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, starterCbu, targetCbu));
    }

    @Test
    void constructorShouldThrowExceptionWhenTryingToSaveTransferWithOneCbu() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long accountCbu = 100111222L;

        assertThrows(IllegalArgumentException.class, () -> new Transaction(id, date, hour, type, amount, accountCbu));
    }

    @Test
    void transactionShouldSaveAllDataCorrectlyForDeposit() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.DEPOSIT;
        double amount = 10.0;
        Long accountCbu = 100111222L;

        Transaction transaction = new Transaction(id, date, hour, type, amount, accountCbu);

        assertEquals(id, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(type, transaction.getTypeOfOperation());
        assertEquals(amount, transaction.getAmount());
        assertEquals(accountCbu, transaction.getStarterAccountCbu());
        assertEquals(accountCbu, transaction.getTargetAccountCbu());
    }

    @Test
    void transactionShouldSaveAllDataCorrectlyForWithdrawal() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.WITHDRAWAL;
        double amount = 100.0;
        Long accountCbu = 100111222L;

        Transaction transaction = new Transaction(id, date, hour, type, amount, accountCbu);

        assertEquals(id, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(type, transaction.getTypeOfOperation());
        assertEquals(amount, transaction.getAmount());
        assertEquals(accountCbu, transaction.getStarterAccountCbu());
        assertEquals(accountCbu, transaction.getTargetAccountCbu());
    }

    @Test
    void transactionShouldSaveAllDataCorrectlyForTransfer() {
        int id = 1;
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 1);
        LocalTime hour = LocalTime.of(13, 0, 0);
        TransactionType type = TransactionType.TRANSFER;
        double amount = 100.0;
        Long starterCbu = 100111222L;
        Long targetCbu = 111222333L;

        Transaction transaction = new Transaction(id, date, hour, type, amount, starterCbu, targetCbu);

        assertEquals(id, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(type, transaction.getTypeOfOperation());
        assertEquals(amount, transaction.getAmount());
        assertEquals(starterCbu, transaction.getStarterAccountCbu());
        assertEquals(targetCbu, transaction.getTargetAccountCbu());
    }
}
