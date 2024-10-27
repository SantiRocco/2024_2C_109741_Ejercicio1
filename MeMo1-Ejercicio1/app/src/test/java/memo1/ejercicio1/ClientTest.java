package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Pruebas unitarias

class ClientTest {

    @Test
    void clientShouldInitializeWithCorrectValues() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        
        assertEquals(12345678, client.getDni());
        assertEquals("Fernandez", client.getSurname());
        assertEquals("Martin", client.getName());
        assertEquals("2000-5-17", client.getBirthDate());
        assertEquals("Av. Acoyte 245", client.getAddress());
        
    }

    @Test
    void clientShouldBeAbleToChangeNameSurnameAndAddress() {
        Client client = new Client(12345678, "Fernandez", "Martin", "2000-5-17", "Av. Acoyte 245");
        
        client.setSurname("Pirlo");
        client.setName("Pepe");
        client.setAddress("Av. Paseo Colon 854");
        
        assertEquals("Pirlo", client.getSurname());
        assertEquals("Pepe", client.getName());
        assertEquals("Av. Paseo Colon 854", client.getAddress());
    }

}

