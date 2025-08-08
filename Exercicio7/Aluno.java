import java.util.ArrayList;

class Aluno {
    private String nome;
    private int idade;
    private ArrayList<Curso> cursos = new ArrayList<>();

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public void matricular(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            curso.adicionarAluno(this);
        }
    }

    public void desmatricular(Curso curso) {
        if (cursos.contains(curso)) {
            cursos.remove(curso);
            curso.removerAluno(this);
        }
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}