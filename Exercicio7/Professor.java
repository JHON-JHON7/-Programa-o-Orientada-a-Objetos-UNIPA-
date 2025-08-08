import java.util.ArrayList;

class Professor {
    private String nome;
    private String especialidade;
    private ArrayList<Curso> cursos = new ArrayList<>();

    public Professor(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public void adicionarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            curso.setProfessor(this);
        }
    }

    public void removerCurso(Curso curso) {
        if (cursos.contains(curso)) {
            cursos.remove(curso);
            curso.setProfessor(null);
        }
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
