package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

// Pruebas unitarias

class ClientTest {

    @Test
    void clientShouldInitializeWithCorrectValues() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        
        assertEquals(12345678, client.getDni());
        assertEquals("Fernandez", client.getSurname());
        assertEquals("Martin", client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals("Av. Acoyte 245", client.getAddress());
    }

    @Test
    void constructorShouldThrowExceptionIfDniIsNegative() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        assertThrows(IllegalArgumentException.class, () -> new Client(-1, "Fernandez", "Martin", birthDate, "Av. Acoyte 245") );
    }

    @Test
    void constructorShouldThrowExceptionIfDniIsZero() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        assertThrows(IllegalArgumentException.class, () -> new Client(0, "Fernandez", "Martin", birthDate, "Av. Acoyte 245") );
    }

    @Test
    void clientShouldBeAbleToChangeNameSurnameAndAddress() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        
        client.setSurname("Pirlo");
        client.setName("Pepe");
        client.setAddress("Av. Paseo Colon 854");
        
        assertEquals("Pirlo", client.getSurname());
        assertEquals("Pepe", client.getName());
        assertEquals("Av. Paseo Colon 854", client.getAddress());
    }

    @Test
    void clientShouldBeAbleToIncreaseItsRelatedAccountsCounter() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        
        client.oneMoreRelatedAccount();
        
        assertEquals(1, client.getNumberOfRelatedAccounts());
    }

    @Test
    void clientShouldBeAbleToDecreaseItsRelatedAccountsCounter() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        
        client.oneMoreRelatedAccount();
        client.oneLessRelatedAccount();

        assertEquals(0, client.getNumberOfRelatedAccounts());
    }

    @Test
    void clientShouldThrowExceptionWhenTryingToDecreaseRelatedAccountsCounterWhenItIsAtZero() {
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        Client client = new Client(12345678, "Fernandez", "Martin", birthDate, "Av. Acoyte 245");
        
        assertThrows(IllegalArgumentException.class, () -> client.oneLessRelatedAccount() );
    }
}

