package memo1.ejercicio1;

public class Marriage {
    private String marriageDate;
    private Client spouse1;
    private Client spouse2;

    public Marriage(String marriageDate, Client client1, Client client2) {
        if (client1.getDni() == client2.getDni()) {
            throw new IllegalArgumentException("Client cannot be married to itself.");
        }
        this.marriageDate = marriageDate;
        this.spouse1 = client1;
        this.spouse2 = client2;
    }

    public String getDate() {
        return marriageDate;
    }

    public Client getFirstSpouse(){
        return spouse1;
    }

    public Client getSecondSpouse(){
        return spouse2;
    }

    public boolean isInMarriage(Client foreignClient) {
        return ( foreignClient.getDni() == spouse1.getDni() || foreignClient.getDni() == spouse2.getDni() );
    }

}