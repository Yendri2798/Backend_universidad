package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioCiclo;
import LogicaNegocio.Ciclo;

import java.util.List;

public class CicloModel {
    private static final CicloModel instancia = null;
    private final ServicioCiclo servicio;

    private CicloModel() {
        servicio = new ServicioCiclo();
    }


    public static CicloModel obtenerInstancia() {
        return instancia == null ? new CicloModel() : instancia;
    }


    public Ciclo obtenerCiclo(int annio) throws NoDataException, GlobalException {
        return servicio.buscarCiclo(annio);
    }

    public void insertarCiclo(Ciclo ciclo) throws NoDataException, GlobalException {
        System.out.println(ciclo);
        servicio.insertarCiclo(ciclo);
    }

    public List<Ciclo> listarCiclo() throws NoDataException, GlobalException {
        return servicio.listarCiclo();
    }

    public void modificarCiclo(Ciclo ciclo) throws NoDataException, GlobalException {
        servicio.modificaCiclo(ciclo);
    }

    public void eliminarCiclo(Ciclo ciclo) throws NoDataException, GlobalException {
        System.out.println(ciclo);
        servicio.eliminarCiclo(ciclo.getAnnio(), ciclo.getNumero());

    }
}
