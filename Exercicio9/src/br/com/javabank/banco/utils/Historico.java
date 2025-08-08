package br.com.javabank.banco.utils;
import java.util.ArrayList;
import br.com.javabank.banco.conta.ContaCorrente;
public class Historico {
    
    // Métodos estáticos (static) são métodos de classe e não objeto
    // Isto é, podem ser utilizados sem instanciar um objeto
    // Exemplo: Historico.exibirHistorico();

    public static boolean validarHistorico(ArrayList<String> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            System.out.println("Histórico de transações vazio ou nulo.");
            return false;
        }
        for (String transacao : transacoes) {
            if (transacao == null || transacao.trim().isEmpty()) {
                System.out.println("Transação inválida encontrada no histórico.");
                return false;
            }
        }
        return true;
    }

    public static void exibirHistorico(ContaCorrente conta) {
        if (!validarHistorico(conta.getTransacoes())) {
            return;
        }
        System.out.println("Histórico de Transações:");
        for (String transacao : conta.getTransacoes()) {
            System.out.println("- " + transacao);
        }
    }

    public static boolean adicionarTransacao(ContaCorrente conta, String transacao) {
        conta.getTransacoes().add(transacao);
        System.out.println("Transação adicionada: " + transacao);
        return true;
    }
}
