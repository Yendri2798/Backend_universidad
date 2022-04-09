package AccesoDatos;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    protected Connection conexion = null;

    public Conector() {

    }

    public void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
        if (conexion != null) {
            System.out.println("Me conecté");
        } else {
            System.out.println("Estoy mal");
        }

    }

    public void desconectar() throws SQLException {
        if (!conexion.isClosed()) {
            conexion.close();
            System.out.println("Me desconecté");
        } else {
            System.out.println("Estoy mal");
        }
    }

    private Connection getJdbcMydbsource() throws NamingException {
        Context c = new InitialContext();
        try {
            return ((DataSource) c.lookup("jdbc/Mydbsource")).getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

