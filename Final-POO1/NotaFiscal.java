import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotaFiscal {
    private static int contadorNF = 1;
    private int numeroNF;
    private LocalDateTime dataEmissao;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double subtotal;
    private double taxaEntrega;
    private double total;
    private String formaPagamento;
    private String tipoEntrega;
    
    public NotaFiscal(Cliente cliente, List<ItemPedido> itens, double subtotal, 
                     double taxaEntrega, double total, String formaPagamento, String tipoEntrega) {
        this.numeroNF = contadorNF++;
        this.dataEmissao = LocalDateTime.now();
        this.cliente = cliente;
        this.itens = itens;
        this.subtotal = subtotal;
        this.taxaEntrega = taxaEntrega;
        this.total = total;
        this.formaPagamento = formaPagamento;
        this.tipoEntrega = tipoEntrega;
    }
    
    public void exibirNotaFiscal() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    NOTA FISCAL ELETRÔNICA");
        System.out.println("=".repeat(60));
        System.out.println("Número da NF-e: " + String.format("%06d", numeroNF));
        System.out.println("Data/Hora de Emissão: " + dataEmissao.format(formatter));
        System.out.println("=".repeat(60));
        
        // Dados da empresa
        System.out.println("EMITENTE:");
        System.out.println("E-Commerce Solutions LTDA");
        System.out.println("CNPJ: 12.345.678/0001-90");
        System.out.println("Endereço: Rua do Comércio, 123 - Centro - São Paulo/SP");
        System.out.println("CEP: 01001-000 | Telefone: (11) 3333-4444");
        System.out.println("-".repeat(60));
        
        // Dados do cliente
        System.out.println("DESTINATÁRIO:");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        
        if (cliente instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) cliente;
            System.out.println("CPF: " + formatarCPF(pf.getCpf()));
        } else if (cliente instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) cliente;
            System.out.println("CNPJ: " + formatarCNPJ(pj.getCnpj()));
            System.out.println("Razão Social: " + pj.getRazaoSocial());
        }
        
        Endereco endereco = cliente.getEnderecoPrincipal();
        if (endereco != null) {
            System.out.println("Endereço: " + endereco.getDescricaoCompleta());
        }
        
        System.out.println("=".repeat(60));
        System.out.println("DISCRIMINAÇÃO DOS PRODUTOS/SERVIÇOS:");
        System.out.println("-".repeat(60));
        System.out.printf("%-4s %-25s %8s %12s %12s\n", "ITEM", "DESCRIÇÃO", "QTD", "VL. UNIT.", "VL. TOTAL");
        System.out.println("-".repeat(60));
        
        int itemNum = 1;
        for (ItemPedido item : itens) {
            System.out.printf("%04d %-25s %8d %12.2f %12.2f\n",
                itemNum++,
                truncarTexto(item.getProduto().getNome(), 25),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()
            );
        }
        
        System.out.println("-".repeat(60));
        System.out.printf("SUBTOTAL DOS PRODUTOS: %30s R$ %10.2f\n", "", subtotal);
        System.out.printf("TAXA DE ENTREGA (%s): %22s R$ %10.2f\n", tipoEntrega, "", taxaEntrega);
        System.out.println("-".repeat(60));
        System.out.printf("VALOR TOTAL DA NOTA: %32s R$ %10.2f\n", "", total);
        System.out.println("=".repeat(60));
        
        System.out.println("FORMA DE PAGAMENTO: " + formaPagamento);
        System.out.println("STATUS: PAGO");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("            Obrigado pela sua compra!");
        System.out.println("        Esta é uma via simplificada da NF-e");
        System.out.println("=".repeat(60));
    }
    
    private String formatarCPF(String cpf) {
        if (cpf.length() == 11) {
            return cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + 
                   cpf.substring(6,9) + "-" + cpf.substring(9,11);
        }
        return cpf;
    }
    
    private String formatarCNPJ(String cnpj) {
        if (cnpj.length() == 14) {
            return cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." + 
                   cnpj.substring(5,8) + "/" + cnpj.substring(8,12) + "-" + cnpj.substring(12,14);
        }
        return cnpj;
    }
    
    private String truncarTexto(String texto, int maxLength) {
        if (texto.length() <= maxLength) {
            return texto;
        }
        return texto.substring(0, maxLength - 3) + "...";
    }
}