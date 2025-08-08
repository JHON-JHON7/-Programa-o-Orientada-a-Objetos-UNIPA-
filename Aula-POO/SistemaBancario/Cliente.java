class Cliente {
    String nome;
    String endereco;
    String cpf;
    double renda;
    int idCliente;

    // Construtor da classe Cliente
    public Cliente(String nome, String endereco, String cpf, double renda, int idCliente) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.renda = renda;
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
}
