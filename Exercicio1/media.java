import java.util.Scanner;

public class media {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
         
        double nota1, nota2, nota3, nota4, mediaNotas;
        System.out.print("Digite a nota 1: ");
        nota1 = scanner.nextDouble();
        
        System.out.print("Digite a nota 2: ");
        nota2 = scanner.nextDouble();

        System.out.print("Digite a nota 3: ");
        nota3 = scanner.nextDouble();

        System.out.print("Digite a nota 4: ");
        nota4 = scanner.nextDouble();

        mediaNotas = (nota1 + nota2 + nota3 + nota4) / 4;

        System.out.printf("A média das notas é: %.2f\n", mediaNotas);

        scanner.close();
    }
}
