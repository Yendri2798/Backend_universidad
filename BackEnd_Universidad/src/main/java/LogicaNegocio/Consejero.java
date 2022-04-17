package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Consejero {

    @Getter
    @Setter
    private Alumno alumno;
    @Getter
    @Setter
    private Profesor profesor;

    public Consejero(Alumno alumno, Profesor profesor) {
        this.alumno = alumno;
        this.profesor = profesor;
    }

    public Consejero() {
        this.alumno = new Alumno();
        this.profesor = new Profesor();
    }

    @Override
    public String toString() {
        return "Consejero{" +
                "alumno=" + alumno +
                ", profesor=" + profesor +
                '}';
    }
}
