package Exercicio4;

class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    Cliente(String nome, String cpf, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    //Getters

    public String getNome(){
        return this.nome;
    }

    public String getCfp(){
        return this.cpf;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public String getEmail(){
        return this.email;
    }

    // Setters

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String  consultarInfo(){
        return "Nome: " + nome + "\nCPF: " + cpf + "\nEmail: " + email + "\nTelefone: " + telefone;
    }

}
