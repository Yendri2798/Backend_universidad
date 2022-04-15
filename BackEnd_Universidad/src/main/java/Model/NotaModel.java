package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioNota;
import LogicaNegocio.Nota;

import java.util.List;

public class NotaModel {
    private static final NotaModel instancia = null;
    private final ServicioNota servicio;

    private NotaModel() {
        servicio =  ServicioNota.obtenerInstancia();
    }


    public static NotaModel obtenerInstancia() {
        return instancia == null ? new NotaModel() : instancia;
    }


    public List<Nota> obtenerNota(String id) throws NoDataException, GlobalException {
        return servicio.buscarNotasAlumno(id);
    }

    public void insertarNota(Nota nota) throws NoDataException, GlobalException {
        System.out.println(nota);
        servicio.insertarNota(nota);
    }

    public void modificar(Nota nota) throws NoDataException, GlobalException {
        servicio.modificaNota(nota);
    }

}
