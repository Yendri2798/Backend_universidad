package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Profesor;
import Model.ProfesorModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/profesor")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesorController {

    private final ProfesorModel profesorModel = ProfesorModel.obtenerInstancia();

    @POST
    @Path("/insertarProfesor")
    public Response insertarProfesor(Profesor profesor) throws NoDataException, GlobalException {
        System.out.println("metodo insertar profesor");
        profesorModel.insertarProfesor(profesor);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarProfesor")
    public Response buscarProfesor(Profesor profesor) throws NoDataException, GlobalException {
        Profesor ptr = profesorModel.obtenerProfesor(profesor.getCedula_Profesor());
        System.out.println(profesor);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }

    @DELETE
    @Path("/eliminarProfesor")
    public Response eliminarProfesor(Profesor profesor) {
        System.out.println("metodo eliminar profesor");
        try {
            profesorModel.eliminarProfesor(profesor);
        } catch (NoDataException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (GlobalException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity("Profesor eliminado").build();
    }

    @GET
    @Path("/listarProfesor")
    public Response listarProfesor() throws NoDataException, GlobalException {
        List<Profesor> list = profesorModel.listarProfesor();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarProfesor")
    public Response modificarProfesor(Profesor profesor)  {
        System.out.println("metodo modificar profesor");
        try {
            profesorModel.modificarProfesor(profesor);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica profesor").build();
    }


}
