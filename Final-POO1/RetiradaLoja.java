public class RetiradaLoja implements IEntrega {
    @Override
    public String getTipo() {
        return "Retirada na loja";
    }

    @Override
    public double calcularTaxa() {
        // A retirada em loja não tem custo.
        return 0.0;
    }
}