package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Analisis {
    @Getter
    @Setter
    private Consejero consejero;
    @Getter
    @Setter
    private Prematricula prematricula;

    public Analisis(Consejero consejero, Prematricula prematricula) {
        this.consejero = consejero;
        this.prematricula = prematricula;
    }

    public Analisis() {
        this.consejero = new Consejero();
        this.prematricula = new Prematricula();
    }

    @Override
    public String toString() {
        return "Analisis{" +
                "consejero=" + consejero +
                ", prematricula=" + prematricula +
                '}';
    }
}
