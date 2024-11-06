package memo1.ejercicio1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class TransactionsManager {
    private int transactionsCount;
    private Map<Integer, Transaction> transactions;

    public TransactionsManager() {
        transactionsCount = 0;
        transactions = new HashMap<Integer, Transaction>();
    }

    public Transaction getTransaction(int idOfTransaction) {
        Transaction transaction = transactions.get(idOfTransaction);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction with passed ID does not exist.");
        }
        return transaction;
    }

    public int saveTransaction(LocalDate date, LocalTime hour, TransactionType type, Double amount, Long cbu){
        Transaction newTransaction = new Transaction(transactionsCount + 1, date, hour, type, amount, cbu);
        transactions.put(newTransaction.getId(), newTransaction);
        transactionsCount = transactionsCount + 1;
        return newTransaction.getId();
    }

    public int saveTransaction(LocalDate date, LocalTime hour, TransactionType type, Double amount, Long starterCbu, Long targetCbu){
        Transaction newTransaction = new Transaction(transactionsCount + 1, date, hour, type, amount, starterCbu, targetCbu);
        transactions.put(newTransaction.getId(), newTransaction);
        transactionsCount = transactionsCount + 1;
        return newTransaction.getId();
    }

}
