package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Alumno;
import LogicaNegocio.Login;
import LogicaNegocio.Profesor;
import Model.AlumnoModel;
import Model.LoginModel;
import Model.ProfesorModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/login")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {


    private final LoginModel loginModel = LoginModel.obtenerInstancia();
    private final AlumnoModel alumnoModel = AlumnoModel.obtenerInstancia();
    private final ProfesorModel profesorModel = ProfesorModel.obtenerInstancia();

    public boolean verificarUsuario(Login user) throws NoDataException, GlobalException {

            boolean a = loginModel.
                    verificarLogin(user.getCedula(), user.getContrasena());

            return a;

    }

    @POST
    @Path("/obtenerUsuarioEstudiante")
    public Response obtenerUsuarioEstudiante(Login user) {

        try {
            verificarUsuario(user);

        } catch (NoDataException | GlobalException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
        try {

            Alumno alumno = alumnoModel.obtenerAlumno(user.getCedula());
            return Response.ok(alumno).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/obtenerUsuarioProfesor")
    public Response obtenerUsuarioProfesor(Login user) {

        try {
            verificarUsuario(user);

        } catch (NoDataException | GlobalException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
        try {

            Profesor profesor = profesorModel.obtenerProfesor(user.getCedula());
            return Response.ok(profesor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
    }


}

