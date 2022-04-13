package Controller;

import LogicaNegocio.Login;
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

    Login login;
    LoginModel model;

    @POST
    @Path("/verify")
    public Response verificarUsuario(Login user) {
        try {
            boolean a = model.
                    verificarLogin(user.getCedula(), user.getContrasena());

            if (!a) {
                throw new Exception("Denegado");
            }
            Login u = model.obtenerLogin(user.getCedula());
            Login ptr = new Login(u.getCedula(), u.getContrasena(), u.getRol());
            return Response.ok(ptr).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage()).build();
        }
    }

}

