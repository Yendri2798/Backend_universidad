package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioProfesor;
import LogicaNegocio.Profesor;

import java.util.List;

public class ProfesorModel {
    private static final ProfesorModel instancia = null;
    private final ServicioProfesor servicio;

    private ProfesorModel() {
        servicio = ServicioProfesor.obtenerInstancia();
    }


    public static ProfesorModel obtenerInstancia() {
        return instancia == null ? new ProfesorModel() : instancia;
    }


    public Profesor obtenerProfesor(String id) throws NoDataException, GlobalException {
        return servicio.buscarProfesor(id);
    }

    public void insertarProfesor(Profesor profesor) throws NoDataException, GlobalException {
        System.out.println(profesor);
        servicio.insertarProfesor(profesor);
    }

    public List<Profesor> listarProfesor() throws NoDataException, GlobalException {
        return servicio.listarProfesor();
    }

    public void modificarProfesor(Profesor profesor) throws NoDataException, GlobalException {
        servicio.modificaProfesor(profesor);
    }

    public void eliminarProfesor(Profesor profesor) throws NoDataException, GlobalException {
        System.out.println(profesor);
        servicio.eliminarProfesor(profesor.getCedula_Profesor());

    }
}

