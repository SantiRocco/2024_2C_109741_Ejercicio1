package memo1.ejercicio1;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class BranchTest {

    @Test
    void constructorShouldInitializeAllDataCorrectly() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertEquals(1, branch.getNumberOfBranch());
        assertEquals("Suc. Belgrano", branch.getName());
        assertEquals("Av. Paseo Colon 900", branch.getAddress());
    }

    @Test
    void constructorShouldThrowExceptionIfNumberOfInstanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Branch(-1, "Suc. Belgrano", "Av. Paseo Colon 900"));
    }    

    @Test
    void constructorShouldThrowExceptionIfNumberOfInstanceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Branch(0, "Suc. Belgrano", "Av. Paseo Colon 900"));
    }   
    
    @Test
    void branchShouldBeAbleToChangeItsName() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        assertEquals("Suc. Belgrano", branch.getName());

        branch.changeName("Suc. Belgrano R.");

        assertEquals("Suc. Belgrano R.", branch.getName());
    }   

    @Test
    void branchShouldBeAbleToChangeItsAddress() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        assertEquals("Av. Paseo Colon 900", branch.getAddress());

        branch.changeAddress("Av. Paseo Colon 1000");

        assertEquals("Av. Paseo Colon 1000", branch.getAddress());
    }   

    @Test
    void branchShouldCorrectlyCreateAndGetAccountWithCbu() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        
        clientManager.addClient(dni, surname, name, birthDate, address);


        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = branch.getAccount(123456789L);

        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        branch.deleteAccount(123456789L);
        clientManager.deleteClient(dni);
    }

    @Test
    void branchShouldCorrectlyCreateAndGetAccountWithAlias() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = branch.getAccount("iAmAccount");

        assertEquals(1, branch.getNumberOfAccountsOfBranch());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        branch.deleteAccount(123456789L);
        clientManager.deleteClient(dni);
    }

    @Test
    void branchShouldCorrectlyDeleteAccountWithCbu() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = branch.getAccount(123456789L);

        assertEquals(1, branch.getNumberOfAccountsOfBranch());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        branch.deleteAccount(123456789L);
    
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertThrows(IllegalArgumentException.class, () -> branch.getAccount(123456789L));

        clientManager.deleteClient(dni);
    }

    @Test
    void branchShouldCorrectlyDeleteAccountWithAlias() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        branch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        Account account = branch.getAccount("iAmAccount");

        assertEquals(1, branch.getNumberOfAccountsOfBranch());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertEquals(50.0, account.getBalance());
        assertTrue(account.isOwner(12345678));
        assertEquals(1, account.getBranch());

        branch.deleteAccount("iAmAccount");
    
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertThrows(IllegalArgumentException.class, () -> branch.getAccount(123456789L));

        clientManager.deleteClient(dni);
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToCreateAccountWithDniOfNonExistentClient() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        assertThrows(IllegalArgumentException.class, () -> branch.createAccount(123456789L, "iAmAccount", 50.0, 10000000));
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToGetNonExistentAccount() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        assertThrows(IllegalArgumentException.class, () -> branch.getAccount(123456789L));
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToGetAccountOfDifferentBranchWithCbu() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        Branch anotherBranch = new Branch(2, "Suc. Palermo", "Av. Corrientes 5000");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        anotherBranch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.getAccount(123456789L));

        anotherBranch.deleteAccount(123456789L);
        clientManager.deleteClient(dni);
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToGetAccountOfDifferentBranchWithAlias() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        Branch anotherBranch = new Branch(2, "Suc. Palermo", "Av. Corrientes 5000");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        anotherBranch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.getAccount("iAmAccount"));

        anotherBranch.deleteAccount("iAmAccount");
        clientManager.deleteClient(dni);
    }  

    @Test
    void branchShouldThrowExceptionWhenTryingToDeleteAccountOfDifferentBranchWithCbu() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        Branch anotherBranch = new Branch(2, "Suc. Palermo", "Av. Corrientes 5000");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        anotherBranch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount(123456789L));

        anotherBranch.deleteAccount(123456789L);
        clientManager.deleteClient(dni);
    }

    @Test
    void branchShouldThrowExceptionWhenTryingToDeleteAccountOfDifferentBranchWithAlias() {
        Branch branch = new Branch(1, "Suc. Belgrano", "Av. Paseo Colon 900");

        Branch anotherBranch = new Branch(2, "Suc. Palermo", "Av. Corrientes 5000");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);

        anotherBranch.createAccount(123456789L, "iAmAccount", 50.0, 12345678);

        assertThrows(IllegalArgumentException.class, () -> branch.deleteAccount("iAmAccount"));

        anotherBranch.deleteAccount("iAmAccount");
        clientManager.deleteClient(dni);
    }   

}
