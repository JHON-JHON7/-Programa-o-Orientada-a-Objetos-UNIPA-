import java.util.Scanner;

public class Multa {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a velocidade do carro (km/h): ");
        int velocidade = scanner.nextInt();

        System.out.print("Digite a velocidade máxima permitida (km/h): ");
        int velocidadeMaxima = scanner.nextInt();

        int excesso = velocidade - velocidadeMaxima;

        int multa = 0;

        if (excesso > 0) {
            if (excesso <= 10) {
                multa = 50;
            } else if (excesso <= 20) {
                multa = 120;
            } else {
                multa = 300;
            }
            System.out.println("Você foi multado! Valor da multa: R$ " + multa);
        } else {
            System.out.println("Velocidade dentro do limite. Sem multa.");
        }
        
        scanner.close();;
    }
}
