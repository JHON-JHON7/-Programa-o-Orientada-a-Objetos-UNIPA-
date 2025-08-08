//Classe de EntregaRapida
class EntregaRapida implements IEntrega {
    @Override
    public String getTipo() {
        return "Entrega Rápida";
    }
    
    @Override
    public double calcularTaxa() {
        return 15.90; // Taxa fixa para entrega rápida
    }
}
