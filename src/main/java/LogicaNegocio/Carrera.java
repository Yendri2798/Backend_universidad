package LogicaNegocio;


import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String codigo_Carrera;
    private String nombre;
    private String titulo;
    private List<Curso> cursos;

    public Carrera(String codigo, String nombre, String titulo, List<Curso> cursos) {
        this.codigo_Carrera = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = cursos;
    }

    public Carrera() {
        this.codigo_Carrera = "";
        this.nombre = "";
        this.titulo = "";
        this.cursos = new ArrayList<>();
    }

    public String getCodigo_Carrera() {
        return codigo_Carrera;
    }

    public void setCodigo_Carrera(String codigo_Carrera) {
        this.codigo_Carrera = codigo_Carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "codigo='" + codigo_Carrera + '\'' +
                ", nombre='" + nombre + '\'' +
                ", titulo='" + titulo + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}

