package memo1.ejercicio1;

public class Branch {
    private int numberOfInstance;
    private String name;
    private String address;
    private AccountManager accountManager;
    private MarriageManager marriageManager;
    private TransactionsManager transactionsManager;

    public Branch(String name, String address, AccountManager accountManager, MarriageManager marriageManager, TransactionsManager transactionsManager) {
        this.name = name;
        this.address = address;
        this.accountManager = accountManager;
        this.marriageManager = marriageManager;
        this.transactionsManager = transactionsManager;
    }





}
