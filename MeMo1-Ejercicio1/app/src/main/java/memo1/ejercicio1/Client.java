package memo1.ejercicio1;

import java.time.LocalDate;

public class Client {
    private int dni;
    private int numberOfRelatedAccounts;
    private String surname;
    private String name;
    private LocalDate birthDate;
    private String address;

    private void checkIfDniIsInvalid(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("DNI cannot be negative or zero.");
        }
    }

    public Client(int dni, String surname, String name, LocalDate birthDate, String address) {
        checkIfDniIsInvalid(dni);
        this.dni = dni;
        this.numberOfRelatedAccounts = 0;
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
    }

    public int getDni(){
        return dni;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String newSurname){
        this.surname = newSurname;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    public int getNumberOfRelatedAccounts() {
        return numberOfRelatedAccounts;
    }

    public void oneMoreRelatedAccount() {
        numberOfRelatedAccounts++;
    }

    public void oneLessRelatedAccount() {
        if (numberOfRelatedAccounts == 0) {
            throw new IllegalArgumentException("Client already has no related accounts.");
        }
        numberOfRelatedAccounts--;
    }

}
