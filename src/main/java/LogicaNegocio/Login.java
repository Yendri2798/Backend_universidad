package LogicaNegocio;

import lombok.Getter;
import lombok.Setter;

public class Login {
    @Getter
    @Setter
    String cedula;
    @Getter
    @Setter
    String contrasena;
    @Getter
    @Setter
    String rol;

    public Login() {
        this("", "", "");
    }

    public Login(String cedula, String contrasena, String rol) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.rol = rol;
    }


    @Override
    public String toString() {
        return "Login " +
                "cedula='" + cedula + ' ' +
                ", contraseña='" + contrasena + ' ' +
                ", rol='" + rol + '\'';
    }
}
