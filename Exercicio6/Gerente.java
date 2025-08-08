class Gerente extends Funcionario {
    
    private Funcionario funcionario1;
    private Funcionario funcionario2;
     
    
    public Gerente(String nome, String cpf, String matricula, double salario){
        super(nome, cpf, matricula, salario);
    }

    public Funcionario getFuncionario1() {
        return funcionario1;
    }

    public void setFuncionario1(Funcionario funcionario1) {
        this.funcionario1 = funcionario1;
    }

    public Funcionario getFuncionario2() {
        return funcionario2;
    }

    public void setFuncionario2(Funcionario funcionario2) {
        this.funcionario2 = funcionario2;
    }

    


}
