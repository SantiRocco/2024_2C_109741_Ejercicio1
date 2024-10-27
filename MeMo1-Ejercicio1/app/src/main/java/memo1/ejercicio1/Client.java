package memo1.ejercicio1;

public class Client {
    private int dni;
    private String surname;
    private String name;
    private String birthDate;
    private String address;

    public Client(int dni, String surname, String name, String birthDate, String address) {
        this.dni = dni;
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

    public String getBirthDate(){
        return birthDate;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }


}
