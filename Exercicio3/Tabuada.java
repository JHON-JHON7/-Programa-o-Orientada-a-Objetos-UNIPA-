import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite número da tabuada:");
        int tabuada = scanner.nextInt();

        if (tabuada < 0) {
            System.out.println("Número negativo!");
        }

        for(int i = 1; i <= 10; i++  ){
            System.out.println(
                tabuada + " x " + i + " = " + (tabuada * i)
            );
        }

        scanner.close();

    }
    
}
