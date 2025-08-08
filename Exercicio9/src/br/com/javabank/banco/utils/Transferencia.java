package br.com.javabank.banco.utils;

public class Transferencia {

    // Método utilitário
    public static void realizarTransferencia(String origem, String destino, double valor) {
        System.out.println("Transferência de R$ " + valor + " de " + origem + " para " + destino + " realizada.");
    }
}
