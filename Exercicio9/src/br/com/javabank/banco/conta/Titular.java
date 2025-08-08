package br.com.javabank.banco.conta;

public class Titular implements IUsuario {

    private String nome;
    private String cpf;

    public Titular(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return cpf;
    }
}
