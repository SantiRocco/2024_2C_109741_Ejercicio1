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

        ClientManager manager = new ClientManager();

        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);

        assertEquals(1, manager.getNumberOfClients());

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());
    }

    @Test
    void clientManagerShouldGetClientCorrectly() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = new ClientManager();

        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());
    }

    @Test
    void clientManagerShouldCorrectlyDeleteClient() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = new ClientManager();
        MarriageManager marriageManager = new MarriageManager(clientManager);

        clientManager.addClient(dni, surname, name, birthDate, address);


        Client client = clientManager.getClient(dni);

        assertTrue(clientManager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());

        clientManager.deleteClient(dni, marriageManager);
    
        assertFalse(clientManager.clientExists(dni));
        assertThrows(IllegalArgumentException.class, () -> clientManager.getClient(dni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToGetClientWithNegativeDni() {
        int negativeDni = -1;
        ClientManager manager = new ClientManager();

        assertThrows(IllegalArgumentException.class, () -> manager.clientExists(negativeDni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToGetClientWithZeroDni() {
        int zeroDni = 0;
        ClientManager manager = new ClientManager();
    
        assertThrows(IllegalArgumentException.class, () -> manager.clientExists(zeroDni) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToGetNonExistentClient() {
        int nonExistentDni = 12345678;
        ClientManager manager = new ClientManager();
    
        assertFalse(manager.clientExists(nonExistentDni));
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToAddAlreadyExistingClient() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager manager = new ClientManager();
        manager.addClient(dni, surname, name, birthDate, address);

        Client client = manager.getClient(dni);

        assertTrue(manager.clientExists(dni));
        assertEquals(surname, client.getSurname());
        assertEquals(name, client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals(address, client.getAddress());

        assertThrows(IllegalArgumentException.class, () ->  manager.addClient(dni, surname, name, birthDate, address) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToDeleteNonExistentClient() {
        int nonExistentDni = 12345678;
        ClientManager clientManager = new ClientManager();
        MarriageManager marriageManager = new MarriageManager(clientManager);

    
        assertFalse(clientManager.clientExists(nonExistentDni));
        assertThrows(IllegalArgumentException.class, () -> clientManager.deleteClient(nonExistentDni, marriageManager) );
    }

    @Test
    void clientManagerShouldThrowExceptionIfTryingToDeleteClientWithRelatedAccounts() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = new ClientManager();
        MarriageManager marriageManager = new MarriageManager(clientManager);

        clientManager.addClient(dni, surname, name, birthDate, address);

        Client client = clientManager.getClient(dni);
        client.oneMoreRelatedAccount();  

        assertTrue(clientManager.clientExists(dni));
        assertThrows(IllegalArgumentException.class, () -> clientManager.deleteClient(dni, marriageManager) );
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

        ClientManager clientManager = new ClientManager();
        MarriageManager marriageManager = new MarriageManager(clientManager);

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        marriageManager.newMarriage(LocalDate.of(2010, Month.JANUARY, 1), dni, dniSpouse);

        assertTrue(marriageManager.isMarried(dni));
        assertTrue(marriageManager.isMarried(dniSpouse));

        clientManager.deleteClient(dni, marriageManager);

        assertThrows(IllegalArgumentException.class, () -> marriageManager.isMarried(dni) );
        assertFalse(marriageManager.isMarried(dniSpouse));
    }
}
