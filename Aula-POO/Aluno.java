public class Aluno {
    
    private String nome;
    private int idade;
    private String endereco;
    private String curso;
    private int periodo;
    private boolean matriculado;

    public void apresentaAluno(){
        System.out.println("Aluno: "+this.nome+"\n"+"Idade: "+this.idade+"\n"+"Reside em: "+this.endereco+"\n"+"Período: "+this.periodo);
    }

    public void matricular(String opcao_curso){
        /*
         Verificamos se o aluno já está matriculado em algum curso
         */
        if(this.curso == null){
            this.matriculado = true;
            this.curso = opcao_curso;
            this.periodo = 1;
            System.out.println("Aluno "+this.nome+" matriculado no curso de "+this.curso);
        }
        else{
            System.out.println("Aluno já matriculado no curso de "+this.curso);
        }
    }

    public String verificaSituacao(){
        String situacao = "";
        if(this.curso != ""){
            situacao = "Aluno em situação regular";
        }else{
            situacao = "Aluno não matriculado";
        }

        return situacao;
    }

    /*
     * Métodos Getters. Usamos para retornar os valores dos atributos.
     * É necessário informar o tipo do retorno.
     */

    public String getNome(){
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public String getCurso(){
        return this.curso;
    }

    public int getPeriodo(){
        return this.periodo;
    }

    public boolean getMatriculado(){
        return this.matriculado;
    }

    /*
     * Métodos Setters. Usamos para alterar os valores dos atributos
     * É necessário
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

}