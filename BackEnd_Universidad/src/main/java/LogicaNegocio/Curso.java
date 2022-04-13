package LogicaNegocio;


import lombok.Getter;
import lombok.Setter;

public class Curso {
    @Getter
    @Setter
    private String codigo_Curso;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private int creditos;
    @Getter
    @Setter
    private int horas_semanales;
    @Getter
    @Setter
    private Carrera carrera;

    public Curso(String codigoCurso, String nombre, int creditos, int horas, Carrera carrera) {
        this.codigo_Curso = codigoCurso;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas_semanales = horas;
        this.carrera = carrera;
    }

    public Curso() {
        this.codigo_Curso = "";
        this.nombre = "";
        this.creditos = 0;
        this.horas_semanales = 0;
        this.carrera = new Carrera();
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigoCurso='" + codigo_Curso + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", horas=" + horas_semanales +
                ", carrera=" + carrera +
                '}';
    }
}

