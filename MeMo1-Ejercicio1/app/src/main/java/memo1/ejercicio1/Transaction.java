package memo1.ejercicio1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private int id;
    private LocalDate date;
    private LocalTime hour;
    private TransactionType type;
    private double amount;
    private Long starterCbu;
    private Long targetCbu;
    
    private void checkIfCbuIsInvalid(Long cbu) {
        if (cbu <= 0) {
            throw new IllegalArgumentException("CBU cannot be negative or zero.");
        }
    }

    private void checkIfIdIsInvalid(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be negative or zero.");
        }
    }

    private void checkIfAmountIsInvalid(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount cannot be negative or zero.");
        }
    }

    private void checkCorrectCbuPassedForDepositAndWithdrawalOperations(Long accountCbu) {
        checkIfCbuIsInvalid(accountCbu);
    }

    private void checkCorrectCbusPassedForTransferOperations(Long starterCbu, Long targetCbu) {
        checkIfCbuIsInvalid(starterCbu);
        checkIfCbuIsInvalid(targetCbu);
        if (starterCbu.equals(targetCbu)) {
            throw new IllegalArgumentException("Cannot transfer to same account.");
        }
    }

    public Transaction(int id, LocalDate date, LocalTime hour, TransactionType type, double amount, Long accountCbu) {
        checkIfIdIsInvalid(id);
        if (type == TransactionType.TRANSFER) {
            throw new IllegalArgumentException("Transfer transaction requires two DNIs.");
        }
        checkIfAmountIsInvalid(amount);
        checkCorrectCbuPassedForDepositAndWithdrawalOperations(accountCbu);
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.amount = amount;
        this.starterCbu = accountCbu;
        this.targetCbu = accountCbu;
    }

    public Transaction(int id, LocalDate date, LocalTime hour, TransactionType type, double amount, Long starterCbu, Long targetCbu) {
        checkIfIdIsInvalid(id);
        if (type != TransactionType.TRANSFER) {
            throw new IllegalArgumentException("Deposit and withdrawal transactions require only one DNI.");
        }
        checkIfAmountIsInvalid(amount);
        checkCorrectCbusPassedForTransferOperations(starterCbu, targetCbu);
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.type = type;
        this.amount = amount;
        this.starterCbu = starterCbu;
        this.targetCbu = targetCbu;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }


    public LocalDate getDateOfOperation() {
        return date;
    }

    public LocalTime getHourOfOperation() {
        return hour;
    }

    public TransactionType getTypeOfOperation() {
        return type;
    }

    public Long getStarterAccountCbu() {
        return starterCbu;
    }

    public Long getTargetAccountCbu() {
        return targetCbu;
    }

}