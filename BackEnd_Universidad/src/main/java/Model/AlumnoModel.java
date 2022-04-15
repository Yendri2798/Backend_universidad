package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioAlumno;
import LogicaNegocio.Alumno;

import java.util.List;


public class AlumnoModel {
    private static final AlumnoModel instancia = null;
    private final ServicioAlumno servicio;

    private AlumnoModel() {
        servicio = ServicioAlumno.obtenerInstancia();
    }


    public static AlumnoModel obtenerInstancia() {
        return instancia == null ? new AlumnoModel() : instancia;
    }


    public Alumno obtenerAlumno(String id) throws NoDataException, GlobalException {
        return servicio.buscarAlumno(id);
    }

    public void insertarAlumno(Alumno alumno) throws NoDataException, GlobalException {
        System.out.println(alumno);
        servicio.insertarAlumno(alumno);
    }

    public List<Alumno> listarAlumno() throws NoDataException, GlobalException {
        return servicio.listarAlumno();
    }

    public void modificar(Alumno alumno) throws NoDataException, GlobalException {
        servicio.modificaAlumno(alumno);
    }

    public void eliminarAlumno(Alumno alumno) throws NoDataException, GlobalException {
        System.out.println(alumno);
        servicio.eliminarAlumno(alumno.getCedulaAlumno());

    }

}
