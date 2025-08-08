package Exercicio4;

public class Banco {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Joâo Lucas", "227.025.090-70", "joao856l@gmail.com", "(32) 2161-8675" );
        
        Conta conta1 = new Conta(50580317, "Corrente", 5000, cliente1);
        
        System.out.println("Informações do Cliente:");
        System.out.println(cliente1.consultarInfo());

        System.out.println("\nSaldo Inicial: R$" + conta1.consultarSaldo());

        conta1.depositar(5000);
        System.out.println("Saldo após depósito: R$" + conta1.consultarSaldo());

        conta1.sacar(7000);
        System.out.println("Saldo após saque: R$" + conta1.consultarSaldo());

        conta1.sacar(3000);
        System.out.println("Saldo após saque: R$" + conta1.consultarSaldo());

        /* Testar as opções inválidas
           
        Cliente cliente2 = new Cliente("Lucas Da Silva", "983.015.900-09", "lucasdasilva@gmail.com", "(34) 3448-1721");

        Conta conta2 = new Conta(1067042, "Correte", 9000, cliente2);

        System.out.println("Informações do Cliente:");
        System.out.println(cliente2.consultarInfo());

        System.out.println("\nSaldo Inicial: R$" + conta2.consultarSaldo());

        conta1.depositar(-8);
        System.out.println("Saldo após depósito: R$" + conta2.consultarSaldo());

        conta1.sacar(80000);
        System.out.println("Saldo após saque: R$" + conta2.consultarSaldo());
        

        conta1.sacar(50000);
        System.out.println("Saldo após saque: R$" + conta2.consultarSaldo());
        */
    }
}
