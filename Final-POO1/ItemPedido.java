import java.util.Objects;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double calcularSubtotal() {
        return this.quantidade * this.precoUnitario;
    }

    public void adicionarQuantidade(int quantidadeAdicional) {
        if (quantidadeAdicional > 0) {
            this.quantidade += quantidadeAdicional;
        }
    }

    public double getSubtotal() {
        return calcularSubtotal();
    }

    @Override
    public String toString() {
        return produto.getNome() + " - Qtd: " + quantidade + " - R$ " + String.format("%.2f", calcularSubtotal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return produto.getId() == that.produto.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto.getId());
    }
}