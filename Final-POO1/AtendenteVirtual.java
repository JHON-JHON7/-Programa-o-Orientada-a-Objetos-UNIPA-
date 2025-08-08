import java.util.List;
import java.util.Random;

public class AtendenteVirtual {
    private String nome;

    public AtendenteVirtual(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String responder(String mensagemCliente) {
        String mensagemLower = mensagemCliente.toLowerCase();

        // --- SAUDAÇÕES E DESPEDIDAS ---
        if (mensagemLower.contains("ola") || mensagemLower.contains("oi") || mensagemLower.contains("bom dia") || mensagemLower.contains("boa tarde")) {
            return "Olá! Sou a " + this.nome + ", sua assistente virtual. Como posso ajudar?";
        }
        if (mensagemLower.contains("obrigado") || mensagemLower.contains("tchau") || mensagemLower.contains("ate mais")) {
            return "De nada! Se precisar de mais alguma coisa, estarei por aqui. Até mais!";
        }

        // --- DÚVIDAS SOBRE PEDIDOS E ENTREGA ---
        if (mensagemLower.contains("status") || mensagemLower.contains("meu pedido") || mensagemLower.contains("onde esta")) {
            return "Você pode consultar o status de todos os seus pedidos na seção 'Histórico de Pedidos' no menu principal.";
        }
        if (mensagemLower.contains("prazo") || mensagemLower.contains("quando chega") || mensagemLower.contains("demora")) {
            return "O prazo de entrega varia por região. A Entrega Padrão leva de 5 a 10 dias úteis e a Rápida de 1 a 3 dias úteis.";
        }
        if (mensagemLower.contains("alterar endereco") || mensagemLower.contains("endereco errado")) {
            return "Após a finalização do pedido, não é possível alterar o endereço de entrega por segurança. Por favor, verifique os dados antes de confirmar a compra.";
        }
        if (mensagemLower.contains("frete") || mensagemLower.contains("custo de envio")) {
            return "O valor do frete é fixo. Temos a Entrega Padrão por R$ 8,50 e a Entrega Rápida por R$ 15,90.";
        }
        if (mensagemLower.contains("nota fiscal") || mensagemLower.contains("nf-e") || mensagemLower.contains("danfe")) {
            return "Você pode visualizar a Nota Fiscal de cada compra na opção 'Ver Histórico de Pedidos' e digitando o ID do pedido desejado.";
        }
        if (mensagemLower.contains("cancelar pedido") || mensagemLower.contains("cancelamento")) {
            return "Cancelamentos podem ser solicitados em até 24 horas após a compra, diretamente na página de detalhes do seu pedido.";
        }
        
        // --- DÚVIDAS SOBRE PAGAMENTOS ---
        if (mensagemLower.contains("pagamento") || mensagemLower.contains("boleto") || mensagemLower.contains("cartao")) {
            return "Aceitamos Cartão de Crédito, Débito, Boleto e PIX (com 15% de desconto!).";
        }
        if (mensagemLower.contains("parcelar") || mensagemLower.contains("vezes")) {
            return "No momento, oferecemos parcelamento em até 12x sem juros no Cartão de Crédito.";
        }
        if (mensagemLower.contains("pagamento recusado") || mensagemLower.contains("nao consigo pagar")) {
            return "Se seu pagamento foi recusado, verifique os dados do cartão ou o saldo. Você também pode tentar gerar um boleto ou usar o PIX.";
        }
        if (mensagemLower.contains("cupom") || mensagemLower.contains("desconto")) {
            return "Fique de olho em nosso site para promoções! O desconto de 15% no PIX é aplicado automaticamente ao finalizar a compra.";
        }
        
        // --- DÚVIDAS SOBRE PRODUTOS E ESTOQUE ---
        if (mensagemLower.contains("devolucao") || mensagemLower.contains("devolver")) {
            return "Para devoluções, acesse a seção 'Histórico de Pedidos', selecione o pedido e siga as instruções para 'Solicitar Devolução'. O prazo é de 7 dias após o recebimento.";
        }
        if (mensagemLower.contains("garantia") || mensagemLower.contains("defeito")) {
            return "Nossos produtos possuem garantia de 90 dias contra defeitos de fabricação. Por favor, entre em contato com nosso suporte com o número do seu pedido.";
        }
        if (mensagemLower.contains("disponivel") || mensagemLower.contains("tem estoque")) {
            return "A disponibilidade de cada produto é mostrada na lista de produtos. Se estiver zerado, estamos trabalhando para repor o mais rápido possível!";
        }
        if (mensagemLower.contains("especificacao") || mensagemLower.contains("detalhes") || mensagemLower.contains("tamanho")) {
            return "Você pode encontrar todos os detalhes técnicos e especificações na página de cada produto em nosso site.";
        }

        // --- DÚVIDAS SOBRE A CONTA DO CLIENTE ---
        if (mensagemLower.contains("alterar senha") || mensagemLower.contains("esqueci a senha")) {
            return "Para alterar sua senha, acesse a opção 'Minha Conta' em nosso site e procure por 'Alterar Senha'.";
        }
        if (mensagemLower.contains("meus dados") || mensagemLower.contains("mudar email")) {
            return "Você pode atualizar seus dados cadastrais, como nome e email, na opção 'Atualizar dados do cliente' no menu principal.";
        }
        if (mensagemLower.contains("excluir conta") || mensagemLower.contains("cancelar cadastro")) {
            return "Para solicitar a exclusão da sua conta, por favor, envie um email para privacidade@ecommerce.com com sua solicitação.";
        }

        // --- CONVERSA GERAL E SUPORTE ---
        if (mensagemLower.contains("ajuda") || mensagemLower.contains("suporte")) {
            return "Claro! Se não conseguir te ajudar por aqui, você pode contatar nosso suporte especializado no email suporte@ecommerce.com.";
        }
        if (mensagemLower.contains("quem e voce") || mensagemLower.contains("robo")) {
            return "Eu sou a " + this.nome + ", um assistente virtual programado para ajudar com as dúvidas mais comuns sobre nossos produtos e serviços!";
        }
        
        // --- RESPOSTAS PADRÃO ---
        // Se nenhuma palavra-chave acima for encontrada, uma destas será escolhida aleatoriamente.
        List<String> respostasPadrao = List.of(
            "Não entendi muito bem. Você poderia ser mais específico?",
            "Para essa questão, recomendo contatar nosso suporte humano pelo email suporte@ecommerce.com para uma ajuda detalhada."
        );
        return respostasPadrao.get(new Random().nextInt(respostasPadrao.size()));
    }
}