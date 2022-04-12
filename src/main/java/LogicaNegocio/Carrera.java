package LogicaNegocio;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
    @Getter
    @Setter
    private String codigo_Carrera;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String titulo;
    @Getter
    @Setter
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

