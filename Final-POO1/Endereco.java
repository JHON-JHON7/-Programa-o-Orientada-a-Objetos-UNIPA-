class Endereco {
    private static int contadorId = 1;
    private int id;
    private String nome; // Ex: "Casa", "Trabalho", "Principal"
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private boolean enderecoPrincipal;

    public Endereco(String nome, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        this.id = contadorId++;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = "";
        this.enderecoPrincipal = false;
    }

    public Endereco(String nome, String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this(nome, rua, numero, bairro, cidade, estado, cep);
        this.complemento = complemento;
    }

    public String getDescricaoCompleta() {
        StringBuilder desc = new StringBuilder();
        desc.append(rua).append(", ").append(numero);
        if (!complemento.isEmpty()) {
            desc.append(", ").append(complemento);
        }
        desc.append(" - ").append(bairro);
        desc.append(" - ").append(cidade).append("/").append(estado);
        desc.append(" - CEP: ").append(cep);
        return desc.toString();
    }

    public String getDescricaoResumida() {
        return nome + ": " + rua + ", " + numero + " - " + cidade + "/" + estado;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getRua() { return rua; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public boolean isEnderecoPrincipal() { return enderecoPrincipal; }
    
    public void setNome(String nome) { this.nome = nome; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public void setEnderecoPrincipal(boolean principal) { this.enderecoPrincipal = principal; }
}