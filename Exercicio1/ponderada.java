import java.util.Scanner;

public class ponderada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         
        double nota1, nota2, nota3,mediaPonderada;
        int peso1, peso2, peso3;

        System.out.print("Digite a nota 1: ");
        nota1 = scanner.nextDouble();

        System.out.print("Digite a peso 1: ");
        peso1 = scanner.nextInt();

        System.out.print("Digite a nota 2: ");
        nota2 = scanner.nextDouble();

        System.out.print("Digite a peso 2: ");
        peso2 = scanner.nextInt();
        
        System.out.print("Digite a nota 3: ");
        nota3 = scanner.nextDouble();

        System.out.print("Digite a peso 3: ");
        peso3 = scanner.nextInt();

        mediaPonderada = (nota1 * peso1 + nota2 * peso2 + nota3 * peso3) / (peso1 + peso2 + peso3);
       

        System.out.printf("A média ponderada  das notas é: %.2f\n", mediaPonderada);

        scanner.close();;

    
    }
}
