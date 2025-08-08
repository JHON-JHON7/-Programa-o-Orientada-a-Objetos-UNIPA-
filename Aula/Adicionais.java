package Aula;

import java.util.Scanner;

public class Adicionais {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int adicionais = 0;
        String pedido = "";
        String item = "";

        while (adicionais < 4) {
            
            System.out.println("Escolha um adicionais: ");
            
            item = scanner.nextLine();
            adicionais = adicionais + 1;
            
            if (item.equals ("sair")){
                break;
            }
            
            pedido = pedido + " || " + item ;

        }

        System.out.println("Pedido fim: " + pedido );

        scanner.close();
    }
}
