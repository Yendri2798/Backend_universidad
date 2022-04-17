package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioGrupo;
import LogicaNegocio.Grupo;

import java.util.List;

public class GrupoModel {
    private static final GrupoModel instancia = null;
    private final ServicioGrupo servicio;

    private GrupoModel() {
        servicio = ServicioGrupo.obtenerInstancia();
    }


    public static GrupoModel obtenerInstancia() {
        return instancia == null ? new GrupoModel() : instancia;
    }


    public Grupo obtenerGrupo(int id) throws NoDataException, GlobalException {
        return servicio.buscarGrupo(id);
    }

    public void insertarGrupo(Grupo grupo) throws NoDataException, GlobalException {
        System.out.println(grupo);
        servicio.insertarGrupo(grupo);
    }

    public List<Grupo> listarGrupo() throws NoDataException, GlobalException {
        return servicio.listarGrupo();
    }

    public void modificar(Grupo grupo) throws NoDataException, GlobalException {
        servicio.modificaGrupo(grupo);
    }

    public void eliminarGrupo(Grupo grupo) throws NoDataException, GlobalException {
        System.out.println(grupo);
        servicio.eliminarGrupo(grupo.getNumero_Grupo());

    }
}

