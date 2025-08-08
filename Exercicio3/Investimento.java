import java.util.Scanner;

public class Investimento {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecione o tipo de conta:");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        String identificacao;
        double deposito;

        switch (opcao) {
            case 1:
                // Pessoa Física (CPF)
                
                System.out.print("Informe o CPF (somente números): ");
                identificacao = scanner.nextLine();

                System.out.print("Digite o valor do depósito inicial: R$ ");
                deposito = scanner.nextDouble();

                System.out.println("\nConta Pessoa Física criada com sucesso!");
                System.out.println("CPF: " + identificacao);
                System.out.println("Depósito Inicial: R$ " + String.format("%.2f", deposito));
                break;

            case 2:
                // Pessoa Jurídica (CNPJ)
                
                System.out.print("Informe o CNPJ (somente números): ");
                identificacao = scanner.nextLine();

                do {
                    System.out.print("Digite o valor do depósito inicial (mínimo R$5000.00): R$ ");
                    deposito = scanner.nextDouble();

                    if (deposito < 5000) {
                        System.out.println("Erro: O depósito mínimo para CNPJ é R$ 5.000,00. Tente novamente.");
                    }
                } while (deposito < 5000);

                System.out.println("\nConta Pessoa Jurídica criada com sucesso!");
                System.out.println("CNPJ: " + identificacao);
                System.out.println("Depósito Inicial: R$ " + String.format("%.2f", deposito));
                break;

            default:
                System.out.println("Opção inválida! O programa será encerrado.");
        }

        scanner.close();
    }
}

