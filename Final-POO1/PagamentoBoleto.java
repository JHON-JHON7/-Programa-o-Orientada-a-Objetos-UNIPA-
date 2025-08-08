public class PagamentoBoleto implements IPagamento {
    @Override
    public void fazerPagamento(double valor) {
        System.out.println("Boleto bancário gerado no valor de R$ " + String.format("%.2f", valor));
        System.out.println("Código de barras: 34191.79001 01043.510047 91020.150008 1 83320000023000");
        System.out.println("Vencimento: 3 dias úteis");
    }
    
    @Override
    public String toString() {
        return "Boleto Bancário";
    }
}