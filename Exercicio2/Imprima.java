import java.util.Scanner;

public class Imprima {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int numero1, numero2, numero3, maior;

        System.out.print("Digite o primeiro número: ");
        numero1 = scanner.nextInt();

        System.out.print("Digite o segundo número: ");
        numero2 = scanner.nextInt();

        System.out.print("Digite o terceiro número: ");
        numero3 = scanner.nextInt();

        
        if (numero1 >= numero2 && numero1 >= numero3) {
            maior = numero1;
        } else if (numero2 >= numero1 && numero2 >= numero3) {
            maior = numero2;
        } else {
            maior = numero3;
        }

        System.out.println("O maior número é: " + maior);

        scanner.close();
    
    }

}


