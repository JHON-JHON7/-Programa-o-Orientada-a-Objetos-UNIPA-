public class PagamentoPix implements IPagamento {
    @Override
    public void fazerPagamento(double valor) {
        System.out.println("Pagamento de R$ " + String.format("%.2f", valor) + " processado via PIX");
        System.out.println("QR Code gerado para pagamento");
        System.out.println("Desconto de 15% aplicado!");
    }
    
    @Override
    public String toString() {
        return "PIX";
    }
}