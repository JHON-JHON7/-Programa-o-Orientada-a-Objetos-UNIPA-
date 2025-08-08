package Aula;
import java.util.Scanner;

public class aprovado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a média do aluno: ");
        double media = scanner.nextDouble();

        System.out.print("Digite a frequência do aluno : ");
        double frequencia = scanner.nextDouble();

        
        if (media >= 6.0 && frequencia >= 75.0) {
            System.out.println("Aluno APROVADO!");
        } else {
            System.out.println("Aluno REPROVADO!");
        }

        scanner.close();
    }
}

