package memo1.ejercicio1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ClientManager {
    private int numberOfClients;
    private Map<Integer, Client> clients = new HashMap<Integer, Client>();

    public ClientManager() {
        clients = new HashMap<Integer, Client>();
    }

    private void checkIfDniIsInvalid(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("DNI cannot be negative or zero.");
        }
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public boolean clientExists(int dni) {
        checkIfDniIsInvalid(dni);
        Client result = clients.get(dni);
        return result != null;
    }

    public void addClient(int dni, String surname, String name, LocalDate birthdate, String address) {
        Client newClient = new Client(dni, surname, name, birthdate, address);
        if (this.clientExists(dni)){
            throw new IllegalArgumentException("Client already exists");
        }
        clients.put(dni, newClient);
        numberOfClients++;
    }

    public void deleteClient(int dni, MarriageManager marriageManager) {
        if (!this.clientExists(dni)){
            throw new IllegalArgumentException("Client does not exist.");
        } else if (clients.get(dni).getNumberOfRelatedAccounts() > 0) {
            throw new IllegalArgumentException("Cannot delete client with related accounts.");
        }
        if (marriageManager.isMarried(dni)) {
            marriageManager.deleteMarriage(dni);
        }
        clients.remove(dni);
        numberOfClients--;
    }

    public Client getClient(int dni) {
        if (!this.clientExists(dni)){
            throw new IllegalArgumentException("Client does not exist.");
        }
        return clients.get(dni);
    }
}
