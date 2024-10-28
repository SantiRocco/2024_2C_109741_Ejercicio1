package memo1.ejercicio1;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {
    private Map<Integer, Client> clients;
    private MarriageManager marriageManager;

    public boolean clientExists(int dni) {
        Client result = clients.get(dni);
        return result != null;
    }

    public Client getClient(int dni) {
        if (!this.clientExists(dni)){
            throw new IllegalArgumentException("Client does not exist.");
        }
        return clients.get(dni);
    }

    public void addClient(Client newClient) {
        int newClientDni = newClient.getDni();
        if (this.clientExists(newClientDni)){
            throw new IllegalArgumentException("Client already exists");
        }
        clients.put(newClientDni, newClient);
    }

    public boolean isMarried(int dni) {
        return marriageManager.isMarried(dni);
    }

    public String getDateOfMarriage(int dni) {
        return marriageManager.getDateOfMarriage(dni);
    }

    public Client getMarriedClient(int dni) {
        return marriageManager.getMarriedClient(dni);
    }

    public Client getSpouseOfClient(int dni) {
        return marriageManager.getSpouseOfClient(dni);
    }

    
    public void addMarriage(Marriage newMarriage) {
        marriageManager.saveMarriage(newMarriage);
    }
}
