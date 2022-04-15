package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Curso;
import Model.CursoModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/curso")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class CursoController {

    private final CursoModel cursoModel = CursoModel.obtenerInstancia();

    @POST
    @Path("/insertarCurso")
    public Response insertarCurso(Curso curso) throws NoDataException, GlobalException {
        System.out.println("metodo insertar curso");
        cursoModel.insertarCurso(curso);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarCurso")
    public Response buscarCurso(Curso curso) throws NoDataException, GlobalException {
        Curso ptr = cursoModel.obtenerCurso(curso.getCodigo_Curso());
        System.out.println(curso);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }

    @DELETE
    @Path("/eliminarCurso")
    public Response eliminarCurso(Curso curso) {
        System.out.println("metodo eliminar curso");
        try {
            cursoModel.eliminarCurso(curso);
        } catch (NoDataException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (GlobalException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity("Curso eliminado").build();
    }

    @GET
    @Path("/listarCurso")
    public Response listarCurso() throws NoDataException, GlobalException {
        List<Curso> list = cursoModel.listarCurso();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarCurso")
    public Response modificarCurso(Curso curso)  {
        System.out.println("metodo modificar curso");
        try {
            cursoModel.modificarCurso(curso);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica curso").build();
    }


}

