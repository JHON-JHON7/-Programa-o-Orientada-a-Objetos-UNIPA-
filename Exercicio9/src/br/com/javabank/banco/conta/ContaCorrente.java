package br.com.javabank.banco.conta;
import br.com.javabank.banco.utils.Historico;
import java.util.ArrayList;

public class ContaCorrente implements IConta {

    public Titular titular;
    protected String numero;
    protected double saldo;
    public ArrayList<String> transacoes =  new ArrayList<>();

    public ContaCorrente() {
        this.numero = "0001";
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        Historico.adicionarTransacao(this, "Depósito de R$ " + valor);
        System.out.println("Depósito realizado com sucesso! Novo saldo: R$ " + this.saldo);
    }

    public boolean sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            Historico.adicionarTransacao(this, "Saque de R$ " + valor);
            return true;
        }
        return false;
    }

    public void extrato(){
        Historico.exibirHistorico(this);
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<String> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(ArrayList<String> transacoes) {
        this.transacoes = transacoes;
    }
    
}
