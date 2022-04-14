package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioAlumno;
import LogicaNegocio.Alumno;
import LogicaNegocio.Login;

public class AlumnoModel {
    private static final AlumnoModel instancia = null;
    private final ServicioAlumno servicio;

    private AlumnoModel(){
        servicio = new ServicioAlumno();
    }


    public static AlumnoModel obtenerInstancia() {
        return instancia == null ? new AlumnoModel() : instancia;
    }


    public Alumno obtenerAlumno(String id) throws NoDataException, GlobalException {
        return servicio.buscarAlumno(id);
    }

}
