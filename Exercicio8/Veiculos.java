public class Veiculos {
    public static void main(String[] args) {
        // Criando e usando um Carro
        Carro carro = new Carro();
        System.out.println("=== Carro ===");
        carro.acelerar();
        carro.frear();
        carro.ligarArCondicionado();

        // Criando e usando uma Moto
        Moto moto = new Moto();
        System.out.println("\n=== Moto ===");
        moto.acelerar();
        moto.frear();
        moto.darGrau();

        // Criando e usando um Carro Elétrico
        CarroEletrico carroEletrico = new CarroEletrico();
        System.out.println("\n=== Carro Elétrico ===");
        carroEletrico.acelerar();
        carroEletrico.frear();
        carroEletrico.carregarBateria(20);
        carroEletrico.verificarNivelBateria();
        carroEletrico.ativarModoEconomico();
    }
}
