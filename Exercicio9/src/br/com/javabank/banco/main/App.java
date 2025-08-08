package br.com.javabank.banco.main;

import br.com.javabank.banco.conta.*;
import br.com.javabank.banco.investimento.Conta;

public class App {
    public static void main(String[] args) {

        // Criando titular
        Titular titular = new Titular("Ana Silva", "123.456.789-00");

        // Conta Corrente
        ContaCorrente cc = new ContaCorrente();
        cc.titular = titular;
        for(int i=0; i<=5; i++){
            cc.depositar(cc.getSaldo()+100);
        }
        System.out.println("Saldo Conta Corrente: R$ " + cc.getSaldo());

        cc.sacar(100);

        // Conta Poupança
        ContaPoupanca cp = new ContaPoupanca();
        cp.titular = titular;
        cp.depositar(300);
        System.out.println("Saldo Conta Poupança: R$ " + cp.getSaldo());

        // Conta da corretora (Investimento)
        Conta ci = new Conta();
        ci.titular = titular;
        ci.depositar(1000);
        System.out.println("Saldo Conta Investimento: R$ " + ci.getSaldo());

        // Usando métodos estáticos
        cc.extrato();

        // Verificando acesso ao atributo protected fora do pacote
        // cc.numero = "teste"; -> Não permitido! Fora do pacote e sem herança
    }
}
