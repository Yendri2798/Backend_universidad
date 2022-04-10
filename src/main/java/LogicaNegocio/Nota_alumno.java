package LogicaNegocio;

public class Nota_alumno {
    private Alumno alumno;
    private String id_Curso;
    private int nota;

    public Nota_alumno(Alumno alumno, String id_Curso, int nota) {
        this.alumno = alumno;
        this.id_Curso = id_Curso;
        this.nota = nota;
    }

    public Nota_alumno() {
        this.alumno = new Alumno();
        this.id_Curso = "";
        this.nota = 0;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getId_Curso() {
        return id_Curso;
    }

    public void setId_Curso(String id_Curso) {
        this.id_Curso = id_Curso;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Nota_alumno{" +
                "alumno=" + alumno +
                ", id_Curso='" + id_Curso + '\'' +
                ", nota=" + nota +
                '}';
    }
}
