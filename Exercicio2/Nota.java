import java.util.Scanner;

public class Nota {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        double nota1, nota2,mediaNotas;

        System.out.print("Digite a nota 1: ");
        nota1 = scanner.nextDouble();

        System.out.print("Digite a nota 2: ");
        nota2 = scanner.nextDouble();

        mediaNotas = (nota1 + nota2) / 2;

        if (mediaNotas >= 7 && mediaNotas < 10) {
            System.out.print("Aprovado!!");
        } else if (mediaNotas < 7){
            System.out.print("Reprovado!!");
        } else {
            System.out.print("Aprovado com Distinção!!");
        }

        scanner.close();;


    }
}
