import java.util.Scanner;

public class Senha {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Cadas sua seu (SÓ NÚMOROS): ");
        int cadastrada = scanner.nextInt();

        System.out.print("Digite sua senha para login: ");
        int digitada = scanner.nextInt();

        if (cadastrada == digitada){
            System.out.print("Acesso concedido!!");
        } else {
            System.out.print("Acesso negado!!");
        }


        scanner.close();
    }
}
