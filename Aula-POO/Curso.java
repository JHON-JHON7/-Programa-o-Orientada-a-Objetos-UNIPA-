public class Curso {

    private String nomeCurso;
    private int cargaHorariaCurso;
    private String area;

    public String getNomeCurso() {
        return this.nomeCurso;
    }

    public int getCargaHorariaCurso() {
        return this.cargaHorariaCurso;
    }

    public String getArea() {
        return this.area;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public void setCargaHorariaCurso(int cargaHorariaCurso) {
        this.cargaHorariaCurso = cargaHorariaCurso;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void apresentaCurso(){
        System.out.println("Curso: "+this.nomeCurso+"\n"+"Carga Horária: "+this.cargaHorariaCurso+"\n"+"Área: "+this.area);
    }
}