// Classe Produto
class Produto {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto(String nome, String descricao, double preco, int estoque) {
        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

     public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.estoque += quantidade;
        }
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    // Método para dar baixa no estoque
    public void baixarEstoque(int quantidade) {
        if (quantidade <= this.estoque) {
            this.estoque -= quantidade;
        } else {
            System.out.println("Erro: Estoque insuficiente para o produto " + this.nome);
        }
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - R$ %.2f (Estoque: %d)", id, nome, preco, estoque);
    }
}