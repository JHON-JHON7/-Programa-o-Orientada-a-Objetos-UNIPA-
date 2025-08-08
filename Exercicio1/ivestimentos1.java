import java.util.Scanner;

public class ivestimentos1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double dipositoInicial, taxaJuros, jurosSimples;
        int tempoInvestido;

        System.out.print("Digite valor do depósito inicial em reis: ");
        dipositoInicial = scanner.nextDouble();

        System.out.print("Digite taxa juros anual: ");
        taxaJuros = scanner.nextDouble();

        System.out.print("Digite tempo do invetimento em anos: ");
        tempoInvestido = scanner.nextInt();

        jurosSimples = dipositoInicial + (dipositoInicial * (taxaJuros / 100 ) * tempoInvestido );

        System.out.printf("Valor do investimento total é: %.2f\n", jurosSimples);

        scanner.close();
    }
}
