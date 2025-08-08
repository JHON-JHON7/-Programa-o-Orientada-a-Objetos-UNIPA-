public class PagamentoCartaoDebito implements IPagamento {
    @Override
    public void fazerPagamento(double valor) {
        System.out.println("Pagamento de R$ " + String.format("%.2f", valor) + " processado via Cartão de Débito");
        System.out.println("Valor debitado da conta corrente com sucesso!");
        System.out.println("Transação processada instantaneamente");
    }
    
    @Override
    public String toString() {
        return "Cartão de Débito";
    }
}