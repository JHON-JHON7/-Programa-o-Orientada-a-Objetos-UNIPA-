//Classe de EntregaPadrao
class EntregaPadrao implements IEntrega {
    @Override
    public String getTipo() {
        return "Entrega Padrão";
    }
    
    @Override
    public double calcularTaxa() {
        return 8.50; // Taxa fixa para entrega padrão
    }
}
