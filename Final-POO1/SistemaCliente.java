import java.util.*;
import java.util.regex.Pattern;


public class SistemaCliente {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>(); // Lista de produtos
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuarioLogado = null;
    private AtendenteVirtual atendenteVirtual;


    // Validações
    private boolean validarEmail(String email) {
        if(email == null) return false;
        String regex = "^[\\w-.]+@[\\w-]+(\\.[\\w-]+)+$";
        return Pattern.matches(regex, email.toLowerCase().trim());
    }

    private boolean validarCPF(String cpf) {
        String numeros = cpf.replaceAll("\\D", "");
        return numeros.length() == 11 && !numeros.matches("(\\d)\\1{10}");
    }

    private boolean validarCNPJ(String cnpj) {
        String numeros = cnpj.replaceAll("\\D", "");
        return numeros.length() == 14;
    }

    private String detectarTipoIdentificador(String id) {
        id = id.trim();
        if(validarEmail(id)) return "EMAIL";
        if(validarCPF(id)) return "CPF";
        if(validarCNPJ(id)) return "CNPJ";
        return "INVALIDO";
    }

    // Cadastro de usuário
    private void cadastrarUsuario() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        System.out.println("Tipos de identificador disponíveis:");
        System.out.println("1 - Email");
        System.out.println("2 - CPF");
        System.out.println("3 - CNPJ");
        System.out.print("Escolha o tipo: ");
        
        String opcao = scanner.nextLine().trim();
        String tipoIdentificador;
        
        switch(opcao) {
            case "1": tipoIdentificador = "EMAIL"; break;
            case "2": tipoIdentificador = "CPF"; break;
            case "3": tipoIdentificador = "CNPJ"; break;
            default:
                System.out.println("Opção inválida!\n");
                return;
        }

        System.out.print("Digite o " + tipoIdentificador.toLowerCase() + ": ");
        String identificador = scanner.nextLine().trim();

        // Valida o identificador
        boolean valido = false;
        switch(tipoIdentificador) {
            case "EMAIL": valido = validarEmail(identificador); break;
            case "CPF": valido = validarCPF(identificador); break;
            case "CNPJ": valido = validarCNPJ(identificador); break;
        }

        if (!valido) {
            System.out.println(tipoIdentificador + " inválido!\n");
            return;
        }

        // Verifica se o usuário já existe
        for (Usuario u : usuarios) {
            if(u.getIdentificador().equalsIgnoreCase(identificador) && 
               u.getTipoIdentificador().equals(tipoIdentificador)) {
                System.out.println("Usuário já cadastrado com este identificador!\n");
                return;
            }
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (senha.length() < 6) {
            System.out.println("Senha deve ter pelo menos 6 caracteres!\n");
            return;
        }

        Usuario novoUsuario = new Usuario(identificador, tipoIdentificador, senha);
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!\n");
    }

    // Login 
    private boolean login() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Identificador (Email, CPF ou CNPJ): ");
        String identificador = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        String tipo = detectarTipoIdentificador(identificador);
        if(tipo.equals("INVALIDO")) {
            System.out.println("Identificador inválido!\n");
            return false;
        }

        String identificadorFormatado = new Usuario(identificador, tipo, senha).getIdentificador();

        for(Usuario u : usuarios) {
            if(u.getTipoIdentificador().equals(tipo) && 
               u.getIdentificador().equalsIgnoreCase(identificadorFormatado) &&
               u.validarSenha(senha)) {
                usuarioLogado = u;
                u.atualizarUltimoLogin();
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem-vindo, " + usuarioLogado.getIdentificador() + "\n");
                return true;
            }
        }

