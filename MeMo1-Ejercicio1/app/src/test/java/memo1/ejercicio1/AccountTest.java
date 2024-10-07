package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Pruebas unitarias

class AccountTest {

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        Account account = new Account();
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        Account account = new Account(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Account(-50.0));
    }

    @Test
    void constructorWithCbuShouldInitializeCorrectly() {
        Account account = new Account(123456789L, 100.0);
        assertEquals(123456789L, account.getCbu());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void setBalanceShouldThrowExceptionIfBalanceIsNegative() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void depositShouldIncreaseBalance() {
        Account account = new Account();
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void depositShouldReturnFalseForNegativeAmount() {
        Account account = new Account();
        assertFalse(account.deposit(-10.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Account account = new Account(100.0);
        assertTrue(account.withdraw(50.0));
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void withdrawShouldReturnFalseIfAmountExceedsBalance() {
        Account account = new Account(100.0);
        assertFalse(account.withdraw(150.0));
    }

    @Test
    void withdrawShouldReturnFalseForNegativeAmount() {
        Account account = new Account(100.0);
        assertFalse(account.withdraw(-10.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        Account account = new Account(100.0);
        assertTrue(account.withdraw(100.0));
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void transferShouldDecreaseSenderAccountBalanceAndIncreaseReceiverAccountBalance() {
        Account senderAccount = new Account(123456789L, 1000.0);
        Account receiverAccount = new Account(111222333L, 1000.0);
        assertTrue(senderAccount.transfer(100.0, receiverAccount));
        assertEquals(900.0, senderAccount.getBalance());
        assertEquals(1100.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldReturnFalseForAmountHigherThanBalanceAvailable() {
        Account senderAccount = new Account(123456789L, 1000.0);
        Account receiverAccount = new Account(111222333L, 1000.0);
        assertFalse(senderAccount.transfer(2000.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldReturnFalseForNegativeAmount() {
        Account senderAccount = new Account(123456789L, 1000.0);
        Account receiverAccount = new Account(111222333L, 1000.0);
        assertFalse(senderAccount.transfer(-100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldReturnFalseForZeroAmount() {
        Account senderAccount = new Account(123456789L, 1000.0);
        Account receiverAccount = new Account(111222333L, 1000.0);
        assertFalse(senderAccount.transfer(0.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldReturnFalseWhenTransferringToAccountWithNoCbu() {
        Account senderAccount = new Account(123456789L, 1000.0);
        Account accountWithNoCbu = new Account(1000.0);
        assertFalse(senderAccount.transfer(100.0, accountWithNoCbu));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, accountWithNoCbu.getBalance());
    }

    @Test
    void transferShouldReturnFalseWhenTransferringToSameAccount() {
        Account senderAccount = new Account(123456789L, 1000.0);
        Account receiverAccount = senderAccount;
        assertFalse(senderAccount.transfer(100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

}
