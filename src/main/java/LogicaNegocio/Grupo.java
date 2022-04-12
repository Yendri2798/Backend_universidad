package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Grupo {
    @Getter
    @Setter
    private int numero_Grupo;
    @Getter
    @Setter
    private String horario;
    @Getter
    @Setter
    private Ciclo ciclo;
    @Getter
    @Setter
    private Curso curso;
    @Getter
    @Setter
    private Profesor profesor;
    @Getter
    @Setter
    private int campos_Restantes;
    @Getter
    @Setter
    private int capacidad_Maxima;

    public Grupo(int numero_Grupo, String horario, Ciclo ciclo, Curso curso, Profesor profesor, int campos_Restantes, int capacidad_Maxima) {
        this.numero_Grupo = numero_Grupo;
        this.horario = horario;
        this.ciclo = ciclo;
        this.curso = curso;
        this.profesor = profesor;
        this.campos_Restantes = campos_Restantes;
        this.capacidad_Maxima = capacidad_Maxima;
    }

    public Grupo() {
        this.numero_Grupo = 0;
        this.horario = "";
        this.ciclo = new Ciclo();
        this.curso = new Curso();
        this.profesor = new Profesor();
        this.campos_Restantes = 0;
        this.capacidad_Maxima = 0;

    }

    @Override
    public String toString() {
        return "Grupo{" +
                "num_Grupo=" + numero_Grupo +
                ", horario='" + horario + '\'' +
                ", camposRestantes=" + campos_Restantes +
                ", capacidadMaxima=" + capacidad_Maxima +
                ", ciclo=" + ciclo +
                ", curso=" + curso +
                ", profesor=" + profesor +
                '}';
    }
}

