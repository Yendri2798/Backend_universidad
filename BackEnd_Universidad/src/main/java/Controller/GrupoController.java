package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Grupo;
import Model.GrupoModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/grupo")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class GrupoController {

    private final GrupoModel grupoModel = GrupoModel.obtenerInstancia();

    @POST
    @Path("/insertarGrupo")
    public Response insertarGrupo(Grupo grupo) throws NoDataException, GlobalException {
        System.out.println("metodo insertar grupo");
        grupoModel.insertarGrupo(grupo);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarGrupo")
    public Response buscarGrupo(Grupo grupo) throws NoDataException, GlobalException {
        Grupo ptr = grupoModel.obtenerGrupo(grupo.getNumero_Grupo());
        System.out.println(grupo);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }

    @DELETE
    @Path("/eliminarGrupo")
    public Response eliminarGrupo(Grupo grupo) {
        System.out.println("metodo eliminar grupo");
        try {
            grupoModel.eliminarGrupo(grupo);
        } catch (NoDataException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (GlobalException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity("Grupo eliminado").build();
    }

    @GET
    @Path("/listarGrupo")
    public Response listarGrupo() throws NoDataException, GlobalException {
        List<Grupo> list = grupoModel.listarGrupo();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarGrupo")
    public Response modificarGrupo(Grupo grupo)  {
        System.out.println("metodo modificar grupo");
        try {
            grupoModel.modificar(grupo);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica grupo").build();
    }


}

