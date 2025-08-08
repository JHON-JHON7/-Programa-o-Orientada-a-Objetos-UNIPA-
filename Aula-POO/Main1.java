class Main1 {
    public static void main(String[] args) {
        
        // Instanciação do Cliente
        Cliente cliente1 = new Cliente();
        cliente1.nome = "João";
        cliente1.endereco = "Avenida null";
        cliente1.cpf = "12345";
        cliente1.renda = 20000;
        cliente1.idCliente = 1;

        // Instanciação da Conta
        Conta conta1 = new Conta();
        conta1.tipo = "Corrente";
        conta1.numeroConta = 123;
        conta1.idCliente = 1;

        Conta conta2 = new Conta();
        conta1.tipo = "Corrente";
        conta1.numeroConta = 456;
        conta1.idCliente = 2;

        conta1.depositar(500);

        System.out.println("Antes da transferência");
        System.out.println(conta1.consultarSaldo());
        System.out.println(conta2.consultarSaldo());

        conta1.transferir(200, conta2);

        System.out.println("Depois da transferência");
        System.out.println(conta1.consultarSaldo());
        System.out.println(conta2.consultarSaldo());


    }
}
