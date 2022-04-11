import DataBaseConnectionDB.ConnectionDB;

import java.sql.SQLException;

public class Test {
    @org.junit.jupiter.api.Test
    public void conexion() throws SQLException, ClassNotFoundException {
        ConnectionDB conector = new ConnectionDB();
        conector.conectar();
        conector.desconectar();
    }
}
