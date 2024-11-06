package memo1.ejercicio1;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class BankSystemTest {

    @Test
    void constructorShouldSetNumberOfBranchesToZero() {
        BankSystem system = BankSystem.getInstance();
        assertEquals(0, system.getNumberOfBranches());
    }

    @Test
    void bankSystemShouldCreateAndGetBranchCorrectly() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        Branch branch = system.getBranch(branchNumber);

        assertEquals("Suc. Belgrano", branch.getName());
        assertEquals("Av. Paseo Colon 900", branch.getAddress());
        assertEquals(0, branch.getNumberOfAccountsOfBranch());
        assertEquals(1, system.getNumberOfBranches());
    }

    @Test
    void bankSystemShouldThrowExceptionWhenTryingToGetNonExistentBranch() {
        BankSystem system = BankSystem.getInstance();

        assertThrows(IllegalArgumentException.class, () -> system.getBranch(1000));
    }

    @Test
    void bankSystemShouldDetectThatExistingBranchExists() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        assertTrue(system.branchExists(branchNumber));
    }

    @Test
    void bankSystemShouldDetectThatNonExistingBranchDoesNotExist() {
        BankSystem system = BankSystem.getInstance();

        assertFalse(system.branchExists(999999));
    }

    @Test
    void bankSystemShouldSuccessfullyCheckThatExistingAccountExistsThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0, dni);

        assertTrue(system.accountExists(123456789L));
    }

    @Test
    void bankSystemShouldSuccessfullyCheckThatExistingAccountExistsThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0, dni);

        assertTrue(system.accountExists("iAmAccount"));
    }

    @Test
    void bankSystemShouldSuccessfullyGetAccountThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0, dni);

        Account account = system.getAccount(123456789L);

        assertEquals(0.0, account.getBalance());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertTrue(account.isOwner(dni));
        assertEquals(branchNumber, account.getBranch());
    }

    @Test
    void bankSystemShouldSuccessfullyGetAccountThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0, dni);

        Account account = system.getAccount("iAmAccount");

        assertEquals(0.0, account.getBalance());
        assertEquals(123456789L, account.getCbu());
        assertEquals("iAmAccount", account.getAlias());
        assertTrue(account.isOwner(dni));
        assertEquals(branchNumber, account.getBranch());
    }

    @Test
    void bankSystemShouldSuccessfullyCheckThatExistingClientExists() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        assertTrue(system.clientExists(dni));
    }

    @Test
    void bankSystemShouldSuccessfullyCheckThatNonExistingClientDoesNotExist() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;

        assertFalse(system.clientExists(dni));
    }

    @Test
    void bankSystemShouldSuccessfullyGetClient() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Client client = system.getClient(dni);

        assertEquals(12345678, client.getDni());
        assertEquals("Fernandez", client.getSurname());
        assertEquals("Martin", client.getName());
        assertEquals(birthDate, client.getBirthDate());
        assertEquals("Av. Acoyte 245", client.getAddress());
    }

    @Test
    void bankSystemShouldSuccessfullyCheckThatMarriedClientIsMarried() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        system.addClient(dni, surname, name, birthDate, address);
        system.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        system.newMarriage(marriageDate, dni, dniSpouse);

        assertTrue(system.isMarried(dni));
        assertTrue(system.isMarried(dniSpouse));
    }

    @Test
    void bankSystemShouldSuccessfullyCheckThatNotMarriedClientIsNotMarried() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        assertFalse(system.isMarried(dni));
    }

    @Test
    void bankSystemShouldSuccessfullyGetDateOfMarriage() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        system.addClient(dni, surname, name, birthDate, address);
        system.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        system.newMarriage(marriageDate, dni, dniSpouse);

        assertEquals(marriageDate, system.getDateOfMarriage(dniSpouse));
    }

    @Test
    void bankSystemShouldSuccessfullyGetSpouseOfMarriedClient() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        system.addClient(dni, surname, name, birthDate, address);
        system.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        system.newMarriage(marriageDate, dni, dniSpouse);

        assertEquals(dniSpouse, system.getSpouseOfMarriedClientDni(dni));
    }

    @Test
    void bankSystemShouldSuccessfullyDeleteMarriage() {
        BankSystem system = BankSystem.getInstance();

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        int dniSpouse = 14000000;
        String surnameSpouse = "Ferreira";
        String nameSpouse = "Maria";
        LocalDate birthDateSpouse = LocalDate.of(2001, Month.JUNE, 10);
        String addressSpouse = "Av. Acoyte 245";
        
        system.addClient(dni, surname, name, birthDate, address);
        system.addClient(dniSpouse, surnameSpouse, nameSpouse, birthDateSpouse, addressSpouse);

        LocalDate marriageDate = LocalDate.of(2021, Month.JANUARY, 1);

        system.newMarriage(marriageDate, dni, dniSpouse);

        assertEquals(dniSpouse, system.getSpouseOfMarriedClientDni(dni));
        
        assertTrue(system.isMarried(dni));
        assertTrue(system.isMarried(dniSpouse));

        system.deleteMarriage(dni);

        assertFalse(system.isMarried(dni));
        assertFalse(system.isMarried(dniSpouse));
    }

    @Test
    void bankSystemShouldSuccessfullyDepositToAccountThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0.0, dni);

        system.deposit(123456789L, 100.0);

        Account account = system.getAccount(123456789L);

        assertEquals(100.0, account.getBalance());
    }

    @Test
    void bankSystemShouldSuccessfullyDepositToAccountThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0.0, dni);

        system.deposit("iAmAccount", 100.0);

        Account account = system.getAccount("iAmAccount");

        assertEquals(100.0, account.getBalance());
    }

    @Test
    void bankSystemShouldSuccessfullyWithdrawToAccountThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 250.0, dni);

        system.withdraw(dni, 123456789L, 100.0);

        Account account = system.getAccount(123456789L);

        assertEquals(150.0, account.getBalance());
    }

    @Test
    void bankSystemShouldSuccessfullyWithdrawToAccountThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 250.0, dni);

        system.withdraw(dni, "iAmAccount", 100.0);

        Account account = system.getAccount("iAmAccount");

        assertEquals(150.0, account.getBalance());
    }

    @Test
    void bankSystemShouldSuccessfullyTransferToAccountThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int senderDni = 12345678;
        String senderSurname = "Fernandez";
        String senderName = "Martin";
        LocalDate senderBirthDate = LocalDate.of(2000, Month.MAY, 17);
        String senderAddress = "Av. Acoyte 245";

        int receiverDni = 14000000;
        String receiverSurname = "Ferreira";
        String receivertName = "Maria";
        LocalDate receiverBirthDate = LocalDate.of(2001, Month.JUNE, 10);
        String receiverAddress = "Av. Acoyte 245";

        system.addClient(senderDni, senderSurname, senderName, senderBirthDate, senderAddress);
        system.addClient(receiverDni, receiverSurname, receivertName, receiverBirthDate, receiverAddress);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmSenderAccount", 500.0, senderDni);
        branch.createAccount(111222333L, "iAmReceiverAccount", 200.0, receiverDni);

        system.transfer(senderDni, 123456789L, 111222333L, 100.0);

        Account senderAccount = system.getAccount(123456789L);
        Account receiverAccount = system.getAccount(111222333L);

        assertEquals(400.0, senderAccount.getBalance());
        assertEquals(300.0, receiverAccount.getBalance());
    }

    @Test
    void bankSystemShouldSuccessfullyTransferToAccountThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int senderDni = 12345678;
        String senderSurname = "Fernandez";
        String senderName = "Martin";
        LocalDate senderBirthDate = LocalDate.of(2000, Month.MAY, 17);
        String senderAddress = "Av. Acoyte 245";

        int receiverDni = 14000000;
        String receiverSurname = "Ferreira";
        String receivertName = "Maria";
        LocalDate receiverBirthDate = LocalDate.of(2001, Month.JUNE, 10);
        String receiverAddress = "Av. Acoyte 245";

        system.addClient(senderDni, senderSurname, senderName, senderBirthDate, senderAddress);
        system.addClient(receiverDni, receiverSurname, receivertName, receiverBirthDate, receiverAddress);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmSenderAccount", 500.0, senderDni);
        branch.createAccount(111222333L, "iAmReceiverAccount", 200.0, receiverDni);

        system.transfer(senderDni, "iAmSenderAccount", "iAmReceiverAccount", 100.0);

        Account senderAccount = system.getAccount("iAmSenderAccount");
        Account receiverAccount = system.getAccount("iAmReceiverAccount");

        assertEquals(400.0, senderAccount.getBalance());
        assertEquals(300.0, receiverAccount.getBalance());
    }

    @Test
    void bankSystemShouldCorrectlySaveTransactionWhenDepositingThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0.0, dni);


        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        int transactionId = system.deposit(date, hour, 123456789L, 100.0);

        Transaction transaction = system.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(TransactionType.DEPOSIT, transaction.getTypeOfOperation());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(123456789L, transaction.getStarterAccountCbu());
        assertEquals(123456789L, transaction.getTargetAccountCbu());
    }

    @Test
    void bankSystemShouldCorrectlySaveTransactionWhenDepositingThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 0.0, dni);


        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        int transactionId = system.deposit(date, hour, "iAmAccount", 100.0);

        Transaction transaction = system.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(TransactionType.DEPOSIT, transaction.getTypeOfOperation());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(123456789L, transaction.getStarterAccountCbu());
        assertEquals(123456789L, transaction.getTargetAccountCbu());
    }

    @Test
    void bankSystemShouldCorrectlySaveTransactionWhenWithdrawingThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 100.0, dni);


        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        int transactionId = system.withdraw(date, hour, dni, 123456789L, 40.0);

        Transaction transaction = system.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(TransactionType.WITHDRAWAL, transaction.getTypeOfOperation());
        assertEquals(40.0, transaction.getAmount());
        assertEquals(123456789L, transaction.getStarterAccountCbu());
        assertEquals(123456789L, transaction.getTargetAccountCbu());
    }

    @Test
    void bankSystemShouldCorrectlySaveTransactionWhenWithdrawingThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int dni = 12345678;
        String surname = "Fernandez";
        String name = "Martin";
        LocalDate birthDate = LocalDate.of(2000, Month.MAY, 17);
        String address = "Av. Acoyte 245";

        system.addClient(dni, surname, name, birthDate, address);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmAccount", 100.0, dni);


        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        int transactionId = system.withdraw(date, hour, dni, "iAmAccount", 40.0);

        Transaction transaction = system.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(TransactionType.WITHDRAWAL, transaction.getTypeOfOperation());
        assertEquals(40.0, transaction.getAmount());
        assertEquals(123456789L, transaction.getStarterAccountCbu());
        assertEquals(123456789L, transaction.getTargetAccountCbu());
    }

    @Test
    void bankSystemShouldCorrectlySaveTransactionWhenTransferingToAnotherAccountThroughCbu() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int senderDni = 12345678;
        String senderSurname = "Fernandez";
        String senderName = "Martin";
        LocalDate senderBirthDate = LocalDate.of(2000, Month.MAY, 17);
        String senderAddress = "Av. Acoyte 245";

        int receiverDni = 14000000;
        String receiverSurname = "Ferreira";
        String receivertName = "Maria";
        LocalDate receiverBirthDate = LocalDate.of(2001, Month.JUNE, 10);
        String receiverAddress = "Av. Acoyte 245";

        system.addClient(senderDni, senderSurname, senderName, senderBirthDate, senderAddress);
        system.addClient(receiverDni, receiverSurname, receivertName, receiverBirthDate, receiverAddress);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmSenderAccount", 500.0, senderDni);
        branch.createAccount(111222333L, "iAmReceiverAccount", 200.0, receiverDni);

        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        int transactionId = system.transfer(date, hour, senderDni,123456789L, 111222333L, 100.0);

        Transaction transaction = system.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(TransactionType.TRANSFER, transaction.getTypeOfOperation());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(123456789L, transaction.getStarterAccountCbu());
        assertEquals(111222333L, transaction.getTargetAccountCbu());
    }

    @Test
    void bankSystemShouldCorrectlySaveTransactionWhenTransferingToAnotherAccountThroughAlias() {
        BankSystem system = BankSystem.getInstance();
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Paseo Colon 900");

        int senderDni = 12345678;
        String senderSurname = "Fernandez";
        String senderName = "Martin";
        LocalDate senderBirthDate = LocalDate.of(2000, Month.MAY, 17);
        String senderAddress = "Av. Acoyte 245";

        int receiverDni = 14000000;
        String receiverSurname = "Ferreira";
        String receivertName = "Maria";
        LocalDate receiverBirthDate = LocalDate.of(2001, Month.JUNE, 10);
        String receiverAddress = "Av. Acoyte 245";

        system.addClient(senderDni, senderSurname, senderName, senderBirthDate, senderAddress);
        system.addClient(receiverDni, receiverSurname, receivertName, receiverBirthDate, receiverAddress);

        Branch branch = system.getBranch(branchNumber);
        branch.createAccount(123456789L, "iAmSenderAccount", 500.0, senderDni);
        branch.createAccount(111222333L, "iAmReceiverAccount", 200.0, receiverDni);

        LocalDate date = LocalDate.of(2024, Month.JANUARY, 2);
        LocalTime hour = LocalTime.of(13, 0, 0);
        int transactionId = system.transfer(date, hour, senderDni, "iAmSenderAccount", "iAmReceiverAccount", 100.0);

        Transaction transaction = system.getTransaction(transactionId);

        assertEquals(transactionId, transaction.getId());
        assertEquals(date, transaction.getDateOfOperation());
        assertEquals(hour, transaction.getHourOfOperation());
        assertEquals(TransactionType.TRANSFER, transaction.getTypeOfOperation());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(123456789L, transaction.getStarterAccountCbu());
        assertEquals(111222333L, transaction.getTargetAccountCbu());
    }

    @AfterEach
    public final void bankSystemRestart() {
      BankSystem.getInstance().clearEntireSystem();
    }
}