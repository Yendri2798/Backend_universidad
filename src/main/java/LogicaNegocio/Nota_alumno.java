package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Nota_alumno {
    @Getter
    @Setter
    private Alumno alumno;
    @Getter
    @Setter
    private String id_Curso;
    @Getter
    @Setter
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

    @Override
    public String toString() {
        return "Nota_alumno{" +
                "alumno=" + alumno +
                ", id_Curso='" + id_Curso + '\'' +
                ", nota=" + nota +
                '}';
    }
}
