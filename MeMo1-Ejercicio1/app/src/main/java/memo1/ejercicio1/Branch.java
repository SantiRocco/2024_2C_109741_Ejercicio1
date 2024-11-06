package memo1.ejercicio1;

public class Branch {
    private int numberOfInstance;
    private int numberOfAccountsOfBranch;
    private String name;
    private String address;
    private AccountManager accountManager;
    private ClientManager clientManager;
    private boolean isActive;

    private boolean accountIsInThisBranch(Long cbu) {
        return numberOfInstance == accountManager.getAccount(cbu).getBranch();
    }

    private boolean accountIsInThisBranch(String alias) {
        return numberOfInstance == accountManager.getAccount(alias).getBranch();
    }

    private void checkIfInactive() {
        if (!isActive()) {
            throw new IllegalArgumentException("This branch is inactive. No operations with it are not available.");
        }
    }

    public Branch(int numberOfInstance, String name, String address, AccountManager accountManager, ClientManager clientManager) {
        if (numberOfInstance <= 0) {
            throw new IllegalArgumentException("Branch cannot have negative or zero number as identifier.");
        }
        activate();
        this.numberOfAccountsOfBranch = 0;
        this.numberOfInstance = numberOfInstance;
        this.name = name;
        this.address = address;
        this.accountManager = accountManager;
        this.clientManager = clientManager;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void inactivate() {
        if (numberOfAccountsOfBranch > 0) {
            throw new IllegalArgumentException("Branch cannot be inactivated when it still has related accounts");
        }
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public int getNumberOfBranch() {
        checkIfInactive();
        return numberOfInstance;
    }

    public int getNumberOfAccountsOfBranch() {
        checkIfInactive();
        return numberOfAccountsOfBranch;
    }

    public void createAccount(Long cbu, String alias, int clientDni) {
        checkIfInactive();
        Client owner = clientManager.getClient(clientDni);
        accountManager.createAccount(cbu, alias, 0.0, owner, numberOfInstance);
        numberOfAccountsOfBranch++;
    }

    public void createAccount(Long cbu, String alias, double balance, int clientDni) {
        checkIfInactive();
        Client owner = clientManager.getClient(clientDni);
        accountManager.createAccount(cbu, alias, balance, owner, numberOfInstance);
        numberOfAccountsOfBranch++;
    }

    public void deleteAccount(Long cbu) {
        checkIfInactive();
        if (!accountIsInThisBranch(cbu)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot delete.");
        }
        accountManager.deleteAccount(cbu);
        numberOfAccountsOfBranch--;
    }

    public void deleteAccount(String alias) {
        checkIfInactive();
        if (!accountIsInThisBranch(alias)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot delete.");
        }
        accountManager.deleteAccount(alias);
        numberOfAccountsOfBranch--;
    }

    public Account getAccount(Long cbu) {
        checkIfInactive();
        if (!accountIsInThisBranch(cbu)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot get it.");
        }
        return accountManager.getAccount(cbu);
    }

    public Account getAccount(String alias) {
        checkIfInactive();
        if (!accountIsInThisBranch(alias)) {
            throw new IllegalArgumentException("Account does not belong to this branch. Cannot get it.");
        }
        return accountManager.getAccount(alias);
    }

    public String getName() {
        checkIfInactive();
        return name;
    }

    public String getAddress() {
        checkIfInactive();
        return address;
    }

    public void changeName(String newName){
        checkIfInactive();
        this.name = newName;
    }

    public void changeAddress(String newAddress){
        checkIfInactive();
        this.address = newAddress;
    }
}
