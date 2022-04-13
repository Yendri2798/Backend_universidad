package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Matricula {
    @Getter
    @Setter
    private Prematricula prematricula;
    @Getter
    @Setter
    private Alumno alumno;

    public Matricula(Prematricula prematricula, Alumno alumno) {
        this.prematricula = prematricula;
        this.alumno = alumno;
    }

    public Matricula() {
        this.prematricula = new Prematricula();
        this.alumno = new Alumno();
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "prematricula=" + prematricula +
                ", alumno=" + alumno +
                '}';
    }
}