        System.out.println("Identificador ou senha incorretos!\n");
        return false;
    }

    // Criar conta de cliente melhorada
    private void criarContaCliente() {
        if (!usuarioLogado.temClienteAssociado()) {
            System.out.println("\n=== CRIAR CONTA DE CLIENTE ===");
            System.out.println("1 - Pessoa Física");
            System.out.println("2 - Pessoa Jurídica");
            System.out.print("Escolha o tipo: ");
            
            String opcao = scanner.nextLine().trim();
            
            if (opcao.equals("1")) {
                criarPessoaFisica();
            } else if (opcao.equals("2")) {
                criarPessoaJuridica();
            } else {
                System.out.println("Opção inválida!\n");
            }
        } else {
            System.out.println("Você já possui uma conta de cliente associada!\n");
            exibirDadosCliente(usuarioLogado.getCliente());
        }
    }

    private void criarPessoaFisica() {
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if (!validarEmail(email)) {
            System.out.println("Email inválido!\n");
            return;
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        if (!validarCPF(cpf)) {
            System.out.println("CPF inválido!\n");
            return;
        }

        System.out.print("Telefone (opcional): ");
        String telefone = scanner.nextLine().trim();

        PessoaFisica cliente = new PessoaFisica(nome, email, cpf, telefone);
        
        if (!cliente.validarIdentificacao()) {
            System.out.println("Dados de identificação inválidos!\n");
            return;
        }

        clientes.add(cliente);
        usuarioLogado.associarCliente(cliente);
        
        System.out.println("\nConta de cliente criada com sucesso!");
        exibirDadosCliente(cliente);
        
        // Oferece para cadastrar endereço
        System.out.print("Deseja cadastrar um endereço agora? (s/n): ");
        if (scanner.nextLine().trim().toLowerCase().startsWith("s")) {
            cadastrarEndereco();
        }
    }

    private void criarPessoaJuridica() {
        System.out.print("Razão Social: ");
        String razaoSocial = scanner.nextLine().trim();

        System.out.print("Nome Fantasia (opcional): ");
        String nomeFantasia = scanner.nextLine().trim();
        if (nomeFantasia.isEmpty()) {
            nomeFantasia = razaoSocial;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if (!validarEmail(email)) {
            System.out.println("Email inválido!\n");
            return;
        }

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine().trim();
        if (!validarCNPJ(cnpj)) {
            System.out.println("CNPJ inválido!\n");
            return;
        }

        System.out.print("Nome do responsável: ");
        String responsavel = scanner.nextLine().trim();

        PessoaJuridica cliente = new PessoaJuridica(razaoSocial, nomeFantasia, email, cnpj, responsavel);
        
        if (!cliente.validarIdentificacao()) {
            System.out.println("Dados de identificação inválidos!\n");
            return;
        }

        clientes.add(cliente);
        usuarioLogado.associarCliente(cliente);
        
        System.out.println("\nConta de cliente criada com sucesso!");
        exibirDadosCliente(cliente);
        
        // Oferece para cadastrar endereço
        System.out.print("Deseja cadastrar um endereço agora? (s/n): ");
        if (scanner.nextLine().trim().toLowerCase().startsWith("s")) {
            cadastrarEndereco();
        }
    }

    // Gerenciamento de endereços
    private void cadastrarEndereco() {
        if (!usuarioLogado.temClienteAssociado()) {
            System.out.println("Você precisa criar uma conta de cliente primeiro!\n");
            return;
        }

        System.out.println("\n=== CADASTRAR ENDEREÇO ===");
        System.out.print("Nome do endereço (ex: Casa, Trabalho): ");
        String nome = scanner.nextLine().trim();

        System.out.print("Rua: ");
        String rua = scanner.nextLine().trim();

        System.out.print("Número: ");
        String numero = scanner.nextLine().trim();

        System.out.print("Complemento (opcional): ");
        String complemento = scanner.nextLine().trim();

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine().trim();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine().trim();

        System.out.print("Estado: ");
        String estado = scanner.nextLine().trim();

        System.out.print("CEP: ");
        String cep = scanner.nextLine().trim();

        Endereco endereco = new Endereco(nome, rua, numero, complemento, bairro, cidade, estado, cep);
        usuarioLogado.getCliente().adicionarEndereco(endereco);

        System.out.println("Endereço cadastrado com sucesso!\n");
        listarEnderecos();
    }

    private void listarEnderecos() {
        if (!usuarioLogado.temClienteAssociado()) {
            System.out.println("Você precisa criar uma conta de cliente primeiro!\n");
            return;
        }

        List<Endereco> enderecos = usuarioLogado.getCliente().getEnderecos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.\n");
            return;
        }

        System.out.println("\n=== SEUS ENDEREÇOS ===");
        for (Endereco endereco : enderecos) {
            System.out.printf("[%d] %s%s\n", 
                endereco.getId(), 
                endereco.getDescricaoResumida(),
                endereco.isEnderecoPrincipal() ? " (PRINCIPAL)" : "");
        }
        System.out.println();
    }

    private void gerenciarEnderecos() {
        if (!usuarioLogado.temClienteAssociado()) {
            System.out.println("Você precisa criar uma conta de cliente primeiro!\n");
            return;
        }

        while (true) {
            System.out.println("\n=== GERENCIAR ENDEREÇOS ===");
            System.out.println("1 - Listar endereços");
            System.out.println("2 - Cadastrar novo endereço");
            System.out.println("3 - Definir endereço principal");
            System.out.println("4 - Remover endereço");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    listarEnderecos();
                    break;
                case "2":
                    cadastrarEndereco();
                    break;
                case "3":
                    definirEnderecoPrincipal();
                    break;
                case "4":
                    removerEndereco();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }

    private void definirEnderecoPrincipal() {
        listarEnderecos();
        List<Endereco> enderecos = usuarioLogado.getCliente().getEnderecos();
        if (enderecos.isEmpty()) return;

        System.out.print("Digite o ID do endereço para tornar principal: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            usuarioLogado.getCliente().definirEnderecoPrincipal(id);
            System.out.println("Endereço principal definido com sucesso!\n");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!\n");
        }
    }

    private void removerEndereco() {
        listarEnderecos();
        List<Endereco> enderecos = usuarioLogado.getCliente().getEnderecos();
        if (enderecos.isEmpty()) return;

        System.out.print("Digite o ID do endereço para remover: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            usuarioLogado.getCliente().removerEndereco(id);
            System.out.println("Endereço removido com sucesso!\n");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!\n");
        }
    }
    
    // --- GERENCIAMENTO DE PRODUTOS ---
    private void gerenciarProdutos() {
        while (true) {
            System.out.println("\n=== GERENCIAR PRODUTOS ===");
            System.out.println("1 - Listar todos os produtos");
            System.out.println("2 - Cadastrar novo produto");
            System.out.println("3 - Repor estoque de produto"); // <-- NOVA OPÇÃO
            System.out.println("4 - Voltar ao menu principal"); // <-- OPÇÃO ANTIGA MUDOU DE NÚMERO
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    listarProdutos(true);
                    break;
                case "2":
                    cadastrarProduto();
                    break;
                case "3": // <-- NOVO CASE
                    reporEstoqueProduto();
                    break;
                case "4":
                    return; // Volta ao menu anterior
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }

    private void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Novo Produto ---");
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        double preco = -1;
        while (preco < 0) {
            System.out.print("Preço (ex: 25,50): ");
            try {
                preco = Double.parseDouble(scanner.nextLine().replace(',', '.'));
                if (preco < 0) System.out.println("O preço não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Formato de preço inválido. Use números.");
            }
        }

        int estoque = -1;
        while (estoque < 0) {
            System.out.print("Quantidade em estoque: ");
            try {
                estoque = Integer.parseInt(scanner.nextLine());
                if (estoque < 0) System.out.println("O estoque não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Formato de estoque inválido. Use números inteiros.");
            }
        }

        Produto novoProduto = new Produto(nome, descricao, preco, estoque);
        produtos.add(novoProduto);
        System.out.println("Produto '" + nome + "' cadastrado com sucesso!\n");
    }
     
    private void reporEstoqueProduto() {
        System.out.println("\n--- Reposição de Estoque ---");
        listarProdutos(false); // Mostra a lista de produtos para o usuário escolher

        if (produtos.isEmpty()) {
            return; // Sai se não houver produtos
        }

        System.out.print("\nDigite o ID do produto para repor o estoque: ");
        int idProduto;
        try {
            idProduto = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Produto produtoParaRepor = buscarProdutoPorId(idProduto);

        if (produtoParaRepor == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Produto selecionado: " + produtoParaRepor.getNome());
        System.out.println("Estoque atual: " + produtoParaRepor.getEstoque());

        System.out.print("Digite a quantidade a ser ADICIONADA ao estoque: ");
        int quantidadeParaAdicionar;
        try {
            quantidadeParaAdicionar = Integer.parseInt(scanner.nextLine().trim());
            if (quantidadeParaAdicionar <= 0) {
                System.out.println("A quantidade deve ser um número positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
            return;
        }

        // Utiliza o novo método da classe Produto
        produtoParaRepor.adicionarEstoque(quantidadeParaAdicionar);

        System.out.println("\nEstoque atualizado com sucesso!");
        System.out.println("Novo estoque de '" + produtoParaRepor.getNome() + "': " + produtoParaRepor.getEstoque());
    }

    private void listarProdutos(boolean detalhado) {
        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado no sistema.");
            return;
        }

        System.out.println("\n--- Lista de Produtos Disponíveis ---");
        for (Produto p : produtos) {
            if (detalhado) {
                System.out.println(p + " - Descrição: " + p.getDescricao());
            } else {
                System.out.println(p);
            }
        }
        System.out.println("-------------------------------------\n");
    }

    private Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // --- FLUXO DE PEDIDO ---
    private void fazerPedido() {
        if (!usuarioLogado.temClienteAssociado() || usuarioLogado.getCliente().getEnderecoPrincipal() == null) {
            System.out.println("\nPara fazer um pedido, você precisa ter uma conta de cliente e um endereço principal cadastrado.");
            return;
        }
        if (produtos.isEmpty()) {
            System.out.println("\nNão há produtos disponíveis para compra no momento.");
            return;
        }

        Pedido novoPedido = new Pedido(usuarioLogado.getCliente());
        
        while (true) {
            System.out.println("\n--- Lista de Produtos (Estoque em tempo real) ---");
            for (Produto p : produtos) {
                int quantidadeNoCarrinho = 0;
                for (ItemPedido item : novoPedido.getItens()) {
                    if (item.getProduto().getId() == p.getId()) {
                        quantidadeNoCarrinho = item.getQuantidade();
                        break;
                    }
                }
                int estoqueDisponivel = p.getEstoque() - quantidadeNoCarrinho;
                System.out.printf("[%d] %s - R$ %.2f (Disponível: %d)\n",
                        p.getId(), p.getNome(), p.getPreco(), estoqueDisponivel);
            }
            System.out.println("----------------------------------------------------");

            System.out.print("Digite o ID do produto para adicionar (ou 0 para finalizar): ");
            int idProduto;
            try {
                idProduto = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("ID inválido.");
                continue;
            }

            if (idProduto == 0) {
                if (novoPedido.getItens().isEmpty()) {
                    System.out.println("Carrinho vazio. Pedido cancelado.");
                    return;
                }
                break;
            }

            Produto produtoSelecionado = buscarProdutoPorId(idProduto);
            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade;
            try {
                quantidade = Integer.parseInt(scanner.nextLine().trim());
                int quantidadeNoCarrinho = 0;
                for (ItemPedido item : novoPedido.getItens()) {
                    if (item.getProduto().getId() == produtoSelecionado.getId()) {
                        quantidadeNoCarrinho = item.getQuantidade();
                        break;
                    }
                }
                if (quantidade <= 0 || (quantidade + quantidadeNoCarrinho) > produtoSelecionado.getEstoque()) {
                    System.out.println("Quantidade inválida ou excede o estoque disponível.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida.");
                continue;
            }
            
            novoPedido.adicionarProduto(produtoSelecionado, quantidade);
            System.out.println("'" + produtoSelecionado.getNome() + "' adicionado(s) ao carrinho.");
        }

        escolherEntrega(novoPedido);
        IPagamento pagamento = fazerPagamento(novoPedido);
        if (pagamento == null) { return; }
        
        novoPedido.definirPagamento(pagamento);

        novoPedido.exibirResumo();
        System.out.print("Deseja confirmar o pedido? (s/n): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();

        if (confirmacao.startsWith("s")) {
            double subtotal = novoPedido.calcularSubtotal();
            double taxaEntrega = novoPedido.getEntrega().calcularTaxa();
            double total = novoPedido.calcularTotal();
            if (pagamento instanceof PagamentoPix) { total *= 0.85; }

            for (ItemPedido item : novoPedido.getItens()) {
                item.getProduto().baixarEstoque(item.getQuantidade());
            }

            pagamento.fazerPagamento(total);
            usuarioLogado.getCliente().adicionarPedido(novoPedido);

            // A Nota Fiscal é criada aqui
            NotaFiscal notaFiscal = new NotaFiscal(
                    usuarioLogado.getCliente(), novoPedido.getItens(), subtotal,
                    taxaEntrega, total, pagamento.toString(),
                    novoPedido.getEntrega().getTipo());
            
            // E salva no pedido logo em seguida
            novoPedido.setNotaFiscal(notaFiscal);

            System.out.println("\nPedido realizado com sucesso!");
            notaFiscal.exibirNotaFiscal();
            
        } else {
            System.out.println("Pedido cancelado.");
        }
    }
   
    private void escolherEntrega(Pedido pedido) {
        System.out.println("\n--- Escolha o Tipo de Entrega ---");
        System.out.println("1 - Entrega Padrão (R$ 8,50)");
        System.out.println("2 - Entrega Rápida (R$ 15,90)");
        System.out.println("3 - Retirada na Loja (Grátis)");
        System.out.print("Opção: ");
    
        String opcao = scanner.nextLine().trim();
        IEntrega entrega;
    
        switch (opcao) {
            case "2":
                entrega = new EntregaRapida();
                break;
            case "3":
                entrega = new RetiradaLoja();
                break;
            case "1":
            default:
                entrega = new EntregaPadrao();
                break;
        }
    
        pedido.definirEntrega(entrega);
        System.out.println(entrega.getTipo() + " selecionada - Taxa: R$ " +
                String.format("%.2f", entrega.calcularTaxa()));
    }
    
    // Substitua seu método por esta versão
    private IPagamento fazerPagamento(Pedido pedido) {
        System.out.println("\n--- Escolha a Forma de Pagamento ---");
        List<IPagamento> opcoes = List.of(
                new PagamentoCartaoCredito(),
                new PagamentoCartaoDebito(),
                new PagamentoBoleto(),
                new PagamentoPix()
        );

        // Laço modificado para incluir o aviso de desconto
        for (int i = 0; i < opcoes.size(); i++) {
            IPagamento opcaoAtual = opcoes.get(i);
            String textoOpcao = (i + 1) + " - " + opcaoAtual.toString();

            // VERIFICAÇÃO: Se a opção for PIX, adiciona o texto do desconto
            if (opcaoAtual instanceof PagamentoPix) {
                textoOpcao += " (15% de desconto!)";
            }

            System.out.println(textoOpcao);
        }
        
        System.out.println("0 - Cancelar");
        System.out.print("Opção: ");
        
        try {
            int escolha = Integer.parseInt(scanner.nextLine().trim());
            if (escolha > 0 && escolha <= opcoes.size()) {
                return opcoes.get(escolha - 1);
            }
        } catch (NumberFormatException e) { /* Ignora e cancela */ }
        
        System.out.println("Pagamento cancelado.");
        return null;
    }

    // Substitua o método verHistoricoPedidos inteiro por este
    private void verHistoricoPedidos() {
        if (!usuarioLogado.temClienteAssociado() || usuarioLogado.getCliente().getPedidos().isEmpty()) {
            System.out.println("\nVocê ainda não fez nenhum pedido.");
            return;
        }
        
        System.out.println("\n=== SEU HISTÓRICO DE PEDIDOS ===");
        List<Pedido> pedidos = usuarioLogado.getCliente().getPedidos();
        for (Pedido p : pedidos) {
            p.exibirResumo(); // Mostra o resumo de cada pedido
        }
        
        // NOVA PARTE: Opção para ver a nota fiscal detalhada
        System.out.println("---------------------------------------");
        System.out.print("Deseja ver a nota fiscal de algum pedido? (Digite o ID do pedido ou 0 para voltar): ");
        
        int idPedido;
        try {
            idPedido = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (idPedido == 0) {
            return; // Volta ao menu anterior
        }

        // Procura o pedido com o ID fornecido
        Pedido pedidoEscolhido = null;
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                pedidoEscolhido = p;
                break;
            }
        }

        if (pedidoEscolhido != null) {
            if (pedidoEscolhido.getNotaFiscal() != null) {
                // Se o pedido foi encontrado e tem uma nota fiscal, exibe-a
                pedidoEscolhido.getNotaFiscal().exibirNotaFiscal();
            } else {
                System.out.println("Não há nota fiscal associada a este pedido.");
            }
        } else {
            System.out.println("Pedido com ID " + idPedido + " não encontrado.");
        }
    }
    
    // Exibição de dados 
    private void exibirDadosCliente(Cliente cliente) {
        System.out.println("\n=== DADOS DO CLIENTE ===");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Tipo: " + cliente.getTipoCliente());
        System.out.println("Nome: " + cliente.getNome());
        
        if (cliente instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) cliente;
            System.out.println("CPF: " + pf.getCpf());
            if (pf.getTelefone() != null && !pf.getTelefone().isEmpty()) {
                System.out.println("Telefone: " + pf.getTelefone());
            }
        } else if (cliente instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) cliente;
            System.out.println("Razão Social: " + pj.getRazaoSocial());
            System.out.println("Nome Fantasia: " + pj.getNomeFantasia());
            System.out.println("CNPJ: " + pj.getCnpj());
            if (pj.getResponsavel() != null && !pj.getResponsavel().isEmpty()) {
                System.out.println("Responsável: " + pj.getResponsavel());
            }
        }
        
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Data de Cadastro: " + cliente.getDataCadastroFormatada());
        System.out.println("Status: " + (cliente.isAtivo() ? "Ativo" : "Inativo"));
        
        // Exibe endereço principal se existir
        Endereco enderecoPrincipal = cliente.getEnderecoPrincipal();
        if (enderecoPrincipal != null) {
            System.out.println("Endereço Principal: " + enderecoPrincipal.getDescricaoCompleta());
        } else {
            System.out.println("Nenhum endereço cadastrado");
        }
        
        System.out.println("Total de Endereços: " + cliente.getEnderecos().size());
        System.out.println("========================\n");
    }

    private void logout() {
        if (usuarioLogado != null) {
            System.out.println("Usuário " + usuarioLogado.getIdentificador() + " deslogado com sucesso!\n");
            usuarioLogado = null;
        }
    }

    
    private void menuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            if (usuarioLogado.temClienteAssociado()) {
                System.out.println("Cliente: " + usuarioLogado.getCliente().getNome());
            }
            System.out.println("1 - Criar/Ver conta de cliente");
            System.out.println("2 - Gerenciar endereços");
            System.out.println("3 - Gerenciar produtos (Admin)");
            System.out.println("4 - Fazer Pedido");
            System.out.println("5 - Ver Histórico de Pedidos");
            System.out.println("6 - Atualizar dados do cliente");
            System.out.println("7 - Falar com Atendente Virtual"); 
            System.out.println("8 - Logout");                      
            System.out.print("Escolha sua opção: ");
            
            String opcao = scanner.nextLine().trim();
            
            switch (opcao) {
                case "1":
                    criarContaCliente();
                    break;
                case "2":
                    gerenciarEnderecos();
                    break;
                case "3":
                    gerenciarProdutos();
                    break;
                case "4":
                    fazerPedido();
                    break;
                case "5":
                    verHistoricoPedidos();
                    break;
                case "6":
                    atualizarDadosCliente();
                    break;
                case "7": // <-- NOVO CASE
                    iniciarAtendimentoVirtual(); 
                    break;
                case "8": // <-- CASE DO LOGOUT ATUALIZADO
                    logout();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }
    }

    private void atualizarDadosCliente() {
        if (!usuarioLogado.temClienteAssociado()) {
            System.out.println("Você precisa criar uma conta de cliente primeiro!\n");
            return;
        }

        Cliente cliente = usuarioLogado.getCliente();
        
        while (true) {
            System.out.println("\n=== ATUALIZAR DADOS ===");
            System.out.println("1 - Alterar nome");
            System.out.println("2 - Alterar email");
            if (cliente instanceof PessoaFisica) {
                System.out.println("3 - Alterar telefone");
            } else if (cliente instanceof PessoaJuridica) {
                System.out.println("3 - Alterar nome fantasia");
                System.out.println("4 - Alterar responsável");
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine().trim();
                    if (!novoNome.isEmpty()) {
                        cliente.setNome(novoNome);
                        System.out.println("Nome atualizado com sucesso!\n");
                    }
                    break;
                    
                case "2":
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine().trim();
                    if (validarEmail(novoEmail)) {
                        cliente.setEmail(novoEmail);
                        System.out.println("Email atualizado com sucesso!\n");
                    } else {
                        System.out.println("Email inválido!\n");
                    }
                    break;
                    
                case "3":
                    if (cliente instanceof PessoaFisica) {
                        System.out.print("Novo telefone: ");
                        String novoTelefone = scanner.nextLine().trim();
                        ((PessoaFisica) cliente).setTelefone(novoTelefone);
                        System.out.println("Telefone atualizado com sucesso!\n");
                    } else if (cliente instanceof PessoaJuridica) {
                        System.out.print("Novo nome fantasia: ");
                        String novoNomeFantasia = scanner.nextLine().trim();
                        if (!novoNomeFantasia.isEmpty()) {
                            ((PessoaJuridica) cliente).setNomeFantasia(novoNomeFantasia);
                            System.out.println("Nome fantasia atualizado com sucesso!\n");
                        }
                    }
                    break;
                    
                case "4":
                    if (cliente instanceof PessoaJuridica) {
                        System.out.print("Novo responsável: ");
                        String novoResponsavel = scanner.nextLine().trim();
                        ((PessoaJuridica) cliente).setResponsavel(novoResponsavel);
                        System.out.println("Responsável atualizado com sucesso!\n");
                    } else {
                        System.out.println("Opção inválida!\n");
                    }
                    break;
                    
                case "0":
                    return;
                    
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }

    // Relatórios e estatísticas (para administração)
    private void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        System.out.println("Total de usuários cadastrados: " + usuarios.size());
        System.out.println("Total de clientes: " + clientes.size());
        
        long pessoasFisicas = clientes.stream().filter(c -> c instanceof PessoaFisica).count();
        long pessoasJuridicas = clientes.stream().filter(c -> c instanceof PessoaJuridica).count();
        
        System.out.println("  - Pessoas Físicas: " + pessoasFisicas);
        System.out.println("  - Pessoas Jurídicas: " + pessoasJuridicas);
        
        long clientesAtivos = clientes.stream().filter(Cliente::isAtivo).count();
        System.out.println("Clientes ativos: " + clientesAtivos);
        
        int totalEnderecos = clientes.stream().mapToInt(c -> c.getEnderecos().size()).sum();
        System.out.println("Total de endereços cadastrados: " + totalEnderecos);
        System.out.println("Total de produtos no catálogo: " + produtos.size());
        
        int totalPedidos = clientes.stream().mapToInt(c -> c.getPedidos().size()).sum();
        System.out.println("Total de pedidos realizados: " + totalPedidos);
        System.out.println("================================\n");
    }

    private void iniciarAtendimentoVirtual() {
        if (!usuarioLogado.temClienteAssociado()) {
            System.out.println("\nVocê precisa ter uma conta de cliente para usar o chat.");
            return;
        }
        
        Cliente clienteAtual = usuarioLogado.getCliente();
        SessaoAtendimento novaSessao = new SessaoAtendimento(clienteAtual, this.atendenteVirtual);
        
        clienteAtual.adicionarSessaoDeAtendimento(novaSessao);
        
        novaSessao.iniciarSessao(this.scanner);
    }

    // Sistema principal
    public void iniciarSistema() {
        System.out.println("=================================");
        System.out.println("  SISTEMA E-COMMERCE - CLIENTES  ");
        System.out.println("=================================\n");

        // Inicializa o nosso atendente virtual com o nome "AVA"
        this.atendenteVirtual = new AtendenteVirtual("AVA"); 

        // Dados de exemplo para teste
        inicializarDadosExemplo();

        while (true) {
            System.out.println("\n=== MENU INICIAL ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Login");
            System.out.println("3 - Estatísticas do sistema");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    cadastrarUsuario();
                    break;
                case "2":
                    if (login()) {
                        menuPrincipal();
                    }
                    break;
                case "3":
                    exibirEstatisticas();
                case "4":
                    System.out.println("Encerrando sistema. Obrigado por usar nosso sistema!");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }
        }
    }

    // Método para inicializar alguns dados de exemplo
    private void inicializarDadosExemplo() {
        // Produtos
         produtos.add(new Produto("Notebook Pro", "Notebook de alta performance", 4500.00, 10));
        produtos.add(new Produto("Mouse sem Fio", "Mouse ergonômico com 6 botões", 89.90, 50));
        produtos.add(new Produto("Teclado Mecânico RGB", "Teclado para gamers com switches azuis", 350.00, 25));
        produtos.add(new Produto("Monitor 4K 27 polegadas", "Monitor com painel IPS e alta resolução", 1800.00, 15));
        produtos.add(new Produto("Webcam Full HD 1080p", "Webcam com microfone integrado", 259.90, 40));
        produtos.add(new Produto("Headset Gamer 7.1", "Fone com som surround e cancelamento de ruído", 450.00, 30));
        produtos.add(new Produto("Mousepad Gamer XL", "Superfície grande para mouses de baixa sensibilidade", 99.90, 100));
        produtos.add(new Produto("Caixas de Som 2.1", "Sistema de som estéreo com subwoofer", 320.00, 20));
        produtos.add(new Produto("Microfone Condensador USB", "Ideal para streaming, podcasts e gravações", 550.00, 18));
        produtos.add(new Produto("Placa de Vídeo RTX 4060 8GB", "GPU de última geração para jogos em Full HD", 2800.00, 8));
        produtos.add(new Produto("Processador Core i7", "CPU de 12 núcleos para alta performance", 2500.00, 12));
        produtos.add(new Produto("SSD NVMe 1TB", "Armazenamento ultrarrápido para sistema e jogos", 480.00, 50));
        produtos.add(new Produto("Memória RAM 16GB DDR5", "Kit com 2x8GB 5200MHz para mais desempenho", 650.00, 35));
        produtos.add(new Produto("Placa-mãe B760M", "Placa-mãe com suporte para DDR5 e PCIe 5.0", 950.00, 15));
        produtos.add(new Produto("Fonte de Alimentação 750W", "Fonte com certificação 80 Plus Bronze", 499.90, 22));
        produtos.add(new Produto("Cadeira Gamer Ergonômica", "Cadeira com ajuste de altura e suporte lombar", 1200.00, 10));
        produtos.add(new Produto("Mochila para Notebook 15.6\"", "Mochila reforçada e resistente à água", 189.90, 60));
        produtos.add(new Produto("HD Externo 2TB USB 3.0", "Disco rígido portátil para backups", 380.00, 45));
        produtos.add(new Produto("Roteador Wi-Fi 6", "Roteador dual-band para maior velocidade de internet", 450.00, 28));
        produtos.add(new Produto("Nobreak 600VA", "Proteção contra quedas de energia para seus eletrônicos", 350.00, 30));

        // Usuários
        Usuario usuario1 = new Usuario("teste@gmail.com", "EMAIL", "123456");
        Usuario usuario2 = new Usuario("12345678901", "CPF", "123456");
        Usuario usuario3 = new Usuario("12345678000195", "CNPJ", "123456");
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        // Clientes
        PessoaFisica pf = new PessoaFisica("João Silva", "joao.silva@email.com", "12345678901", "11999998888");
        Endereco endPf = new Endereco("Casa", "Rua das Flores", "123", "", "Jardim", "São Paulo", "SP", "01001-000");
        pf.adicionarEndereco(endPf);
        pf.definirEnderecoPrincipal(endPf.getId());
        clientes.add(pf);
        usuario2.associarCliente(pf);
        
        PessoaJuridica pj = new PessoaJuridica("Tech Solutions", "TechSol", "contato@techsol.com", "12345678000195", "Maria Santos");
        Endereco endPj = new Endereco("Sede", "Av. Principal", "1000", "Andar 5", "Centro", "Rio de Janeiro", "RJ", "20040-004");
        pj.adicionarEndereco(endPj);
        pj.definirEnderecoPrincipal(endPj.getId());
        clientes.add(pj);
        usuario3.associarCliente(pj);
    }
    
    
    // Método main
    public static void main(String[] args) {
        SistemaCliente sistema = new SistemaCliente();
        sistema.iniciarSistema();
    }
}
