public class CarroEletrico implements IVeiculo, IVeiculoEletrico {
    private double nivelBateria = 100.0;

    public void acelerar() {
        System.out.println("Carro elétrico acelerando silenciosamente");
    }

    public void frear() {
        System.out.println("Carro elétrico freando");
    }

    public void carregarBateria(double kilowatts) {
        nivelBateria += kilowatts;
        System.out.println("Bateria carregada com " + kilowatts + " kW.");
    }

    public double verificarNivelBateria() {
        System.out.println("Nível da bateria: " + nivelBateria + "%");
        return nivelBateria;
    }

    public void ativarModoEconomico() {
        System.out.println("Modo econômico ativado.");
    }
}
