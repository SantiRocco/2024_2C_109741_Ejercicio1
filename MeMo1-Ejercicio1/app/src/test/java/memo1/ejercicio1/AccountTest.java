package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Pruebas unitarias

class AccountTest {

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", client);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        assertThrows(IllegalArgumentException.class, () -> new Account(123456789L, "iAmAccount", -50.0, client));
    }

    @Test
    void constructorWithCbuAndAliasShouldInitializeCorrectly() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client);
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void setBalanceShouldThrowExceptionIfBalanceIsNegative() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", client);
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-1.0));
    }

    @Test
    void depositShouldIncreaseBalance() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", client);
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void depositShouldThrowExceptionForNegativeAmount() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", client);
        assertThrows(IllegalArgumentException.class, () -> account.setBalance(-10.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void withdrawShouldThrowExceptionIfAmountExceedsBalance() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client);
        assertFalse(account.withdraw(150.0));
    }

    @Test
    void withdrawShouldThrowExceptionForNegativeAmount() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client);
        assertFalse(account.withdraw(-10.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void transferShouldDecreaseSenderAccountBalanceAndIncreaseReceiverAccountBalance() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Client otherClient = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient);
        senderAccount.transfer(100.0, receiverAccount);
        assertEquals(900.0, senderAccount.getBalance());
        assertEquals(1100.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForAmountHigherThanBalanceAvailable() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Client otherClient = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient);
        assertFalse(senderAccount.transfer(2000.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForNegativeAmount() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Client otherClient = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient);
        assertFalse(senderAccount.transfer(-100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForZeroAmount() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Client otherClient = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient);
        assertFalse(senderAccount.transfer(0.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionWhenTransferringToAccountWithNoCbu() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Client otherClient = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client);
        Account accountWithNoCbu = new Account(null, "iAmAccount", 1000.0, otherClient);
        assertFalse(senderAccount.transfer(100.0, accountWithNoCbu));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, accountWithNoCbu.getBalance());
    }

    @Test
    void transferShouldThrowExceptionWhenTransferringToSameAccount() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client);
        Account receiverAccount = senderAccount;
        assertFalse(senderAccount.transfer(100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void accountShouldNotDetectItsOwnerAsCoOwner() {
        Client owner = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner);
        
        assertFalse(account.isCoOwner(owner));
    }

    @Test
    void accountShouldSaveCorrectlyItsCoOwners() {
        Client owner = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", "1998-2-29", "Cucha Cucha 400");

        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(coOwner1));
        assertTrue(account.isCoOwner(coOwner2));

    }

    @Test
    void accountShouldNotdetectExcludedClientAsCoOwner() {
        Client owner = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", "1998-2-29", "Cucha Cucha 400");
        Client NotACoOwner = new Client(1, "McFalso", "Falso", "1901-1-1", "Av. Falsa 123");
        
        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(coOwner1));
        assertTrue(account.isCoOwner(coOwner2));
        assertFalse(account.isCoOwner(NotACoOwner));

    }

    @Test
    void accountShouldNotLetSaveAlreadyIncludedClients() {
        Client owner = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner);
        Client coOwner = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        Client sameOwner = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        Client sameCoOwner = new Client(20000000, "Costas", "Ignacio", "2003-3-4", "Av. Rivadavia 4523");
        
        account.setNewCoOwner(coOwner);

        assertTrue(account.isCoOwner(coOwner));
        assertThrows(IllegalArgumentException.class, () -> account.setNewCoOwner(sameOwner) );
        assertThrows(IllegalArgumentException.class, () -> account.setNewCoOwner(sameCoOwner) );

    }

}
