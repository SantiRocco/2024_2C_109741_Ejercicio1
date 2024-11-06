package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

// Pruebas unitarias

class AccountManagerTest {

    @Test
    void constructorShouldInitializeNumberOfAccountsToZero() {
        AccountManager manager = new AccountManager();

        assertEquals(0, manager.getNumberOfAccounts());
    }

    @Test
    void accountManagerShouldCorrectlySetBalanceWhenAddingAccountWithBalance() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", 500.0, client, 1);

        assertEquals(500.0, manager.getAccount("iAmAccount").getBalance());
    }

    @Test
    void accountManagerShouldCorrectlyAddToCounterWhenAddingOneAccount() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        assertEquals(1, manager.getNumberOfAccounts());
    }

    @Test
    void accountManagerShouldCorrectlySubstractOfCounterWhenAddingOneAccount() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);
        
        assertEquals(1, manager.getNumberOfAccounts());

        manager.deleteAccount("iAmAccount");

        assertEquals(0, manager.getNumberOfAccounts());
    }

    @Test
    void accountManagerShouldCorrectlyDetectThatExistentAccountExistsWithCbu() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        assertTrue(manager.accountExists(123456789L));
    }

    @Test
    void accountManagerShouldCorrectlyDetectThatExistentAccountExistsWithAlias() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        assertTrue(manager.accountExists("iAmAccount"));
    }

    @Test
    void accountManagerShouldCorrectlyDetectThatNonExistentAccountDoesNotExistWithCbu() {
        AccountManager manager = new AccountManager();

        assertFalse(manager.accountExists(100000000L));
    }

    @Test
    void accountManagerShouldCorrectlyDetectThatNonExistentAccountDoesNotExistWithAlias() {
        AccountManager manager = new AccountManager();

        assertFalse(manager.accountExists("iAmNonExistentAccount"));
    }

    @Test
    void accountManagerShouldCorrectlyGetExistingAccountWithCbu() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        Account account = manager.getAccount(123456789L);

        assertEquals(0.0, account.getBalance());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());
    }

    @Test
    void accountManagerShouldCorrectlyGetExistingAccountWithAlias() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        Account account = manager.getAccount("iAmAccount");

        assertEquals(0.0, account.getBalance());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToGetNonExistentAccountWithCbu() {
        AccountManager manager = new AccountManager();

        assertThrows(IllegalArgumentException.class, () -> manager.getAccount(100000000L));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToGetNonExistentAccountWithAlias() {
        AccountManager manager = new AccountManager();

        assertThrows(IllegalArgumentException.class, () -> manager.getAccount("iAmNonExistentAccount"));
    }

    @Test
    void accountManagerShouldCorrectlyDeleteExistingAccountWithCbu() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        Account account = manager.getAccount("iAmAccount");

        assertEquals(0.0, account.getBalance());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        manager.deleteAccount(123456789L);
    
        assertFalse(manager.accountExists(123456789L));
        assertThrows(IllegalArgumentException.class, () -> manager.getAccount(123456789L));
    }    

    @Test
    void accountManagerShouldCorrectlyDeleteExistingAccountWithAlias() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", client, 1);

        Account account = manager.getAccount("iAmAccount");

        assertEquals(0.0, account.getBalance());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        manager.deleteAccount("iAmAccount");
    
        assertFalse(manager.accountExists("iAmAccount"));
        assertThrows(IllegalArgumentException.class, () -> manager.getAccount("iAmAccount"));
    }

    @Test
    void accountManagerShouldCorrectlyReduceNumberOfRelatedAccountsOfOwnerAndCoOwnersOfAccountWhenDeletingIt() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client owner = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   

        manager.createAccount(123456789L, "iAmAccount", owner, 1);

        Account account = manager.getAccount("iAmAccount");

        LocalDate coOwner1BirthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client coOwner1 = new Client(20000000, "Costas", "Ignacio", coOwner1BirthDate, "Av. Rivadavia 4523");
        LocalDate coOwner2BirthDate = LocalDate.of(1998, Month.FEBRUARY, 28);
        Client coOwner2 = new Client(10000000, "Lechuga", "Daniel", coOwner2BirthDate, "Cucha Cucha 400");

        account.setNewCoOwner(coOwner1);
        account.setNewCoOwner(coOwner2);

        assertEquals(1, owner.getNumberOfRelatedAccounts());
        assertEquals(1, coOwner1.getNumberOfRelatedAccounts());
        assertEquals(1, coOwner2.getNumberOfRelatedAccounts());


        manager.deleteAccount("iAmAccount");

        assertEquals(0, owner.getNumberOfRelatedAccounts());
        assertEquals(0, coOwner1.getNumberOfRelatedAccounts());
        assertEquals(0, coOwner2.getNumberOfRelatedAccounts());
    
        assertFalse(manager.accountExists("iAmAccount"));
        assertThrows(IllegalArgumentException.class, () -> manager.getAccount("iAmAccount"));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToDeleteNonExistentAccountWithCbu() {
        AccountManager manager = new AccountManager();

        assertThrows(IllegalArgumentException.class, () -> manager.deleteAccount(100000000L));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToDeleteNonExistentAccountWithAlias() {
        AccountManager manager = new AccountManager();
        assertThrows(IllegalArgumentException.class, () -> manager.deleteAccount("iAmNonExistentAccount"));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToDeleteAccountWithMoneyWithCbu() {
        AccountManager manager = new AccountManager();
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");    
        manager.createAccount(123456789L, "iAmAccount", 100.0,  client, 1);

        assertThrows(IllegalArgumentException.class, () -> manager.deleteAccount(100000000L));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToDeleteAccountWithMoneyWithAlias() {
        AccountManager manager = new AccountManager();
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");    
        manager.createAccount(123456789L, "iAmAccount", 100.0,  client, 1);

        assertThrows(IllegalArgumentException.class, () -> manager.deleteAccount(100000000L));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToAccountWithSameCbu() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");    
        manager.createAccount(123456789L, "iAmAccount", client, 1);
        assertThrows(IllegalArgumentException.class, () -> manager.createAccount(123456789L, "iAmAnotherAccount", client, 1));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToAccountWithSameAlias() {
        AccountManager manager = new AccountManager();

        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");   
        
        manager.createAccount(123456789L, "iAmAccount", client, 1);
        assertThrows(IllegalArgumentException.class, () -> manager.createAccount(111222333L, "iAmAccount", client, 1));
    }
}