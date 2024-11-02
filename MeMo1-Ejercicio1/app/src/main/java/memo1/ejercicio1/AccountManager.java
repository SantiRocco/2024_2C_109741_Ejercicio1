package memo1.ejercicio1;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static AccountManager managerInstance;
    private int numberOfAccounts;
    private Map<Long, Account> accountsCbuAsKey;
    private Map<String, Account> accountsAliasAsKey;

    private AccountManager() {
        numberOfAccounts = 0;
        accountsCbuAsKey = new HashMap<Long, Account>();
        accountsAliasAsKey = new HashMap<String, Account>();
    }

    private void checkIfAccountExists(Long cbu, String alias) {
        if (accountExists(cbu) || accountExists(alias)){
            throw new IllegalArgumentException("Account already exists");
        }
    }

    public static AccountManager getInstance() {
        if (managerInstance == null) {
            managerInstance = new AccountManager();
        }
        return managerInstance;
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
        Account clientToRemove = accountsCbuAsKey.get(cbu);
        String alias = getAccount(cbu).getAlias();

        clientToRemove.disassociateOwnerAndCoOwners();

        accountsCbuAsKey.remove(cbu);
        accountsAliasAsKey.remove(alias);
        numberOfAccounts--;
    }

    
    public void deleteAccount(String alias) {
        if (!accountExists(alias)){
            throw new IllegalArgumentException("Account does not exist.");
        }
        Account clientToRemove = accountsAliasAsKey.get(alias);
        Long cbu = getAccount(alias).getCbu();

        clientToRemove.disassociateOwnerAndCoOwners();

        accountsCbuAsKey.remove(cbu);
        accountsAliasAsKey.remove(alias);
        numberOfAccounts--;
    }
}
