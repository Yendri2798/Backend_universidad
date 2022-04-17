package Model;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioLogin;
import LogicaNegocio.Login;

public class LoginModel {
    private static final LoginModel instancia = null;
    private final ServicioLogin servicio;

    private LoginModel() {
        servicio = ServicioLogin.obtenerInstancia();
    }

    public static LoginModel obtenerInstancia() {
        return instancia == null ? new LoginModel() : instancia;
    }

    public boolean verificarLogin(String id, String pwd) throws NoDataException, GlobalException {
        return servicio.verificarLogin(id, pwd);
    }

    public String obtenerRolLogin(String id) throws NoDataException, GlobalException {
        return servicio.obtenerRolLogin(id);
    }

    public Login obtenerLogin(String id) throws NoDataException, GlobalException {
        return servicio.obtenerLogin(id);
    }

}
