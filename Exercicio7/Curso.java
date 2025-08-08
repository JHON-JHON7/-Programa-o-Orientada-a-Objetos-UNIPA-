import java.util.ArrayList;

class Curso {
    private String titulo;
    private int duracao;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private Professor professor; 

    public Curso(String titulo, int duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public void adicionarAluno(Aluno aluno) {
        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracao() {
        return duracao;
    }
}
