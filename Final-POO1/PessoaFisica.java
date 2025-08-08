import java.time.LocalDateTime;

class PessoaFisica extends Cliente {
    private String cpf;
    private String telefone;
    private LocalDateTime dataNascimento;

    public PessoaFisica(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = formatarCPF(cpf);
    }

    public PessoaFisica(String nome, String email, String cpf, String telefone) {
        this(nome, email, cpf);
        this.telefone = telefone;
    }

    @Override
    public String getIdentificacao() {
        return cpf;
    }

    @Override
    public String getTipoCliente() {
        return "Pessoa Física";
    }

    @Override
    public boolean validarIdentificacao() {
        return validarCPF(cpf);
    }

    // Validação básica de CPF 
    private boolean validarCPF(String cpf) {
        String numeros = cpf.replaceAll("\\D", "");
        if (numeros.length() != 11) return false;
        
        // Verifica se todos os dígitos são iguais
        if (numeros.matches("(\\d)\\1{10}")) return false;
        
        // Aqui você pode implementar a validação completa dos dígitos verificadores
        return true;
    }

    private String formatarCPF(String cpfRaw) {
        String numeros = cpfRaw.replaceAll("\\D", "");
        if (numeros.length() != 11) return cpfRaw;
        return numeros.substring(0,3) + "." + numeros.substring(3,6) + "." + 
               numeros.substring(6,9) + "-" + numeros.substring(9,11);
    }

    // Getters e Setters específicos
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getDataNascimento() { return dataNascimento; }
    
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setDataNascimento(LocalDateTime dataNascimento) { this.dataNascimento = dataNascimento; }
}
