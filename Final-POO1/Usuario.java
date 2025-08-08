import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

class Usuario {
    private String identificador;
    private String tipoIdentificador;
    private String senhaHash;
    private Cliente cliente; // Referência para o cliente associado
    private LocalDateTime ultimoLogin;

    public Usuario(String identificador, String tipoIdentificador, String senha) {
        this.tipoIdentificador = tipoIdentificador.toUpperCase();
        this.identificador = formatarIdentificador(identificador, tipoIdentificador);
        this.senhaHash = gerarHashSenha(senha);
    }

    // Associa um cliente a este usuário
    public void associarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean temClienteAssociado() {
        return cliente != null;
    }

    private String formatarIdentificador(String id, String tipo) {
        tipo = tipo.toUpperCase();
        if (tipo.equals("CPF")) {
            return formatarCPF(id);
        } else if (tipo.equals("CNPJ")) {
            return formatarCNPJ(id);
        } else {
            return id.trim().toLowerCase();
        }
    }

    private String gerarHashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(senha.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return senha; // Fallback não recomendado
        }
    }

    public boolean validarSenha(String senha) {
        String hashTentativa = gerarHashSenha(senha);
        return senhaHash.equals(hashTentativa);
    }

    public void atualizarUltimoLogin() {
        this.ultimoLogin = LocalDateTime.now();
    }

    private String formatarCPF(String cpfRaw) {
        String numeros = cpfRaw.replaceAll("\\D", "");
        if(numeros.length() != 11) return cpfRaw;
        return numeros.substring(0,3) + "." + numeros.substring(3,6) + "." + 
               numeros.substring(6,9) + "-" + numeros.substring(9,11);
    }

    private String formatarCNPJ(String cnpjRaw) {
        String numeros = cnpjRaw.replaceAll("\\D", "");
        if(numeros.length() !=14) return cnpjRaw;
        return numeros.substring(0,2) + "." + numeros.substring(2,5) + "." + 
               numeros.substring(5,8) + "/" + numeros.substring(8,12) + "-" + numeros.substring(12,14);
    }

    // Getters
    public String getIdentificador() { return identificador; }
    public String getTipoIdentificador() { return tipoIdentificador; }
    public Cliente getCliente() { return cliente; }
    public LocalDateTime getUltimoLogin() { return ultimoLogin; }
}
