package memo1.ejercicio1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class BranchTest {

    @Test
    void constructorShouldInitializeAllDataCorrectly() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertEquals(1, branch.getNumberOfBranch());
        assertEquals("Suc. Belgrano", branch.getName());
        assertEquals("Av. Paseo Colon 900", branch.getAddress());
    }

    @Test
    void constructorShouldThrowExceptionIfNumberOfInstanceIsNegative() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        assertThrows(IllegalArgumentException.class, () -> new Branch(-1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager));
    }    

    @Test
    void constructorShouldThrowExceptionIfNumberOfInstanceIsZero() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        assertThrows(IllegalArgumentException.class, () -> new Branch(0, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager));
    }   
    
    @Test
    void branchShouldBeAbleToChangeItsName() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        assertEquals("Suc. Belgrano", branch.getName());

        branch.changeName("Suc. Belgrano R.");

        assertEquals("Suc. Belgrano R.", branch.getName());
    }   

    @Test
    void branchShouldBeAbleToChangeItsAddress() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        assertEquals("Av. Paseo Colon 900", branch.getAddress());

        branch.changeAddress("Av. Paseo Colon 1000");

        assertEquals("Av. Paseo Colon 1000", branch.getAddress());
    }   

    @Test
    void branchShouldCorrectlyCreateAccountWithDefaultBalance() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";
        
        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 12345678);

        Account account = accountManager.getAccount(123456789L);

        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(0.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());
    }

    @Test
    void branchShouldCorrectlyCreateAccountWithCustomBalance() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";
        
        clientManager.addClient(dni, surname, name, birthDate, address);


        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = accountManager.getAccount(123456789L);

        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());
    }

    @Test
    void branchShouldCorrectlyGetAccountThroughCbu() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";
        
        clientManager.addClient(dni, surname, name, birthDate, address);


        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = accountManager.getAccount(123456789L);

        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());
    }

    @Test
    void branchShouldCorrectlyGetAccountThroughAlias() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = accountManager.getAccount("iAmAccount");

        assertEquals(1, branch.getNumberOfAccountsOfBranch());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());
    }

    @Test
    void branchShouldCorrectlyDeleteAccountWithCbu() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 12345678);

        Account account = accountManager.getAccount(123456789L);

        assertEquals(1, branch.getNumberOfAccountsOfBranch());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(0.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        branch.deleteAccount(123456789L);
    
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertThrows(IllegalArgumentException.class, () -> accountManager.getAccount(123456789L));
    }

    @Test
    void branchShouldCorrectlyDeleteAccountWithAlias() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 12345678);

        Account account = accountManager.getAccount("iAmAccount");

        assertEquals(1, branch.getNumberOfAccountsOfBranch());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(0.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        branch.deleteAccount("iAmAccount");
    
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertThrows(IllegalArgumentException.class, () -> accountManager.getAccount(123456789L));
    }
    
    @Test
    void accountManagerShouldThrowExceptionWhenTryingToDeleteAccountWithMoneyWithCbu() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 100.0,  12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount(123456789L));
    }

    @Test
    void accountManagerShouldThrowExceptionWhenTryingToDeleteAccountWithMoneyWithAlias() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 100.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount("iAmAccount"));
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToCreateAccountWithDniOfNonExistentClient() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        assertThrows(IllegalArgumentException.class, () -> branch.createAccount(123456789L, "iAmAccount", 50.0, 10000000));
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToDeleteAccountOfDifferentBranchWithCbu() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);
        
        Branch anotherBranch = new Branch(2, "Suc. Palermo", "Av. Corrientes 5000", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        anotherBranch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount(123456789L));
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToDeleteAccountOfDifferentBranchWithAlias() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        Branch anotherBranch = new Branch(2, "Suc. Palermo", "Av. Corrientes 5000", accountManager, clientManager);

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        anotherBranch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount("iAmAccount"));
    }

    @Test
    void branchShouldShowCorrectlyThatItIsActive() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        assertTrue(branch.isActive());
    } 

    @Test
    void branchShouldShowCorrectlyThatItIsInactiveWhenInactivated() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        branch.inactivate();

        assertFalse(branch.isActive());
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToInactivateItWhenItStillHasRelatedAccounts() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.inactivate());
    } 

    @Test
    void branchShouldThrowExceptionWhenDoingAnyOperationWhileInactive() {
        AccountManager accountManager = new AccountManager();
        ClientManager clientManager = new ClientManager();
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900", accountManager, clientManager);

        branch.inactivate();

        assertThrows(IllegalArgumentException.class, () -> branch.changeAddress("newAddress"));
        assertThrows(IllegalArgumentException.class, () -> branch.changeName("newName"));
        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount("alias"));
        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount(123L));
        assertThrows(IllegalArgumentException.class, () -> branch.getAddress());
        assertThrows(IllegalArgumentException.class, () -> branch.getName());
        assertThrows(IllegalArgumentException.class, () -> branch.getNumberOfAccountsOfBranch());
        assertThrows(IllegalArgumentException.class, () -> branch.getNumberOfBranch());
        assertThrows(IllegalArgumentException.class, () -> branch.createAccount(123456789L, "iAmAccount", 0.0, 1));
        assertThrows(IllegalArgumentException.class, () -> branch.createAccount(123456789L, "iAmAccount", 0.0, 1));
    }

}
