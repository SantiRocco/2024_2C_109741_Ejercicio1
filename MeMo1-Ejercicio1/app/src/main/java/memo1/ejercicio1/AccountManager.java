package memo1.ejercicio1;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private int numberOfAccounts;
    private Map<Long, Account> accountsCbuAsKey;
    private Map<String, Account> accountsAliasAsKey;

    public AccountManager() {
        numberOfAccounts = 0;
        accountsCbuAsKey = new HashMap<Long, Account>();
        accountsAliasAsKey = new HashMap<String, Account>();
    }

    private void checkIfAccountExists(Long cbu, String alias) {
        if (accountExists(cbu) || accountExists(alias)){
            throw new IllegalArgumentException("Account already exists");
        }
    }

    public boolean accountExists(Long cbu) {
        Account result = accountsCbuAsKey.get(cbu);
        return result != null;
    }

    public boolean accountExists(String alias) {
        Account result = accountsAliasAsKey.get(alias);
        return result != null;
    }

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public Account getAccount(Long cbu) {
        if (!accountExists(cbu)){
            throw new IllegalArgumentException("Account does not exist.");
        }
        return accountsCbuAsKey.get(cbu);
    }

    public Account getAccount(String alias) {
        if (!accountExists(alias)){
            throw new IllegalArgumentException("Account does not exist.");
        }
        return accountsAliasAsKey.get(alias);
    }

    public void createAccount(Long cbu, String alias, Client newOwner, int branch) {
        checkIfAccountExists(cbu, alias);
        Account newAccount = new Account(cbu, alias, newOwner, branch);
        accountsCbuAsKey.put(cbu, newAccount);
        accountsAliasAsKey.put(alias, newAccount);
        numberOfAccounts++;
    }

    public void createAccount(Long cbu, String alias, double balance, Client newOwner, int branch) {
        checkIfAccountExists(cbu, alias);
        Account newAccount = new Account(cbu, alias, balance, newOwner, branch);
        accountsCbuAsKey.put(cbu, newAccount);
        accountsAliasAsKey.put(alias, newAccount);
        numberOfAccounts++;
    }

    public void deleteAccount(Long cbu) {
        if (!accountExists(cbu)){
            throw new IllegalArgumentException("Account does not exist.");
        }
        Account accountToRemove = accountsCbuAsKey.get(cbu);
        if (accountToRemove.getBalance() > 0.0) {
            throw new IllegalArgumentException("Cannot delete account with money still in it.");
        }
        String alias = getAccount(cbu).getAlias();

        accountToRemove.disassociateOwnerAndCoOwners();

        accountsCbuAsKey.remove(cbu);
        accountsAliasAsKey.remove(alias);
        numberOfAccounts--;
    }

    
    public void deleteAccount(String alias) {
        if (!accountExists(alias)){
            throw new IllegalArgumentException("Account does not exist.");
        }
        Account accountToRemove = accountsAliasAsKey.get(alias);
        if (accountToRemove.getBalance() > 0.0) {
            throw new IllegalArgumentException("Cannot delete account with money still in it.");
        }
        Long cbu = getAccount(alias).getCbu();

        accountToRemove.disassociateOwnerAndCoOwners();

        accountsCbuAsKey.remove(cbu);
        accountsAliasAsKey.remove(alias);
        numberOfAccounts--;
    }
}
