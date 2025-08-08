package Aula;

import java.util.Scanner;

public class Notas {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira a nota da aluno: ");
        double nota = scanner.nextDouble();

        while (nota <0 || nota>10 ) {
            System.out.println("Valor Ivalido!!!");
            nota = scanner.nextDouble();
        }

        System.out.println("A nota informada foi: " + nota );

        scanner.close();
    }
}
