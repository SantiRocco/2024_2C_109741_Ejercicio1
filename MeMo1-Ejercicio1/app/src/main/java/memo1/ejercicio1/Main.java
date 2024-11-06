package memo1.ejercicio1;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        BankSystem system = BankSystem.getInstance();

        // Crear una sucursal nueva
        int branchNumber = system.createBranch("Suc. Belgrano", "Av. Acoyte 245");
        Branch branch = system.getBranch(branchNumber);

        // Crear un nuevo cliente
        LocalDate birthDate1 = LocalDate.of(2003, Month.MARCH, 4);
        system.addClient(12345678, "Costas", "Ignacio", birthDate1, "Av. Rivadavia 4523");

        LocalDate birthDate2 = LocalDate.of(2003, Month.JUNE, 15);
        system.addClient(20000000, "Garcia", "Manuel", birthDate2, "Av. Independencia 1564");

        // Crear una instancia de Account usando el constructor sin saldo inicial
        branch.createAccount(123456789L, "iAmAccount1", 12345678);
        Account account1 = branch.getAccount("iAmAccount1");

        // Crear una instancia de Account usando el constructor con saldo inicial
        branch.createAccount(111222333L, "iAmAccount2", 1000.0, 20000000);
        Account account2 = branch.getAccount("iAmAccount2");

        // Imprimir detalles de las cuentas
        System.out.println("Cuenta 1:");
        System.out.println("CBU: " + account1.getCbu());
        System.out.println("Alias: " + account1.getAlias());
        System.out.println("Balance: " + account1.getBalance());

        System.out.println("Cuenta 2:");
        System.out.println("CBU: " + account2.getCbu());
        System.out.println("Alias: " + account2.getAlias());
        System.out.println("Balance: " + account2.getBalance());

        // Imprimir aclaración de creación de las cuentas con su titularidad
        System.out.println("Al crear las cuentas, la titularidad de las cuentas es la siguiente:");

        // Verificar si las operaciones fueron exitosas
        System.out.println("El cliente 1 " + (account1.isOwner(12345678) ? "sí" : "no") + " es el titular de la cuenta 1");
        System.out.println("El cliente 2 " + (account2.isOwner(20000000) ? "sí" : "no") + " es el titular de la cuenta 2");
        System.out.println("El cliente 1 " + (account1.isOwner(20000000) ? "sí" : "no") + " es el titular de la cuenta 2");
        System.out.println("El cliente 2 " + (account2.isOwner(12345678) ? "sí" : "no") + " es el titular de la cuenta 1");

        // Realizar operaciones de depósito y retiro
        system.deposit("iAmAccount1", 1150.0);  // Depositar 1150 en la cuenta 1
        system.withdraw(12345678, "iAmAccount1", 300.0);  // Retirar 300 de la cuenta 1 con el DNI 20000000
        boolean successWithdraw1 = true;
        
        system.deposit("iAmAccount2", 100.0);  // Depositar 100 en la cuenta 2
        boolean successWithdraw2;
        try {
            system.withdraw(20000000, "iAmAccount2", 1200.0);  // Intentar retirar 1200 de la cuenta 2 (debería fallar)
            successWithdraw2 = true;
        } catch (Exception e) {
            successWithdraw2 = false;
        }

        // Verificar si las operaciones fueron exitosas
        System.out.println("Retiro en cuenta 1 fue " + (successWithdraw1 ? "exitoso" : "fallido"));
        System.out.println("Retiro en cuenta 2 fue " + (successWithdraw2 ? "exitoso" : "fallido"));

        // Realizar operaciones de transferencia de fondos mediante CBU 
        system.transfer(12345678, 123456789L, 111222333L, 100.0);  // Transferir 100 de la cuenta 1 a la cuenta 2 mediante CBU
        boolean successTransferCbu1 = true;

        boolean successTransferCbu2;
        try {
            system.transfer(20000000, 111222333L, 123456789L, 10000.0);  // Intentar transferir 10000 de la cuenta 2 a la cuenta 1 mediante CBU (debería fallar)
            successTransferCbu2 = true;
        } catch (Exception e) {
            successTransferCbu2 = false;
        }

        // Imprimir aclaración de realización de las transferencias
        System.out.println("Al realizar las operaciones de transferencia, el nuevo saldo de las cuentas es:");

        // Imprimir detalles de las cuentas, con su nuevo saldo luego de las operaciones de transferencia
        System.out.println("Balance de cuenta 1: " + account1.getBalance());
        System.out.println("Balance de cuenta 2: " + account2.getBalance());

        // Verificar si las operaciones fueron exitosas
        System.out.println("Transferencia de cuenta 1 a cuenta 2 mediante CBU fue " + (successTransferCbu1 ? "exitoso" : "fallido"));
        System.out.println("Transferencia de cuenta 2 a cuenta 1 mediante CBU fue " + (successTransferCbu2 ? "exitoso" : "fallido"));




        // Realizar operaciones de transferencia de fondos mediante alias 
        system.transfer(12345678, "iAmAccount1", "iAmAccount2", 100.0);  // Transferir 100 de la cuenta 1 a la cuenta 2 mediante alias
        boolean successTransferAlias1 = true;

        boolean successTransferAlias2;
        try {
            system.transfer(20000000, "iAmAccount2", "iAmAccount1", 10000.0);  // Intentar transferir 10000 de la cuenta 2 a la cuenta 1 mediante alias(debería fallar)
            successTransferAlias2 = true;
        } catch (Exception e) {
            successTransferAlias2 = false;
        }

        // Imprimir aclaración de realización de las transferencias
        System.out.println("Al realizar las operaciones de transferencia, el nuevo saldo de las cuentas es:");

        // Imprimir detalles de las cuentas, con su nuevo saldo luego de las operaciones de transferencia
        System.out.println("Balance de cuenta 1: " + account1.getBalance());
        System.out.println("Balance de cuenta 2: " + account2.getBalance());

        // Verificar si las operaciones fueron exitosas
        System.out.println("Transferencia de cuenta 1 a cuenta 2 mediante alias fue " + (successTransferAlias1 ? "exitoso" : "fallido"));
        System.out.println("Transferencia de cuenta 2 a cuenta 1 mediante alias fue " + (successTransferAlias2 ? "exitoso" : "fallido"));

    }
}
