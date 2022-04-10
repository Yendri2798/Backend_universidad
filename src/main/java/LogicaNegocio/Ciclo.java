package LogicaNegocio;


import java.util.Date;

public class Ciclo {
    private int annio;
    private String numero;
    private Date fecha_Incio;
    private Date fecha_Finalizacion;

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

    public int getAnnio() {
        return annio;
    }

    public void setAnnio(int annio) {
        this.annio = annio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha_Incio() {
        return fecha_Incio;
    }

    public void setFecha_Incio(Date fecha_Incio) {
        this.fecha_Incio = fecha_Incio;
    }

    public Date getFecha_Finalizacion() {
        return fecha_Finalizacion;
    }

    public void setFecha_Finalizacion(Date fecha_Finalizacion) {
        this.fecha_Finalizacion = fecha_Finalizacion;
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

