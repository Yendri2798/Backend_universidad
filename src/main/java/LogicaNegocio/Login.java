package LogicaNegocio;

public class Login {
    String cedula, contrasena, rol;

    public Login() {
        this("", "", "");
    }

    public Login(String cedula, String contrasena, String rol) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
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
