public class Aluno extends Pessoa{
    private String matricula;
    private String curso;

    public void matricular(){
        System.out.println("Matricula efetuada");
    }

    public void emitirDiploma(){
        System.out.println("Emitindo diploma...");
    }
}
