package br.com.javabank.banco.investimento;

import br.com.javabank.banco.conta.ContaCorrente;

// Classe com o mesmo nome mas em pacote diferente
public class Conta extends ContaCorrente {

    public Conta() {
        // Acesso permitido por ser protected via herança mesmo estando em outro pacote
        this.numero = "INV-" + this.numero;
    }
}
