package LogicaNegocio;


public class Curso {
    private String codigo_Curso;
    private String nombre;
    private int creditos;
    private int horas_semanales;
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

    public String getCodigo_Curso() {
        return codigo_Curso;
    }

    public void setCodigo_Curso(String codigo_Curso) {
        this.codigo_Curso = codigo_Curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras_semanales() {
        return horas_semanales;
    }

    public void setHoras_semanales(int horas_semanales) {
        this.horas_semanales = horas_semanales;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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

