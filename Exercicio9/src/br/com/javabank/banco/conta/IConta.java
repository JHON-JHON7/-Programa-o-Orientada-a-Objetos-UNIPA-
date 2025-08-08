package br.com.javabank.banco.conta;

public interface IConta {
    void depositar(double valor);
    boolean sacar(double valor);
    double getSaldo();
}
