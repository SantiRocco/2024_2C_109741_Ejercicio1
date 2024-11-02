package memo1.ejercicio1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MarriageManager {
    private static MarriageManager managerInstance;
    private Map<Integer, Marriage> marriagesClient1AsKey;
    private Map<Integer, Marriage> marriagesClient2AsKey;

    private MarriageManager() {
        marriagesClient1AsKey = new HashMap<Integer, Marriage>();
        marriagesClient2AsKey = new HashMap<Integer, Marriage>();
    }

    private void checkIfDniIsInvalid(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("DNI cannot be negative or zero.");
        } else if (!ClientManager.getInstance().clientExists(dni)) {
            throw new IllegalArgumentException("DNI must be of existent client.");
        }
    }

    private void deleteClient(int dni) {
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        if (resultMap1 != null) {
            marriagesClient1AsKey.remove(dni);
        } else {
            marriagesClient2AsKey.remove(dni);
        }
    }

    public static MarriageManager getInstance() {
        if (managerInstance == null) {
            managerInstance = new MarriageManager();
        }
        return managerInstance;
    }

    public boolean isMarried(int dni) {
        checkIfDniIsInvalid(dni);
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        Marriage resultMap2 = marriagesClient2AsKey.get(dni);
        boolean finalResult = (resultMap1 != null || resultMap2 != null);
        return finalResult;
    }

    public LocalDate getDateOfMarriage(int dni) {
        if (!isMarried(dni)) {
            throw new IllegalArgumentException("Client specified is not married.");
        }
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        Marriage resultMap2 = marriagesClient2AsKey.get(dni);
        if (resultMap1 != null) {
            return resultMap1.getDate();
        } else {
            return resultMap2.getDate();
        }
    }

    public int getSpouseOfMarriedClientDni(int dni) {
        if (!isMarried(dni)){
            throw new IllegalArgumentException("Client specified is not married.");
        }
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        Marriage resultMap2 = marriagesClient2AsKey.get(dni);
        if (resultMap1 != null) {
            return resultMap1.getSecondSpouseDni();
        } else {
            return resultMap2.getFirstSpouseDni();
        }
    }

    public void newMarriage(LocalDate date, int newSpouse1Dni, int newSpouse2Dni) {
        if ( isMarried(newSpouse1Dni) || isMarried(newSpouse2Dni) ){
            throw new IllegalArgumentException("One client is already married");
        }
        Marriage newMarriage = new Marriage(date, newSpouse1Dni, newSpouse2Dni);
        marriagesClient1AsKey.put(newSpouse1Dni, newMarriage);
        marriagesClient2AsKey.put(newSpouse2Dni, newMarriage);
    }

    public void deleteMarriage(int clientDni) {
        if (!isMarried(clientDni)){
            throw new IllegalArgumentException("Client specified is not married.");
        }
        int spouseOfClientDni = getSpouseOfMarriedClientDni(clientDni);
        deleteClient(clientDni);
        deleteClient(spouseOfClientDni);
    }
}