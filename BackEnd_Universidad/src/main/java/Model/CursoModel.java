package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioCurso;
import LogicaNegocio.Curso;

import java.util.List;

public class CursoModel {
    private static final CursoModel instancia = null;
    private final ServicioCurso servicio;

    private CursoModel() {
        servicio = new ServicioCurso();
    }


    public static CursoModel obtenerInstancia() {
        return instancia == null ? new CursoModel() : instancia;
    }


    public Curso obtenerCurso(String id) throws NoDataException, GlobalException {
        return servicio.buscarCurso(id);
    }

    public void insertarCurso(Curso curso) throws NoDataException, GlobalException {
        System.out.println(curso);
        servicio.insertarCurso(curso);
    }

    public List<Curso> listarCurso() throws NoDataException, GlobalException {
        return servicio.listarCurso();
    }

    public void modificarCurso(Curso curso) throws NoDataException, GlobalException {
        servicio.modificaCurso(curso);
    }

    public void eliminarCurso(Curso curso) throws NoDataException, GlobalException {
        System.out.println(curso);
        servicio.eliminarCurso(curso.getCodigo_Curso());
    }
}

