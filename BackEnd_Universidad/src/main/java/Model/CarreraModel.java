package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioCarrera;
import LogicaNegocio.Carrera;

import java.util.List;

public class CarreraModel {
    private static final CarreraModel instancia = null;
    private final ServicioCarrera servicio;

    private CarreraModel() {
        servicio =  ServicioCarrera.obtenerInstancia();
    }


    public static CarreraModel obtenerInstancia() {
        return instancia == null ? new CarreraModel() : instancia;
    }


    public Carrera obtenerCarrera(String id) throws NoDataException, GlobalException {
        return servicio.buscarCarrera(id);
    }

    public void insertarCarrera(Carrera carrera) throws NoDataException, GlobalException {
        System.out.println(carrera);
        servicio.insertarCarrera(carrera);
    }

    public List<Carrera> listarCarrera() throws NoDataException, GlobalException {
        return servicio.listarCarrera();
    }

    public void modificar(Carrera carrera) throws NoDataException, GlobalException {
        servicio.modificaCarrera(carrera);
    }

    public void eliminarCarrera(Carrera carrera) throws NoDataException, GlobalException {
        System.out.println(carrera);
        servicio.eliminarCarrera(carrera.getCodigo_Carrera());

    }
}
