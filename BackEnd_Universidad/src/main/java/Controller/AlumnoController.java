package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Alumno;
import LogicaNegocio.Login;
import Model.AlumnoModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alumno")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnoController {

    private static final AlumnoModel alumnoModel = AlumnoModel.obtenerInstancia();

    @POST
    @Path("/insertar")
    public Response insertarAlumno(Alumno alumno) throws NoDataException, GlobalException {
        alumnoModel.insertarAlumno(alumno);
        return Response.ok("Alumno agregado").build();
    }

    @POST
    @Path("/buscarAlumno")
    public Response buscarAlumno(Login user) throws NoDataException, GlobalException {

        Alumno ptr = alumnoModel.obtenerAlumno(user.getCedula());

        System.out.println(user);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }




    }
