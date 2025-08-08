import java.util.Scanner;

public class Tringulo {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite altura de triangulo: ");
        int altura = scanner.nextInt();

        for(int i = 1; i <= altura; i++){
           for(int j = 1; j <= i; j++){
            System.out.print("*");
           }
           System.out.println();
        }

        scanner.close();
    }
}
