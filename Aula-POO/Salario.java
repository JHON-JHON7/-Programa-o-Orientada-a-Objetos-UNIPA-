package Exercicio5;

public class Salario {
    public static void main(String[] args) {
        Funcionario funcionario1 = new Funcionario("Valentim da Silva", "123.456.789-00", "Júnior", 3000.00);
       
        System.out.println("Informações do Funcionário:");
        System.out.println(funcionario1.consultarInfoFucionar());
        
        System.out.println();

        System.out.println("Cargo atual: " + funcionario1.getCargo() + " // Salário: R$ " + funcionario1.getSalarioBase());
       
        System.out.println();

        // Promovendo o funcionário
        System.out.println("Promoção do Funcionário:");
        funcionario1.promover("Pleno", 5500.00);
    }
}