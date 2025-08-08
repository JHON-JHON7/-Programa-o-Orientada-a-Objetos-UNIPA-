class  Desenvolvedor extends Funcionario {
    
    private String linguagem1;
    private String linguagem2;

q
    public Desenvolvedor(String nome, String cpf, String matricula, double salario){
        super(nome, cpf, matricula, salario);
    }

    public String getLinguagem1() {
        return linguagem1;
    }

    public void setLinguagem1(String liguagem1) {
        this.linguagem1 = liguagem1;
    }

    public String getLinguagem2() {
        return linguagem2;
    }

    public void setLinguagem2(String linguagem2) {
        this.linguagem2 = linguagem2;
    }

     
}
