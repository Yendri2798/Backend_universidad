package LogicaNegocio;


import java.util.Date;

public class Alumno {
    private String cedula_Alumno;
    private String nombre;
    private String primer_apellido;
    private int telefono;
    private String email;
    private Date fecha_Nacimiento;
    private Carrera carrera;

    public Alumno(String cedulaAlumno, String nombre, String apellido, int telefono,
                  String email, Date fechaNacimiento, Carrera carrera) {
        this.cedula_Alumno = cedulaAlumno;
        this.nombre = nombre;
        this.primer_apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fecha_Nacimiento = fechaNacimiento;
        this.carrera = carrera;
    }

    public Alumno() {
        this.cedula_Alumno = "";
        this.nombre = "";
        this.primer_apellido = "";
        this.telefono = 0;
        this.email = "";
        this.fecha_Nacimiento = new Date();
        this.carrera = new Carrera();
    }

    public String getCedulaAlumno() {
        return cedula_Alumno;
    }

    public void setCedulaAlumno(String cedulaAlumno) {
        this.cedula_Alumno = cedulaAlumno;
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

    public Date getFechaNacimiento() {
        return fecha_Nacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fecha_Nacimiento = fechaNacimiento;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "cedulaAlumno='" + cedula_Alumno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + primer_apellido + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fecha_Nacimiento +
                ", carrera=" + carrera +
                '}';
    }
}
