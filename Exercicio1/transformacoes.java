import java.util.Scanner;

public class transformacoes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um valor em metros: ");

        double metros = scanner.nextDouble();

        double centimetros =  metros * 100;
        double quilometros = metros / 1000;

        System.out.println("Valor em centímetros é: " + centimetros + " cm");
        System.out.println("Valor em quilômetros é: " + quilometros + " km");

        scanner.close();
    }
}
