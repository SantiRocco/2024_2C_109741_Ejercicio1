package memo1.ejercicio1;

import java.util.HashMap;
import java.util.Map;

public class MarriageManager {
    private Map<Integer, Marriage> marriagesClient1AsKey;
    private Map<Integer, Marriage> marriagesClient2AsKey;

    public MarriageManager() {
        marriagesClient1AsKey = new HashMap<Integer, Marriage>();
        marriagesClient2AsKey = new HashMap<Integer, Marriage>();
    }

    public boolean isMarried(int dni) {
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        Marriage resultMap2 = marriagesClient2AsKey.get(dni);
        boolean finalResult = (resultMap1 != null && resultMap2 == null);
        return finalResult;
    }

    public String getDateOfMarriage(int dni) {
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

    public Client getMarriedClient(int dni) {
        if (!isMarried(dni)) {
            throw new IllegalArgumentException("Client specified is not married.");
        }
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        Marriage resultMap2 = marriagesClient2AsKey.get(dni);
        if (resultMap1 != null) {
            return resultMap1.getFirstSpouse();
        } else {
            return resultMap2.getSecondSpouse();
        }
    }

    public Client getSpouseOfClient(int dni) {
        if (!isMarried(dni)){
            throw new IllegalArgumentException("Client specified is not married.");
        }
        Marriage resultMap1 = marriagesClient1AsKey.get(dni);
        Marriage resultMap2 = marriagesClient2AsKey.get(dni);
        if (resultMap1 != null) {
            return resultMap1.getSecondSpouse();
        } else {
            return resultMap2.getFirstSpouse();
        }
    }

    public void saveMarriage(Marriage newMarriage) {
        int newSpouse1Dni = newMarriage.getFirstSpouse().getDni();
        int newSpouse2Dni = newMarriage.getSecondSpouse().getDni();
        if ( isMarried(newSpouse1Dni) || isMarried(newSpouse1Dni) ){
            throw new IllegalArgumentException("One account is already married");
        }
        marriagesClient1AsKey.put(newSpouse1Dni, newMarriage);
        marriagesClient2AsKey.put(newSpouse2Dni, newMarriage);
    }

}