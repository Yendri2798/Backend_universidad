package LogicaNegocio;



import java.util.Date;

public class Alumno {


    private String cedulaAlumno;

    private String nombre;

    private String primer_apellido;

    private int telefono;

    private String email;

    private Date fechaNacimiento;

    private Carrera carrera;

    public Alumno(String cedulaAlumno, String nombre, String apellido, int telefono,
                  String email, Date fechaNacimiento, Carrera carrera) {
        this.cedulaAlumno = cedulaAlumno;
        this.nombre = nombre;
        this.primer_apellido = apellido;
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

    public String getCedulaAlumno() {
        return cedulaAlumno;
    }

    public void setCedulaAlumno(String cedulaAlumno) {
        this.cedulaAlumno = cedulaAlumno;
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
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
