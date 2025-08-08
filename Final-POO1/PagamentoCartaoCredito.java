public class PagamentoCartaoCredito implements IPagamento {
    @Override
    public void fazerPagamento(double valor) {
        System.out.println("Pagamento de R$ " + String.format("%.2f", valor) + " processado via Cartão de Crédito");
        System.out.println("Transação aprovada com sucesso!");
        System.out.println("Parcelamento disponível em até 12x sem juros");
    }
    
    @Override
    public String toString() {
        return "Cartão de Crédito";
    }
}