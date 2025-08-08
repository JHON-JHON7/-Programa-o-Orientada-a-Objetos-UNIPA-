import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // <-- ESTA É A LINHA QUE ESTAVA FALTANDO

public class SessaoAtendimento {
    private Cliente cliente;
    private AtendenteVirtual atendente;
    private List<String> historicoConversa;
    private LocalDateTime dataInicio;
    private boolean ativa;

    public SessaoAtendimento(Cliente cliente, AtendenteVirtual atendente) {
        this.cliente = cliente;
        this.atendente = atendente;
        this.historicoConversa = new ArrayList<>();
        this.dataInicio = LocalDateTime.now();
        this.ativa = true;
    }

    public void adicionarMensagem(String autor, String mensagem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timestamp = LocalDateTime.now().format(formatter);
        this.historicoConversa.add(String.format("[%s] %s: %s", timestamp, autor, mensagem));
    }

    public void iniciarSessao(Scanner scanner) {
        String mensagemBoasVindas = "Olá! Você iniciou uma sessão de atendimento com " + atendente.getNome() + ". Digite 'sair' a qualquer momento para encerrar.";
        System.out.println("\n" + "=".repeat(mensagemBoasVindas.length()));
        System.out.println(mensagemBoasVindas);
        System.out.println("=".repeat(mensagemBoasVindas.length()));
        
        adicionarMensagem("SISTEMA", "Sessão iniciada.");

        while (this.ativa) {
            System.out.print(cliente.getNome() + ": ");
            String mensagemCliente = scanner.nextLine();

            if (mensagemCliente.equalsIgnoreCase("sair")) {
                this.ativa = false;
                String respostaAtendente = "Entendido. Encerrando a sessão. Até logo!";
                System.out.println(atendente.getNome() + ": " + respostaAtendente);
                adicionarMensagem(cliente.getNome(), mensagemCliente);
                adicionarMensagem(atendente.getNome(), respostaAtendente);
                adicionarMensagem("SISTEMA", "Sessão encerrada pelo cliente.");
            } else {
                String respostaAtendente = atendente.responder(mensagemCliente);
                System.out.println(atendente.getNome() + ": " + respostaAtendente);
                adicionarMensagem(cliente.getNome(), mensagemCliente);
                adicionarMensagem(atendente.getNome(), respostaAtendente);
            }
        }
    }
}