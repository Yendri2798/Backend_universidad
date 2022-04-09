import AccesoDatos.Conector;

import java.sql.SQLException;

public class Test {
    @org.junit.jupiter.api.Test
    public void conexion() throws SQLException, ClassNotFoundException {
        Conector conector= new Conector();
        conector.conectar();
        conector.desconectar();
    }
}
