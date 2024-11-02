package memo1.ejercicio1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class ClientManagerTest {
    
    @Test
    void clientManagerShouldSaveClientDataCorrectly() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = ClientManager.getInstance();

        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());

        manager.deleteClient(dni);
    }

    @Test
    void clientManagerShouldGetClientCorrectly() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = ClientManager.getInstance();

        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());

        manager.deleteClient(dni);
    }

    @Test
    void clientManagerShouldCorrectlyDeleteClient() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = ClientManager.getInstance();

        manager.addClient(dni, surname, name, birthDate, address);


        Client client = manager.getClient(dni);

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());

        manager.deleteClient(dni);
    
        assertFalse(manager.clientExists(dni));
        assertThrows(IllegalArgumentException.class, () -> manager.getClient(dni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToGetClientWithNegativeDni() {
        int negativeDni = -1;
        ClientManager manager = ClientManager.getInstance();
        assertThrows(IllegalArgumentException.class, () -> manager.clientExists(negativeDni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToGetClientWithZeroDni() {
        int zeroDni = 0;
        ClientManager manager = ClientManager.getInstance();
    
        assertThrows(IllegalArgumentException.class, () -> manager.clientExists(zeroDni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToGetNonExistentClient() {
        int nonExistentDni = 12345678;
        ClientManager manager = ClientManager.getInstance();
    
        assertFalse(manager.clientExists(nonExistentDni));
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToAddAlreadyExistingClient() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = ClientManager.getInstance();

        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());

        assertThrows(IllegalArgumentException.class, () ->  manager.addClient(dni, surname, name, birthDate, address) );

        manager.deleteClient(dni);
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToDeleteNonExistentClient() {
        int nonExistentDni = 12345678;
        ClientManager manager = ClientManager.getInstance();
    
        assertFalse(manager.clientExists(nonExistentDni));
        assertThrows(IllegalArgumentException.class, () -> manager.deleteClient(nonExistentDni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToDeleteClientWithRelatedAccounts() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = ClientManager.getInstance();

        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);
        client.oneMoreRelatedAccount();  

        assertTrue(manager.clientExists(dni));
        assertThrows(IllegalArgumentException.class, () -> manager.deleteClient(dni) );

        client.oneLessRelatedAccount();
        manager.deleteClient(dni);
    }

    @Test
    void clientManagerShouldSuccessfullyDeleteMarriageWhenDeletingMarriedClient() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2002, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";

        ClientManager clientManager = ClientManager.getInstance();
        MarriageManager marriageManager = MarriageManager.getInstance();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        marriageManager.newMarriage(LocalDate.of(2010, Month.JANUARY, 1), dni, dniSpouse);

        assertTrue(marriageManager.isMarried(dni));
        assertTrue(marriageManager.isMarried(dniSpouse));

        clientManager.deleteClient(dni);

        assertFalse(marriageManager.isMarried(dni));
        assertFalse(marriageManager.isMarried(dniSpouse));

        clientManager.deleteClient(dniSpouse);
    }
}
