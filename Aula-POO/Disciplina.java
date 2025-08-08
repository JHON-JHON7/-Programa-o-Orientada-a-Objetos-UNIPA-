class Disciplina {
    
    private String nomeDisciplina;
    private int cargaHorariaDisciplina;
    private String curso;

    public String getNomeDisciplina() {
        return this.nomeDisciplina;
    }

    public int getCargaHorariaDisciplina() {
        return this.cargaHorariaDisciplina;
    }

    public String getCurso() {
        return this.curso;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public void setCargaHorariaDisciplina(int cargaHorariaDisciplina) {
        this.cargaHorariaDisciplina = cargaHorariaDisciplina;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void apresentaDisciplina(){
        System.out.println("Disciplina: "+this.nomeDisciplina+"\n"+"Carga Horária: "+this.cargaHorariaDisciplina+"\n"+"Curso: "+this.curso);
    }
}