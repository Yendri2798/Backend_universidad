package LogicaNegocio;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String cedula_Profesor;
    private String nombre;
    private String primer_apellido;
    private int telefono;
    private String email;
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

    public Profesor() {
        this.cedula_Profesor = "";
        this.nombre = "";
        this.primer_apellido = "";
        this.telefono = 0;
        this.email = "";
        this.grupos = new ArrayList<>();
    }

    public String getCedula_Profesor() {
        return cedula_Profesor;
    }

    public void setCedula_Profesor(String cedula_Profesor) {
        this.cedula_Profesor = cedula_Profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
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

