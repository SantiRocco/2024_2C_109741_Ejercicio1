package memo1.ejercicio1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class BankSystem {
    private static BankSystem systemInstance;
    private int numberOfBranches;
    private Map<Integer, Branch> branches;
    private ClientManager clientManager;
    private AccountManager accountManager;
    private MarriageManager marriageManager;
    private TransactionsManager transactionsManager;

    private BankSystem() {
        this.numberOfBranches = 0;
        this.branches = new HashMap<Integer, Branch>();
        this.clientManager = new ClientManager();
        this.accountManager = new AccountManager();
        this.marriageManager = new MarriageManager(clientManager);
        this.transactionsManager = new TransactionsManager();
    }

    public static BankSystem getInstance() {
        if (systemInstance == null) {
        systemInstance = new BankSystem();
        }
        return systemInstance;
    }

    public void clearEntireSystem() {
        this.numberOfBranches = 0;
        this.branches = new HashMap<Integer, Branch>();
        this.clientManager = new ClientManager();
        this.accountManager = new AccountManager();
        this.marriageManager = new MarriageManager(clientManager);
        this.transactionsManager = new TransactionsManager();
    }

    public int getNumberOfBranches() {
        return numberOfBranches;
    }

    public boolean branchExists(int numberOfBranch) {
        return branches.get(numberOfBranch) != null;
    }

    public int createBranch(String name, String address) {
        int branchNumber = numberOfBranches + 1;
        Branch newBranch = new Branch(branchNumber, name, address, this.accountManager, this.clientManager);
        branches.put(branchNumber, newBranch);
        numberOfBranches = branchNumber;
        return numberOfBranches;
    }

    // Para modificar información de las sucursales o crear nuevas cuentas asociadas a las mismas.
    public Branch getBranch(int numberOfBranch) {
        if (!branchExists(numberOfBranch)) {
            throw new IllegalArgumentException("Branch specified does not exist.");
        }
        return branches.get(numberOfBranch);
    }

    public boolean accountExists(Long cbu) {
        return accountManager.accountExists(cbu);
    }

    public boolean accountExists(String alias) {
        return accountManager.accountExists(alias);
    }

    public Account getAccount(Long cbu) {
        return accountManager.getAccount(cbu);
    }

    public Account getAccount(String alias) {
        return accountManager.getAccount(alias);
    }

    public boolean clientExists(int dni) {
        return clientManager.clientExists(dni);
    }

    public Client getClient(int dni) {
        return clientManager.getClient(dni);
    }

    public void addClient(int dni, String surname, String name, LocalDate birthdate, String address) {
        clientManager.addClient(dni, surname, name, birthdate, address);
    }

    public void deleteClient(int dni) {
        clientManager.deleteClient(dni, marriageManager);
    }

    public void newMarriage(LocalDate date, int newSpouse1Dni, int newSpouse2Dni) {
        marriageManager.newMarriage(date, newSpouse1Dni, newSpouse2Dni);
    }

    public boolean isMarried(int dni) {
        return marriageManager.isMarried(dni);
    }

    public LocalDate getDateOfMarriage(int dni) {
        return marriageManager.getDateOfMarriage(dni);
    }

    public int getSpouseOfMarriedClientDni(int dni) {
        return marriageManager.getSpouseOfMarriedClientDni(dni);
    }

    public void deleteMarriage(int clientDni) {
        marriageManager.deleteMarriage(clientDni);
    }

    public Transaction getTransaction(int id) {
        return transactionsManager.getTransaction(id);
    }

    public int deposit(Long cbu, double amount) {
        Account account = accountManager.getAccount(cbu);
        account.deposit(amount);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return transactionsManager.saveTransaction(currentDate, currentTime, TransactionType.DEPOSIT, amount, account.getCbu());
    }

    public int deposit(String alias, double amount) {
        Account account = accountManager.getAccount(alias);
        account.deposit(amount);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return transactionsManager.saveTransaction(currentDate, currentTime, TransactionType.DEPOSIT, amount, account.getCbu());
    }

    public int withdraw(int relatedDni, Long cbu, double amount) {
        Account account = accountManager.getAccount(cbu);
        account.withdraw(relatedDni, amount);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return transactionsManager.saveTransaction(currentDate, currentTime, TransactionType.WITHDRAWAL, amount, account.getCbu());
    }

    public int withdraw(int relatedDni, String alias, double amount) {
        Account account = accountManager.getAccount(alias);
        account.withdraw(relatedDni, amount);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return transactionsManager.saveTransaction(currentDate, currentTime, TransactionType.WITHDRAWAL, amount, account.getCbu());
    }

    public int transfer(int relatedDni, Long starterCbu, Long targetCbu, double amount) {
        Account starterAccount = accountManager.getAccount(starterCbu);
        Account targetAccount = accountManager.getAccount(targetCbu);
        starterAccount.transfer(relatedDni, amount, targetAccount);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return transactionsManager.saveTransaction(currentDate, currentTime, TransactionType.TRANSFER, amount, starterCbu, targetCbu);
    }

    public int transfer(int relatedDni, String starterAlias, String targetAlias, double amount) {
        Account starterAccount = accountManager.getAccount(starterAlias);
        Account targetAccount = accountManager.getAccount(targetAlias);
        starterAccount.transfer(relatedDni, amount, targetAccount);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return transactionsManager.saveTransaction(currentDate, currentTime, TransactionType.TRANSFER, amount, starterAccount.getCbu(), targetAccount.getCbu());
    }

    // Mismas funciones de transacciones de arriba, con la diferencia de que se puede pasar una fecha y hora específicas.
    // Creadas exclusivamente por motivos de testeo con tests unitarios y Gherkin/Cucumber.

    public int deposit(LocalDate passedDate, LocalTime passedTime, Long cbu, double amount) {
        Account account = accountManager.getAccount(cbu);
        account.deposit(amount);

        return transactionsManager.saveTransaction(passedDate, passedTime, TransactionType.DEPOSIT, amount, account.getCbu());
    }

    public int deposit(LocalDate passedDate, LocalTime passedTime, String alias, double amount) {
        Account account = accountManager.getAccount(alias);
        account.deposit(amount);

        return transactionsManager.saveTransaction(passedDate, passedTime, TransactionType.DEPOSIT, amount, account.getCbu());
    }

    public int withdraw(LocalDate passedDate, LocalTime passedTime, int relatedDni, Long cbu, double amount) {
        Account account = accountManager.getAccount(cbu);
        account.withdraw(relatedDni, amount);

        return transactionsManager.saveTransaction(passedDate, passedTime, TransactionType.WITHDRAWAL, amount, account.getCbu());
    }

    public int withdraw(LocalDate passedDate, LocalTime passedTime, int relatedDni, String alias, double amount) {
        Account account = accountManager.getAccount(alias);
        account.withdraw(relatedDni, amount);

        return transactionsManager.saveTransaction(passedDate, passedTime, TransactionType.WITHDRAWAL, amount, account.getCbu());
    }

    public int transfer(LocalDate passedDate, LocalTime passedTime, int relatedDni, Long starterCbu, Long targetCbu, double amount) {
        Account starterAccount = accountManager.getAccount(starterCbu);
        Account targetAccount = accountManager.getAccount(targetCbu);
        starterAccount.transfer(relatedDni, amount, targetAccount);

        return transactionsManager.saveTransaction(passedDate, passedTime, TransactionType.TRANSFER, amount, starterCbu, targetCbu);
    }

    public int transfer(LocalDate passedDate, LocalTime passedTime, int relatedDni, String starterAlias, String targetAlias, double amount) {
        Account starterAccount = accountManager.getAccount(starterAlias);
        Account targetAccount = accountManager.getAccount(targetAlias);
        starterAccount.transfer(relatedDni, amount, targetAccount);

        return transactionsManager.saveTransaction(passedDate, passedTime, TransactionType.TRANSFER, amount, starterAccount.getCbu(), targetAccount.getCbu());
    }
}
