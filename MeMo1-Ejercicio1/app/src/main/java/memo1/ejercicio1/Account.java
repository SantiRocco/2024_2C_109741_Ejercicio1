package memo1.ejercicio1;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private Long cbu;
    private String alias;
    private double balance;
    private Client owner;
    private Map<Integer, Client> coOwners;
    private int relatedBranch;

    private void checkIfBalanceIsInvalid(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("balance cannot be negative.");
        }
    }

    private void checkIfBranchNumberIsInvalid(int branchNumber) {
        if (branchNumber <= 0) {
            throw new IllegalArgumentException("Branch number cannot be negative.");
        }
    }

    private void checkIfClientIsNonExistent(Client owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Client must exist.");
        }
    }

    private void checkIfCbuIsInvalid(Long cbu) {
        if (cbu <= 0) {
            throw new IllegalArgumentException("CBU passed is not in correct format.");
        }
    }

    private void checkIfClientIsRelatedToAccount(int dni) {
        if (!isOwner(dni) && !isCoOwner(dni)) {
            throw new IllegalArgumentException("DNI passed must correspond to the owner or one of the co-owners");
        }
    }

    public Account(Long cbu, String alias, Client owner, int branch) {
        checkIfBalanceIsInvalid(balance);
        checkIfBranchNumberIsInvalid(branch);
        checkIfClientIsNonExistent(owner);
        checkIfCbuIsInvalid(cbu);
        this.cbu = cbu;
        this.alias = alias;
        this.balance = 0.0;
        this.owner = owner;
        this.coOwners = new HashMap<Integer, Client>();
        this.relatedBranch = branch;

        owner.oneMoreRelatedAccount();
    }

    public Account(Long cbu, String alias, double balance, Client owner, int branch) {
        checkIfBalanceIsInvalid(balance);
        checkIfBranchNumberIsInvalid(branch);
        checkIfClientIsNonExistent(owner);
        checkIfCbuIsInvalid(cbu);
        this.cbu = cbu;
        this.alias = alias;
        this.balance = balance;
        this.owner = owner;
        this.coOwners = new HashMap<Integer, Client>();
        this.relatedBranch = branch;

        owner.oneMoreRelatedAccount();
    }

    public Long getCbu() {
        return cbu;
    }

    public String getAlias() {
        return alias;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isOwner(int passedDni) {
        return passedDni == owner.getDni();
    }

    public boolean isCoOwner(int dni) {
        Client result = coOwners.get(dni);
        return result != null;
    }

    public int getNumberOfCoOwners() {
        return coOwners.size();
    }
    
    public void setNewCoOwner(Client newCoOwner) {
        if (this.isCoOwner(newCoOwner.getDni()) || newCoOwner.getDni() == owner.getDni() ){
            throw new IllegalArgumentException("Client already included in account.");
        }
        newCoOwner.oneMoreRelatedAccount();
        coOwners.put(newCoOwner.getDni(), newCoOwner);
    }

    public void removeCoOwner(int dni) {
        if (!this.isCoOwner(dni)){
            throw new IllegalArgumentException("Client not included in account.");
        }
        coOwners.get(dni).oneLessRelatedAccount();
        coOwners.remove(dni);
    }

    public int getBranch() {
        return relatedBranch; 
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount to deposit cannot be nagative or zero.");
        }
        balance += amount;
    }

    public void withdraw(int relatedDni, double amount) {
        checkIfClientIsRelatedToAccount(relatedDni);
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Amount to withdraw cannot be nagative or zero.");
        }
        balance -= amount;
    }

    public void transfer(int relatedDni, double amount, Account otherAccount) {
        checkIfClientIsRelatedToAccount(relatedDni);
        if ( amount <= 0 ) {
            throw new IllegalArgumentException("Amount to transfer cannot be nagative or zero.");
        } else if ( this.getBalance() < amount ) {
            throw new IllegalArgumentException("Not enough money on account.");
        } else if ( this.getCbu().equals( otherAccount.getCbu() ) && this.getAlias().equals( otherAccount.getAlias() ) ) {
            throw new IllegalArgumentException("Cannot transfer to same account.");
        }
        this.withdraw(relatedDni, amount);
        otherAccount.deposit(amount);
    }

    // Esta función debería ser usada solo en caso de eliminar la cuenta desde AccountManager.
    public void disassociateOwnerAndCoOwners() {
        owner.oneLessRelatedAccount();
        coOwners.forEach((dni,client) -> client.oneLessRelatedAccount() );
    }

}
