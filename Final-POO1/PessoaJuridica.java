class PessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String telefone;
    private String responsavel; // Nome do responsável pela conta

    public PessoaJuridica(String razaoSocial, String email, String cnpj) {
        super(razaoSocial, email);
        this.cnpj = formatarCNPJ(cnpj);
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = razaoSocial; // Por padrão, usa a razão social
    }

    public PessoaJuridica(String razaoSocial, String nomeFantasia, String email, String cnpj, String responsavel) {
        this(razaoSocial, email, cnpj);
        this.nomeFantasia = nomeFantasia;
        this.responsavel = responsavel;
    }

    @Override
    public String getIdentificacao() {
        return cnpj;
    }

    @Override
    public String getTipoCliente() {
        return "Pessoa Jurídica";
    }

    @Override
    public boolean validarIdentificacao() {
        return validarCNPJ(cnpj);
    }

    // Validação básica de CNPJ (pode ser melhorada com algoritmo completo)
    private boolean validarCNPJ(String cnpj) {
        String numeros = cnpj.replaceAll("\\D", "");
        if (numeros.length() != 14) return false;
        
        // Aqui você pode implementar a validação completa dos dígitos verificadores
        return true;
    }

    private String formatarCNPJ(String cnpjRaw) {
        String numeros = cnpjRaw.replaceAll("\\D", "");
        if (numeros.length() != 14) return cnpjRaw;
        return numeros.substring(0,2) + "." + numeros.substring(2,5) + "." + 
               numeros.substring(5,8) + "/" + numeros.substring(8,12) + "-" + numeros.substring(12,14);
    }

    // Getters e Setters específicos
    public String getCnpj() { return cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getNomeFantasia() { return nomeFantasia; }
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public String getTelefone() { return telefone; }
    public String getResponsavel() { return responsavel; }
    
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}
