package memo1.ejercicio1;

public class Account {
    private Long cbu;
    private String alias;
    private double balance;

    public Account() {
        this.balance = 0.0;
    }

    public Account(Long cbu, String alias) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.cbu = cbu;
        this.alias = alias;
        this.balance = 0.0;
    }

    public Account(Long cbu, String alias, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.cbu = cbu;
        this.alias = alias;
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount < 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean transfer(double amount, Account otherAccount) {
        if ( amount <= 0 || this.getBalance() < amount ) {
            return false;
        } else if ( otherAccount.getCbu() == null || otherAccount.alias == null) {
            return false;
        } else if ( this.getCbu().equals( otherAccount.getCbu() ) || this.getCbu().equals( otherAccount.getCbu() ) ) {
            return false;
        }
        this.withdraw(amount);
        otherAccount.deposit(amount);
        return true;
    }

}
