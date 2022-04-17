package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    @Getter
    @Setter
    private String cedula_Profesor;
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
    private List<Grupo> grupos;

    public Profesor(String cedula_Profesor, String nombre, String primer_apellido, int telefono, String email, List<Grupo> grupos) {
        this.cedula_Profesor = cedula_Profesor;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.telefono = telefono;
        this.email = email;
        this.grupos = grupos;
    }

    public Profesor(String cedula_Profesor, String nombre, String primer_apellido, int telefono, String email) {
        this.cedula_Profesor = cedula_Profesor;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Profesor(String cedula_Profesor) {
        this.cedula_Profesor = cedula_Profesor;
    }

    public Profesor() {
        this.cedula_Profesor = "";
        this.nombre = "";
        this.primer_apellido = "";
        this.telefono = 0;
        this.email = "";
        this.grupos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "cedula_Profesor='" + cedula_Profesor + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primer_apellido='" + primer_apellido + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", grupos=" + grupos +
                '}';
    }
}

