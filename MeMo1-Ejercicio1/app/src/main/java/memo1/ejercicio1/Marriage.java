package memo1.ejercicio1;

import java.time.LocalDate;

public class Marriage {
    private LocalDate marriageDate;
    private int spouse1Dni;
    private int spouse2Dni;

    private void checkIfDniIsInvalid(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("DNI cannot be negative or zero.");
        }
    }

    public Marriage(LocalDate marriageDate, int dni1, int dni2) {
        if (dni1 == dni2) {
            throw new IllegalArgumentException("Client cannot be married to itself.");
        }
        checkIfDniIsInvalid(dni1);
        checkIfDniIsInvalid(dni2);
        this.marriageDate = marriageDate;
        this.spouse1Dni = dni1;
        this.spouse2Dni = dni2;
    }

    public LocalDate getDate() {
        return marriageDate;
    }

    public int getFirstSpouseDni(){
        return spouse1Dni;
    }

    public int getSecondSpouseDni(){
        return spouse2Dni;
    }

    public boolean isInMarriage(int foreignClientDni) {
        return ( foreignClientDni == spouse1Dni || foreignClientDni == spouse2Dni );
    }

}