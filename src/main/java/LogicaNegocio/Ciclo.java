package LogicaNegocio;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Ciclo {
    @Getter
    @Setter
    private final Date fecha_Incio;
    @Getter
    @Setter
    private final Date fecha_Finalizacion;
    @Getter
    @Setter
    private int annio;
    @Getter
    @Setter
    private String numero;

    public Ciclo(int annio, String numero, Date fechaInicio, Date fechaFinal) {
        this.annio = annio;
        this.numero = numero;
        this.fecha_Incio = fechaInicio;
        this.fecha_Finalizacion = fechaFinal;
    }

    public Ciclo() {
        this.annio = 0;
        this.numero = "";
        this.fecha_Incio = new Date();
        this.fecha_Finalizacion = new Date();
    }

    @Override
    public String toString() {
        return "Ciclo{" +
                "annio=" + annio +
                ", numero='" + numero + '\'' +
                ", fechaInicio=" + fecha_Incio +
                ", fechaFinal=" + fecha_Finalizacion +
                '}';
    }
}

