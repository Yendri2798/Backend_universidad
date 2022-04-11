package Controller;

import LogicaNegocio.Login;
import Model.LoginModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sign")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    Login login;
    LoginModel model;

    @POST
    @Path("/in")
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
