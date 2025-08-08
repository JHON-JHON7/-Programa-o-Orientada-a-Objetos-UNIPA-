
import java.util.Scanner;

public class Compra {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        double total = 0; 
        char opcao;

        do {
            
            System.out.print("Escolha uma opção ('i' para inserir, 'f' para finalizar): ");
            opcao = scanner.next().charAt(0);

          
            if (opcao == 'i' || opcao == 'I') {
                
                System.out.print("Digite o valor do produto: R$ ");
                double valor = scanner.nextDouble();
                total += valor; 
                
            } else if (opcao != 'f' && opcao != 'F') {
                
                System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 'f' && opcao != 'F'); 

        System.out.println("Valor total dos produtos: R$ " + total);

        scanner.close();
    }
}

