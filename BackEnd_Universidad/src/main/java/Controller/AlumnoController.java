package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Alumno;
import LogicaNegocio.Login;
import Model.AlumnoModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/alumno")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnoController {

    private final AlumnoModel alumnoModel = AlumnoModel.obtenerInstancia();

    @POST
    @Path("/insertarAlumno")
    public Response insertarAlumno(Alumno alumno) throws NoDataException, GlobalException {
        System.out.println("metodo insertar alumno");
        alumnoModel.insertarAlumno(alumno);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarAlumno")
    public Response buscarAlumno(Login user) throws NoDataException, GlobalException {

        Alumno ptr = alumnoModel.obtenerAlumno(user.getCedula());

        System.out.println(user);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }

    @DELETE
    @Path("/eliminarAlumno")
    public Response eliminarAlumno(Alumno alumno) {
        System.out.println("metodo eliminar alumno");
        try {
            alumnoModel.eliminarAlumno(alumno);
        } catch (NoDataException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (GlobalException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity("Usuario elimnado").build();
    }

    @GET
    @Path("/listarAlumno")
    public Response listarAlumno() throws NoDataException, GlobalException {
        List<Alumno> list = alumnoModel.listarAlumno();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarAlumno")
    public Response modificarAlumno(Alumno alumno) {
        System.out.println("metodo modificar alumno");
        try {
            alumnoModel.modificar(alumno);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica alumno").build();
    }

}
