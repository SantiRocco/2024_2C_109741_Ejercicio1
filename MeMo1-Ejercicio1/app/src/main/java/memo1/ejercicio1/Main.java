package memo1.ejercicio1;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de Account usando el constructor sin argumentos
        LocalDate birthDate = LocalDate.of(2003, Month.MARCH, 4);
        Client client = new Client(20000000, "Costas", "Ignacio", birthDate, "Av. Rivadavia 4523");
        Account account1 = new Account(123456789L, "iAmAccount1", 1000.0, client, 1);

        // Crear una instancia de Account usando el constructor con saldo inicial
        Account account2 = new Account(987654321L, "iAmAccount2", 500.0, client, 1);

        // Realizar operaciones de depósito y retiro
        account1.deposit(200.0);  // Depositar 200 en la cuenta 1
        //boolean successWithdraw = account1.withdraw(300.0);  // Retirar 300 de la cuenta 1

        account2.deposit(100.0);  // Depositar 100 en la cuenta 2
        //boolean successWithdraw2 = account2.withdraw(700.0);  // Intentar retirar 700 de la cuenta 2 (debería fallar)

        // Imprimir detalles de las cuentas
        System.out.println("Cuenta 1:");
        System.out.println("CBU: " + account1.getCbu());
        System.out.println("Alias: " + account1.getAlias());
        System.out.println("Balance: " + account1.getBalance());

        System.out.println("Cuenta 2:");
        System.out.println("CBU: " + account2.getCbu());
        System.out.println("Alias: " + account2.getAlias());
        System.out.println("Balance: " + account2.getBalance());

        // Verificar si las operaciones fueron exitosas
        //System.out.println("Retiro en cuenta 1 fue " + (successWithdraw ? "exitoso" : "fallido"));
        //System.out.println("Retiro en cuenta 2 fue " + (successWithdraw2 ? "exitoso" : "fallido"));

        // Realizar operaciones de transferencia de fondos
        //boolean successTransfer = account1.transfer(500, account2); // Transferir 500 de la cuenta 1 a la cuenta 2
        //boolean successTransfer2 = account2.transfer(10000, account1); // Intentar transferir 10000 de la cuenta 2 a la cuenta 1 (debería fallar)

        // Imprimir aclaración de realización de las transferencias
        System.out.println("Al realizar las operaciones de transferencia, el nuevo saldo de las cuentas es:");

        // Imprimir detalles de las cuentas, con su nuevo saldo luego de las operaciones de transferencia
        System.out.println("Balance de cuenta 1: " + account1.getBalance());
        System.out.println("Balance de cuenta 2: " + account2.getBalance());

        // Verificar si las operaciones fueron exitosas
        //System.out.println("Transferencia de cuenta 1 a cuenta 2 fue " + (successTransfer ? "exitoso" : "fallido"));
        //System.out.println("Transferencia de cuenta 2 a cuenta 1 fue " + (successTransfer2 ? "exitoso" : "fallido"));

    }
}
