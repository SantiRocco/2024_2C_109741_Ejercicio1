package memo1.ejercicio1;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<Long, Account> accountsCbuAsKey;
    private Map<String, Account> accountsAliasAsKey;

    public AccountManager() {
        accountsCbuAsKey = new HashMap<Long, Account>();
        accountsAliasAsKey = new HashMap<String, Account>();
    }

    public boolean accountExists(Long cbu) {
        Account result = accountsCbuAsKey.get(cbu);
        return result != null;
    }

    public boolean accountExists(String alias) {
        Account result = accountsAliasAsKey.get(alias);
        return result != null;
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

    public void saveAccount(Account newAccount) {
        Long newAccountCbu = newAccount.getCbu();
        if (accountExists(newAccountCbu)){
            throw new IllegalArgumentException("Account already exists");
        }
        String newAccountAlias = newAccount.getAlias();
        accountsCbuAsKey.put(newAccountCbu, newAccount);
        accountsAliasAsKey.put(newAccountAlias, newAccount);
    }

    public void deposit(Long cbu, Double amount) {
        Account account = getAccount(cbu);
        account.deposit(amount);
    }

    public void deposit(String alias, Double amount) {
        Account account = getAccount(alias);
        account.deposit(amount);
    }

    public void withdraw(Long cbu, Double amount) {
        Account account = getAccount(cbu);
        account.withdraw(amount);
    }

    public void withdraw(String alias, Double amount) {
        Account account = getAccount(alias);
        account.withdraw(amount);
    }

    public void transfer(Long senderCbu, Long receiverCbu, Double amount) {
        Account senderAccount = getAccount(senderCbu);
        Account receiverAccount = getAccount(receiverCbu);
        senderAccount.transfer(amount, receiverAccount);
    }

    public void transfer(String senderAlias, String receiverAlias, Double amount) {
        Account senderAccount = getAccount(senderAlias);
        Account receiverAccount = getAccount(receiverAlias);
        senderAccount.transfer(amount, receiverAccount);
    }
}
