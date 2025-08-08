import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um pedido realizado por um cliente no sistema de e-commerce.
 */
public class Pedido {

    private static int proximoId = 1;
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataPedido;
    private IEntrega entrega;
    private IPagamento formaDePagamento;
    private NotaFiscal notaFiscal;

    public Pedido(Cliente cliente) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataPedido = LocalDateTime.now();
        this.entrega = null;
        this.formaDePagamento = null;
        this.notaFiscal = null; // Inicia como nulo.
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        for (ItemPedido itemExistente : itens) {
            if (itemExistente.getProduto().getId() == produto.getId()) {
                itemExistente.adicionarQuantidade(quantidade);
                return;
            }
        }
        this.itens.add(new ItemPedido(produto, quantidade));
    }

    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ItemPedido item : itens) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }
    
    public double calcularTotal() {
        double total = calcularSubtotal();
        if (this.entrega != null) {
            total += this.entrega.calcularTaxa();
        }
        return total;
    }

    // --- MÉTODOS GETTERS E SETTERS ---

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public IEntrega getEntrega() { return entrega; }
    public IPagamento getFormaDePagamento() { return formaDePagamento; }
    
    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }
    
    public void definirEntrega(IEntrega entrega) { this.entrega = entrega; }
    public void definirPagamento(IPagamento formaDePagamento) { this.formaDePagamento = formaDePagamento; }

    
    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public void exibirResumo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("\n===== Resumo do Pedido ID: " + this.id + " =====");
        System.out.println("Data: " + this.dataPedido.format(formatter));
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("Itens do Pedido:");
        for (ItemPedido item : itens) {
            System.out.printf("  - %dx %s (R$ %.2f cada)\n",
                    item.getQuantidade(), item.getProduto().getNome(), item.getPrecoUnitario());
        }
        System.out.printf("Subtotal: R$ %.2f\n", calcularSubtotal());
        
        if (entrega != null) {
            System.out.printf("Tipo de entrega: %s\n", entrega.getTipo());
            System.out.printf("Taxa de Entrega: R$ %.2f\n", entrega.calcularTaxa());
        }
        
        if (formaDePagamento != null) {
            System.out.println("Forma de pagamento: " + formaDePagamento.toString());
        } else {
            System.out.println("Forma de pagamento: Não definida");
        }

        System.out.printf("TOTAL DO PEDIDO: R$ %.2f\n", calcularTotal());
        System.out.println("======================================");
    }
}