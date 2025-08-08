package br.com.javabank.banco.conta;

public class ContaPoupanca extends ContaCorrente {

    public ContaPoupanca() {
        // Acessando atributo protected dentro do mesmo pacote via herança
        this.numero = "POUP-" + this.numero;
    }
}
