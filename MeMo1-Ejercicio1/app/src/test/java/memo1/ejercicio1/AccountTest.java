package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Pruebas unitarias

class AccountTest {

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        Account account = new Account(123456789L, "iAmAccount");
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        Account account = new Account(123456789L, "iAmAccount", 100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Account(123456789L, "iAmAccount", -50.0));
    }

    @Test
    void constructorWithCbuAndAliasShouldInitializeCorrectly() {
        Account account = new Account(123456789L, "iAmAccount", 100.0);
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void setBalanceShouldThrowExceptionIfBalanceIsNegative() {
        Account account = new Account(123456789L, "iAmAccount");
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void depositShouldIncreaseBalance() {
        Account account = new Account(123456789L, "iAmAccount");
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void depositShouldThrowExceptionForNegativeAmount() {
        Account account = new Account(123456789L, "iAmAccount");
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-10.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Account account = new Account(123456789L, "iAmAccount", 100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void withdrawShouldThrowExceptionIfAmountExceedsBalance() {
        Account account = new Account(123456789L, "iAmAccount", 100.0);
        assertFalse(account.withdraw(150.0));
    }

    @Test
    void withdrawShouldThrowExceptionForNegativeAmount() {
        Account account = new Account(123456789L, "iAmAccount", 100.0);
        assertFalse(account.withdraw(-10.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        Account account = new Account(123456789L, "iAmAccount", 100.0);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void transferShouldDecreaseSenderAccountBalanceAndIncreaseReceiverAccountBalance() {
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0);
        senderAccount.transfer(100.0, receiverAccount);
        assertEquals(900.0, senderAccount.getBalance());
        assertEquals(1100.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForAmountHigherThanBalanceAvailable() {
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0);
        assertFalse(senderAccount.transfer(2000.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForNegativeAmount() {
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0);
        assertFalse(senderAccount.transfer(-100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForZeroAmount() {
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0);
        assertFalse(senderAccount.transfer(0.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionWhenTransferringToAccountWithNoCbu() {
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0);
        Account accountWithNoCbu = new Account(null, "iAmAccount", 1000.0);
        assertFalse(senderAccount.transfer(100.0, accountWithNoCbu));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, accountWithNoCbu.getBalance());
    }

    @Test
    void transferShouldThrowExceptionWhenTransferringToSameAccount() {
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0);
        Account receiverAccount = senderAccount;
        assertFalse(senderAccount.transfer(100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

}
