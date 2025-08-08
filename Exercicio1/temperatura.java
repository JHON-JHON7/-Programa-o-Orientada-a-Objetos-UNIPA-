import java.util.Scanner;

public class temperatura {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um temperatura em graus Faherenheit: ");

        double F = scanner.nextDouble();
       
        double forma = (F - 32) * 5 / 9;

        System.out.println("Valor em graus Celsius é:  " + forma + " °C");

        scanner.close();
    }
}
