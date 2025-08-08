package Exercicio5;

public class Funcionario {
    private String nome;
    private String cpf;
    private String cargo;
    private double salarioBase;

    public Funcionario(String nome, String cpf, String cargo, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String  consultarInfoFucionar(){
        return "Nome: " + nome + "\nCPF: " + cpf + "\nCargo: " + cargo + "\nSalario do Funcinario: " + salarioBase;
    }


    // Promover o funcionário
    public void promover(String novoCargo, double novoSalario) {
        if (novoSalario > this.salarioBase) {
            this.cargo = novoCargo;
            this.salarioBase = novoSalario;
            System.out.println(nome + " foi promovido para " + cargo + " com novo salário de R$ " + salarioBase);
        } else {
            System.out.println("O novo salário deve ser maior que o salário atual.");
        }
    }

   
}