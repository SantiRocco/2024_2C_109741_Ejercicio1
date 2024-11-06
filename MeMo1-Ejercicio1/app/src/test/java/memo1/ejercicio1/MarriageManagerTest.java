package memo1.ejercicio1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class MarriageManagerTest {

    @Test
    void marriageManagerShouldSaveMarriageCorrectly() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        assertEquals(1, manager.getNumberOfMarriages());

        assertTrue(manager.isMarried(dni));
        assertTrue(manager.isMarried(dniSpouse));
        assertEquals(dniSpouse, manager.getSpouseOfMarriedClientDni(dni));
        assertEquals(marriageDate, manager.getDateOfMarriage(dni));

        manager.deleteMarriage(dni);
    }


    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToSaveMarriageWithOneDniBeingNegative() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.newMarriage(marriageDate, -1, dniSpouse) );
    }
    
    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToSaveMarriageWithOneDniBeingZero() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.newMarriage(marriageDate, 0, dniSpouse) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToSaveCopyOfMarriage() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        assertThrows(IllegalArgumentException.class, () -> manager.newMarriage(marriageDate, dni, dniSpouse) );

        manager.deleteMarriage(dni);
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToSaveMarriageWithClientAlreadyMarried() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        int dniThirdClient = 11122233;
        String surnameThirdClient = "Ferreira";
        String nameThirdClient = "Maria";
        LocalDate birthDateThirdClient = LocalDate.of(2002, Month.JUNE, 10);
        String addressThirdClient = "Av. Acoyte 245";

        LocalDate amotnerMarriageDate = LocalDate.of(2022, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);
        clientManager.addClient(dniThirdClient, surnameThirdClient, nameThirdClient, birthDateThirdClient, addressThirdClient);

        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        assertThrows(IllegalArgumentException.class, () -> manager.newMarriage(amotnerMarriageDate, dniThirdClient, dniSpouse) );

        manager.deleteMarriage(dni);
    }

    @Test
    void marriageManagerShouldCorrectlyDetectThatMarriedPersonIsMarried() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        assertTrue(manager.isMarried(dni));
        assertTrue(manager.isMarried(dniSpouse));

        manager.deleteMarriage(dni);
    }

    @Test
    void marriageManagerShouldCorrectlyDetectThatNotMarriedPersonIsNotMarried() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);

        MarriageManager manager = new MarriageManager(clientManager);

        assertFalse(manager.isMarried(dni));
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenSearchingIfClientIsMarriedThroughANegativeDni() {
        int negativeDni = -1;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.isMarried(negativeDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenSearchingIfClientIsMarriedThroughADniEqualToZero() {
        int zeroDni = 0;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.isMarried(zeroDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenSearchingIfPersonWhoIsNotAClientIsMarried() {
        int notClientDni = 999999;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.isMarried(notClientDni) );
    }

    @Test
    void marriageManagerShouldCorrectlyReturnDateOfMarriageOfMarriedPerson() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);
        
        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        assertEquals(marriageDate, manager.getDateOfMarriage(dni));
        assertEquals(marriageDate, manager.getDateOfMarriage(dniSpouse));

        manager.deleteMarriage(dni);
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenSearchingForDateOfMarriageForUnmarriedClient() {
        int notMarriedClientDni = 12345678;
        String notMarriedClientSurname = "Fernandez";
        String notMarriedClientName = "Martin";
        LocalDate notMarriedClientBirthDate = LocalDate.of(2000, Month.MAY, 17);
        String notMarriedClientAddress = "Av. Acoyte 245";
        
        ClientManager clientManager = new ClientManager();

        clientManager.addClient(notMarriedClientDni, notMarriedClientSurname, notMarriedClientName, notMarriedClientBirthDate, notMarriedClientAddress);

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getDateOfMarriage(notMarriedClientDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenSearchingForDateOfMarriageThroughANegativeDni() {
        int negativeDni = -1;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getDateOfMarriage(negativeDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenSearchingForDateOfMarriageThroughAZeroDni() {
        int zeroDni = 0;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getDateOfMarriage(zeroDni) );
    }

    @Test
    void marriageManagerShouldCorrectlyGetSpouseOfMarriedClient() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        int spouseOfClient1 = manager.getSpouseOfMarriedClientDni(dni);
        int spouseOfClient2 = manager.getSpouseOfMarriedClientDni(dniSpouse);

        assertEquals( dniSpouse, spouseOfClient1 );
        assertEquals( dni, spouseOfClient2 );

        manager.deleteMarriage(dni);
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToGetSpouseOfNotMarriedPerson() {
        int notMarriedClientDni = 10000000;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getSpouseOfMarriedClientDni(notMarriedClientDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToGetSpouseOfMarriedPersonThroughANegativeDni() {
        int notMarriedClientDni = -1;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getSpouseOfMarriedClientDni(notMarriedClientDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToGetSpouseOfMarriedPersonThroughAZeroDni() {
        int notMarriedClientDni = 0;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getSpouseOfMarriedClientDni(notMarriedClientDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToGetSpouseOfPersonWhoIsNotClient() {
        int notClientDni = 999999;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.getSpouseOfMarriedClientDni(notClientDni) );
    }

    @Test
    void marriageManagerShouldLeaveNoTraceOfMarriageWhenDeletingMarriage() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        clientManager.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);
        
        MarriageManager manager = new MarriageManager(clientManager);

        manager.newMarriage(marriageDate, dni, dniSpouse);

        manager.deleteMarriage(dniSpouse);

        assertEquals(0, manager.getNumberOfMarriages());

        assertFalse(manager.isMarried(dni));
        assertFalse(manager.isMarried(dniSpouse));
        assertThrows(IllegalArgumentException.class, () -> manager.getSpouseOfMarriedClientDni(dni) );
        assertThrows(IllegalArgumentException.class, () -> manager.getSpouseOfMarriedClientDni(dniSpouse) );
        assertThrows(IllegalArgumentException.class, () -> manager.getDateOfMarriage(dni) );
        assertThrows(IllegalArgumentException.class, () -> manager.getDateOfMarriage(dniSpouse) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToDeleteMarriageWithNegativeDni() {
        int negativeDni = -1;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.deleteMarriage(negativeDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToDeleteMarriageWithZeroDni() {
        int zeroDni = 0;

        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.deleteMarriage(zeroDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToDeleteMarriageWithNotClientDni() {
        int notClientDni = 9999999;
        
        ClientManager clientManager = new ClientManager();

        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.deleteMarriage(notClientDni) );
    }

    @Test
    void marriageManagerShouldThrowExceptionWhenTryingToDeleteNonExistentMarriage() {
        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        ClientManager clientManager = new ClientManager();

        clientManager.addClient(dni, surname, name, birthDate, address);
        
        MarriageManager manager = new MarriageManager(clientManager);

        assertThrows(IllegalArgumentException.class, () -> manager.deleteMarriage(dni) );

        clientManager.deleteClient(dni, manager);
    }
}
