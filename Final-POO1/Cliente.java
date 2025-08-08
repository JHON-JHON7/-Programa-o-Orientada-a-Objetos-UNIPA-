import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

abstract class Cliente {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String email;
    private List<Endereco> enderecos;
    private List<Pedido> pedidos; // Histórico de pedidos
    private LocalDateTime dataCadastro;
    private boolean ativo;
    private List<SessaoAtendimento> sessoesDeAtendimento;

    public Cliente(String nome, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.enderecos = new ArrayList<>();
        this.pedidos = new ArrayList<>(); // Inicializa lista de pedidos
        this.sessoesDeAtendimento = new ArrayList<>();
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }

    // Método abstrato para identificação (CPF ou CNPJ)
    public abstract String getIdentificacao();
    public abstract String getTipoCliente();
    public abstract boolean validarIdentificacao();

    // Métodos para gerenciar pedidos - VERSÃO CORRIGIDA
    public void adicionarPedido(Pedido pedido) {
        if (this.pedidos == null) {
            this.pedidos = new ArrayList<>();
        }
        this.pedidos.add(pedido);
    }

    public void removerPedido(Pedido pedido) {
        if (pedidos != null) {
            pedidos.remove(pedido);
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos != null ? new ArrayList<>(pedidos) : new ArrayList<>();
    }

    public List<Pedido> getPedidosAtivos() {
        // Retorna pedidos que não foram cancelados/finalizados
        return getPedidos();
    }

    public int getTotalPedidos() {
        return pedidos != null ? pedidos.size() : 0;
    }

    public double getValorTotalCompras() {
        if (pedidos == null) return 0.0;
        return pedidos.stream()
                .mapToDouble(Pedido::calcularTotal)
                .sum();
    }

    // Métodos para gerenciar endereços
    public void adicionarEndereco(Endereco endereco) {
        if (enderecos == null) {
            enderecos = new ArrayList<>();
        }
        // Se é o primeiro endereço, torna-se principal automaticamente
        if (enderecos.isEmpty()) {
            endereco.setEnderecoPrincipal(true);
        }
        enderecos.add(endereco);
    }

    public void removerEndereco(int idEndereco) {
        if (enderecos == null) return;
        
        enderecos.removeIf(endereco -> endereco.getId() == idEndereco);
        
        // Se removeu o endereço principal e ainda há endereços, define o primeiro como principal
        if (enderecos.size() > 0 && enderecos.stream().noneMatch(Endereco::isEnderecoPrincipal)) {
            enderecos.get(0).setEnderecoPrincipal(true);
        }
    }

    public void definirEnderecoPrincipal(int idEndereco) {
        if (enderecos == null) return;
        
        // Remove a marcação de principal de todos os endereços
        for (Endereco endereco : enderecos) {
            endereco.setEnderecoPrincipal(false);
        }
        
        // Define o endereço especificado como principal
        for (Endereco endereco : enderecos) {
            if (endereco.getId() == idEndereco) {
                endereco.setEnderecoPrincipal(true);
                break;
            }
        }
    }

    public Endereco getEnderecoPrincipal() {
        if (enderecos == null || enderecos.isEmpty()) return null;
        
        return enderecos.stream()
                .filter(Endereco::isEnderecoPrincipal)
                .findFirst()
                .orElse(enderecos.get(0));
    }

    public List<Endereco> getEnderecos() {
        return enderecos != null ? new ArrayList<>(enderecos) : new ArrayList<>();
    }

    public Endereco getEnderecoPorId(int id) {
        if (enderecos == null) return null;
        
        return enderecos.stream()
                .filter(endereco -> endereco.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void adicionarSessaoDeAtendimento(SessaoAtendimento sessao) {
        this.sessoesDeAtendimento.add(sessao);
    }  

    // Métodos para relatórios de pedidos
    public void exibirHistoricoPedidos() {
        System.out.println("\n===== Histórico de Pedidos =====");
        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            for (int i = 0; i < pedidos.size(); i++) {
                System.out.println("Pedido #" + (i + 1) + ":");
                System.out.println("  Total: R$ " + String.format("%.2f", pedidos.get(i).calcularTotal()));
                System.out.println("  Itens: " + pedidos.get(i).getItens().size());
                System.out.println("  Entrega: " + pedidos.get(i).getEntrega());
                System.out.println("  ---");
            }
            System.out.println("Total de pedidos: " + pedidos.size());
            System.out.println("Valor total gasto: R$ " + String.format("%.2f", getValorTotalCompras()));
        }
        System.out.println("==============================\n");
    }

    public Pedido getUltimoPedido() {
        if (pedidos == null || pedidos.isEmpty()) {
            return null;
        }
        return pedidos.get(pedidos.size() - 1);
    }

    // Getters e Setters 
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public boolean isAtivo() { return ativo; }
    
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public String getDataCadastroFormatada() {
        return dataCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}