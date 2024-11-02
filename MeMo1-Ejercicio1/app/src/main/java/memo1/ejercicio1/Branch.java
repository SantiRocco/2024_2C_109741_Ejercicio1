package memo1.ejercicio1;

public class Branch {
    private int numberOfInstance;
    private int numberOfAccountsOfBranch;
    private String name;
    private String address;
    private AccountManager accountManager;
    private ClientManager clientManager;

    private boolean accountIsInThisBranch(Long cbu) {
        return numberOfInstance == accountManager.getAccount(cbu).getBranch();
    }

    private boolean accountIsInThisBranch(String alias) {
        return numberOfInstance == accountManager.getAccount(alias).getBranch();
    }

    public Branch(int numberOfInstance, String name, String address) {
        if (numberOfInstance <= 0) {
            throw new IllegalArgumentException("Branch cannot have negative or zero number as identifier.");
        }
        this.numberOfAccountsOfBranch = 0;
        this.numberOfInstance = numberOfInstance;
        this.name = name;
        this.address = address;
        this.accountManager = AccountManager.getInstance();
        this.clientManager = ClientManager.getInstance();
        //this.marriageManager = MarriageManager.getInstance();
        //this.transactionsManager = TransactionsManager.getInstance();
    }

    public int getNumberOfBranch() {
        return numberOfInstance;
    }

    public int getNumberOfAccountsOfBranch() {
        return numberOfAccountsOfBranch;
    }

    public void createAccount(Long cbu, String alias, double balance, int clientDni) {
        Client owner = clientManager.getClient(clientDni);
        accountManager.createAccount(cbu, alias, balance, owner, numberOfInstance);
        numberOfAccountsOfBranch++;
    }

    public void deleteAccount(Long cbu) {
        if (!accountIsInThisBranch(cbu)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot delete.");
        }
        accountManager.deleteAccount(cbu);
        numberOfAccountsOfBranch--;
    }

    public void deleteAccount(String alias) {
        if (!accountIsInThisBranch(alias)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot delete.");
        }
        accountManager.deleteAccount(alias);
        numberOfAccountsOfBranch--;
    }

    public Account getAccount(Long cbu) {
        if (!accountIsInThisBranch(cbu)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot get it.");
        }
        return accountManager.getAccount(cbu);
    }

    public Account getAccount(String alias) {
        if (!accountIsInThisBranch(alias)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot get it.");
        }
        return accountManager.getAccount(alias);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void changeAddress(String newAddress){
        this.address = newAddress;
    }

}
