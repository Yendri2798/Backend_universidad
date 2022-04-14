package Controller;

import LogicaNegocio.Alumno;
import LogicaNegocio.Login;
import Model.AlumnoModel;
import Model.LoginModel;
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


    private final LoginModel loginModell =LoginModel.obtenerInstancia();
    private final AlumnoModel alumnoModel=AlumnoModel.obtenerInstancia();

    @POST
    @Path("/verify")
    public Response verificarUsuario(Login user) {
        try {
            boolean a = loginModell.
                    verificarLogin(user.getCedula(), user.getContrasena());

            if (!a) {
                throw new Exception("Denegado");
            }

      return Response.ok(a).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/obtenerTipoUsuario")
    public Response obtenerTipoUsuario(Login user){
        try {

            String ptr = loginModell.obtenerRolLogin(user.getCedula());


            return Response.ok(ptr).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/obtenerUsuario")
    public Response obtenerUsuario(Login user){
        try {

          Alumno alumno = alumnoModel.obtenerAlumno(user.getCedula());
            return Response.ok(alumno).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
    }



}

