package Aula;

import java.util.Scanner;

public class Calcule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double total = 0; 
        double notas = 3;

        for(int i=1; i<=notas; i++ ){
            System.out.println("Informa a nota" + i + ":" );
            double nota = scanner.nextDouble();
            
            total = total + nota;
        }

        double media = total / notas;
        System.out.println("Media do aluno " + media);

        scanner.close();
    }
}
