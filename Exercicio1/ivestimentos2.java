import java.util.Scanner;

public class ivestimentos2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double dipositoInicial, taxaJuros, montante, imposto, montanteFinal;
        int tempoInvestido;

        System.out.print("Digite valor do depósito inicial em reis: ");
        dipositoInicial = scanner.nextDouble();

        System.out.print("Digite taxa juros anual: ");
        taxaJuros = scanner.nextDouble();

        System.out.print("Digite tempo do invetimento em anos: ");
        tempoInvestido = scanner.nextInt();

        taxaJuros = dipositoInicial * (taxaJuros / 100) * tempoInvestido;
        montante = dipositoInicial + taxaJuros;

       
        imposto = taxaJuros * 0.15;
        montanteFinal = montante - imposto;

        System.out.printf("\n Juros acumulados: R$ %.2f", taxaJuros);

        System.out.printf("\n Imposto de 15%%: R$ %.2f", imposto);
    
        System.out.printf("\n Montante final após imposto: R$ %.2f\n", montanteFinal);
        
        scanner.close();
    }
}
