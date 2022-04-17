package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Alumno {

    @Getter
    @Setter
    private String cedulaAlumno;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String primer_apellido;
    @Getter
    @Setter
    private int telefono;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private Date fechaNacimiento;
    @Getter
    @Setter
    private Carrera carrera;


    public Alumno(String cedulaAlumno, String nombre, String primer_apellido, int telefono, String email, Date fechaNacimiento, Carrera carrera) {
        this.cedulaAlumno = cedulaAlumno;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.carrera = carrera;
    }

    public Alumno(String cedulaAlumno) {
        this.cedulaAlumno = cedulaAlumno;
    }

    public Alumno() {
        this.cedulaAlumno = "";
        this.nombre = "";
        this.primer_apellido = "";
        this.telefono = 0;
        this.email = "";
        this.fechaNacimiento = new Date();
        this.carrera = new Carrera();
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "cedulaAlumno='" + cedulaAlumno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + primer_apellido + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", carrera=" + carrera +
                '}';
    }

}
