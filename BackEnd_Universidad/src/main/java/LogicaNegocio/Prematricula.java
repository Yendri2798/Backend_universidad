package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Prematricula {
    @Getter
    @Setter
    private int idPrematricula;
    @Getter
    @Setter
    private String estado;
    @Getter
    @Setter
    private Alumno alumno;
    @Getter
    @Setter
    private Grupo grupo;

    public Prematricula(int idPrematricula, String estado, Alumno alumno, Grupo grupo) {
        this.idPrematricula = idPrematricula;
        this.estado = estado;
        this.alumno = alumno;
        this.grupo = grupo;
    }

    public Prematricula(int idPrematricula) {
        this.idPrematricula = idPrematricula;
    }

    public Prematricula() {
        this.idPrematricula = 0;
        this.estado = "";
        this.alumno = new Alumno();
        this.grupo = new Grupo();
    }

    @Override
    public String toString() {
        return "Prematricula{" +
                "idPrematricula=" + idPrematricula +
                ", estado='" + estado + '\'' +
                ", alumno=" + alumno +
                ", grupo=" + grupo +
                '}';
    }
}
