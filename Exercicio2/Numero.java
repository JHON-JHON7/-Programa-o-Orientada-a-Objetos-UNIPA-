import java.util.Scanner;

public class Numero {
    public static void main(String[] args) {
         
        Scanner scanner = new Scanner(System.in);

        int numero;

        System.out.print("Digite um numero: ");

        numero = scanner.nextInt();

        if (numero % 2 == 0) {
            System.out.print("Par!");
        }
        else {
            System.out.print("Impar!");
        }

        scanner.close();
    }
}
