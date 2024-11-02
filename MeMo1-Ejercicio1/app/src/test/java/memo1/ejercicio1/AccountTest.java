package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

// Pruebas unitarias

class AccountTest {

    @Test
    void defaultConstructorShouldInitializeBalanceToZero() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", client, 1);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void constructorShouldSetBalanceCorrectly() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        Account account = new Account(123456789L, "iAmAccount", 100.0, client, 1);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void defaultConstructorShouldSetDataCorrectly() {
        int clientDni = 12345678;
        String clientSurname  = "Fernandez";
        String clientName = "Martin";
        LocalDate clientBirthDate = LocalDate.of(2000, Month.MAY, 17);
        String clientAddress = "Av. Acoyte 245";
        Client newClient = new Client(clientDni, clientSurname, clientName, clientBirthDate, clientAddress);

        Long accountCbu = 123456789L;
        String accountAlias = "iAmAccount";
        int branch = 1;
        Account account = new Account(accountCbu, accountAlias, newClient, branch);
        assertEquals(0.0, account.getBalance());
        assertEquals(accountCbu, account.getCbu());
        assertEquals(accountAlias, account.getAlias());
        assertTrue(account.isOwner(clientDni));
        assertEquals(branch, account.getBranch());
    }

    @Test
    void constructorShouldThrowExceptionIfBalanceIsNegative() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");        
        assertThrows(IllegalArgumentException.class, () -> new Account(123456789L, "iAmAccount", -50.0, client, 1));
    }

    @Test
    void constructorShouldThrowExceptionIfClientIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Account(123456789L, "iAmAccount", 100.0, null, 1));
    }

    @Test
    void constructorShouldThrowExceptionIfCbuIsNegative() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        assertThrows(IllegalArgumentException.class, () -> new Account(-1L, "iAmAccount", 100.0, client, 1));
    }

    @Test
    void constructorShouldThrowExceptionIfCbuIsZero() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        assertThrows(IllegalArgumentException.class, () -> new Account(0L, "iAmAccount", 100.0, client, 1));
    }

    @Test
    void constructorShouldThrowExceptionIfBranchNumberIsNegative() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        assertThrows(IllegalArgumentException.class, () -> new Account(123456789L, "iAmAccount", 100.0, client, -1));
    }

    @Test
    void constructorShouldThrowExceptionIfBranchNumberIsZero() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        assertThrows(IllegalArgumentException.class, () -> new Account(123456789L, "iAmAccount", 100.0, client, 0));
    }

    @Test
    void constructorWithCbuAndAliasShouldInitializeCorrectly() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", 100.0, client,  1);
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void depositShouldIncreaseBalance() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", client, 1);
        account.deposit(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void depositShouldThrowExceptionForNegativeAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", client, 1);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
    }

    @Test
    void depositShouldThrowExceptionForZeroAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", client, 1);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", 100.0, client, 1);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void withdrawShouldThrowExceptionIfAmountExceedsBalance() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", 100.0, client, 1);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150.0));
    }

    @Test
    void withdrawShouldThrowExceptionForNegativeAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", 100.0, client, 1);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10.0));
    }

    @Test
    void withdrawShouldThrowExceptionForZeroAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", client, 1);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
    }

    @Test
    void withdrawShouldAllowExactAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmAccount", 100.0, client, 1);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void transferShouldDecreaseSenderAccountBalanceAndIncreaseReceiverAccountBalance() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   
        LocalDate otherBirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client otherClient = new Client(20000000, "Costas", "Ignacio", otherBirthDate, "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client, 1);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient, 1);
        senderAccount.transfer(100.0, receiverAccount);
        assertEquals(900.0, senderAccount.getBalance());
        assertEquals(1100.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForAmountHigherThanBalanceAvailable() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        LocalDate otherBirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client otherClient = new Client(20000000, "Costas", "Ignacio", otherBirthDate, "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client, 1);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient,1);
        assertThrows(IllegalArgumentException.class, () -> senderAccount.transfer(2000.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForNegativeAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        LocalDate otherBirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client otherClient = new Client(20000000, "Costas", "Ignacio", otherBirthDate, "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client, 1);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient,1);
        assertThrows(IllegalArgumentException.class, () -> senderAccount.transfer(-100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionForZeroAmount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");     
        LocalDate otherBirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client otherClient = new Client(20000000, "Costas", "Ignacio", otherBirthDate, "Av. Rivadavia 4523");
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client, 1);
        Account receiverAccount = new Account(111222333L, "iAmReceiver", 1000.0, otherClient, 1);
        assertThrows(IllegalArgumentException.class, () -> senderAccount.transfer(0.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void transferShouldThrowExceptionWhenTransferringToSameAccount() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account senderAccount = new Account(123456789L, "iAmSender", 1000.0, client, 1);
        Account receiverAccount = senderAccount;
        assertThrows(IllegalArgumentException.class, () -> senderAccount.transfer(100.0, receiverAccount));
        assertEquals(1000.0, senderAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
    }

    @Test
    void accountShouldDetectItsOwnerAsOwner() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        
        assertTrue(account.isOwner(12345678));
    }

    @Test
    void accountShouldNotDetectItsOwnerAsCoOwner() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        
        assertFalse(account.isCoOwner(12345678));
    }

    @Test
    void accountShouldSaveCorrectlyItsCoOwners() {
        LocalDate ownerBirthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", ownerBirthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        LocalDate coOwner1BirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", coOwner1BirthDate, "Av. Rivadavia 4523");
        LocalDate coOwner2BirthDate = LocalDate.of(1998, Month.FEBRUARY, 28);
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", coOwner2BirthDate, "Cucha Cucha 400");

        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(20000000));
        assertTrue(account.isCoOwner(10000000));

    }

    @Test
    void accountShouldShouldNotDetectItsCoOwnersAsOwners() {
        LocalDate ownerBirthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", ownerBirthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        LocalDate coOwner1BirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", coOwner1BirthDate, "Av. Rivadavia 4523");
        LocalDate coOwner2BirthDate = LocalDate.of(1998, Month.FEBRUARY, 28);
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", coOwner2BirthDate, "Cucha Cucha 400");

        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(20000000));
        assertTrue(account.isCoOwner(10000000));
        assertFalse(account.isOwner(20000000));
        assertFalse(account.isOwner(10000000));

    }

    @Test
    void accountShouldCorrectlyRemoveCoOwner() {
        LocalDate ownerBirthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", ownerBirthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        LocalDate coOwner1BirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", coOwner1BirthDate, "Av. Rivadavia 4523");
        LocalDate coOwner2BirthDate = LocalDate.of(1998, Month.FEBRUARY, 28);
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", coOwner2BirthDate, "Cucha Cucha 400");
        
        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(20000000));
        assertTrue(account.isCoOwner(10000000));

        account.removeCoOwner(10000000);

        assertTrue(account.isCoOwner(20000000));
        assertFalse(account.isCoOwner(10000000));
    }

    @Test
    void accountShouldThrowExceptionWhenTryingToRemoveNonExistentCoOwner() {
        LocalDate ownerBirthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", ownerBirthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        LocalDate coOwner1BirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", coOwner1BirthDate, "Av. Rivadavia 4523");
        LocalDate coOwner2BirthDate = LocalDate.of(1998, Month.FEBRUARY, 28);
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", coOwner2BirthDate, "Cucha Cucha 400");
        
        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(20000000));
        assertTrue(account.isCoOwner(10000000));

        assertThrows(IllegalArgumentException.class, () -> account.removeCoOwner(1) );
    }

    @Test
    void accountShouldNotdetectExcludedClientDniAsCoOwner() {
        LocalDate ownerBirthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", ownerBirthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        LocalDate coOwner1BirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", coOwner1BirthDate, "Av. Rivadavia 4523");
        LocalDate coOwner2BirthDate = LocalDate.of(1998, Month.FEBRUARY, 28);
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", coOwner2BirthDate, "Cucha Cucha 400");
        
        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertTrue(account.isCoOwner(20000000));
        assertTrue(account.isCoOwner(10000000));
        assertFalse(account.isCoOwner(1));

    }

    @Test
    void accountShouldNotLetSaveAlreadyIncludedClients() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");      
        Account account = new Account(123456789L, "iAmSender", 1000.0, owner, 1);
        LocalDate coOwnerBirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner = new Client(20000000, "Costas", "Ignacio", coOwnerBirthDate, "Av. Rivadavia 4523");
        Client sameOwner = owner;
        Client sameCoOwner = coOwner;
        
        account.setNewCoOwner(coOwner);

        assertTrue(account.isCoOwner(20000000));
        assertThrows(IllegalArgumentException.class, () -> account.setNewCoOwner(sameOwner) );
        assertThrows(IllegalArgumentException.class, () -> account.setNewCoOwner(sameCoOwner) );

    }

}
