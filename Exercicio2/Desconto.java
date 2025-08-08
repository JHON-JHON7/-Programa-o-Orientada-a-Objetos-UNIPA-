import java.util.Scanner;

public class Desconto {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite valor da compra: R$ ");
        double valorCompra = scanner.nextDouble();

        double desconto = 0;

        if (valorCompra > 100 && valorCompra <= 199 ) {
            desconto = 0.10  ;
        } else if (valorCompra > 200 && valorCompra <= 299) {
            desconto = 0.15;
        } else {
            desconto = 0.20;
        }

        double valorDesconto = valorCompra * desconto;

        double total = valorCompra - valorDesconto;

        System.out.println("Total a pagar: R$ " + total);

        scanner.close();
        
    }
   
}
