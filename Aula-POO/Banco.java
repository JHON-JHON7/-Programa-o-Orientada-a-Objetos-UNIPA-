public class Banco {
    public static void main(String[] args) {
        
        Cliente cliente1 = new Cliente("João Lucas", "Rua A, 123", "123.456.789-00", 5000.0, 12);
        
        Cliente cliente2 = new Cliente("Gustavo da Silva", "Rua B, 456", "987.654.321-00", 7000.0, 34);

        Conta conta1 = new Conta("Corrente", 1000, 155, cliente1.idCliente);
        Conta conta2 = new Conta("Poupança", 500, 256, cliente2.idCliente);

        System.out.println("Antes da transferência");
        
        System.out.println("Saldo conta1: " + conta1.consultarSaldo());
        System.out.println("Saldo conta2: " + conta2.consultarSaldo());

        conta1.transferir(200, conta2);

        System.out.println("Depois da transferência");
        System.out.println("Saldo conta1: " + conta1.consultarSaldo());
        System.out.println("Saldo conta2: " + conta2.consultarSaldo());
    }
}
