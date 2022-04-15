package DataBaseConnectionDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    //private static ConnectionDB instancia = null;
    protected Connection conexion = null;

    protected ConnectionDB() {

    }

    /*
        public static ConnectionDB getInstancia() {
            if (instancia == null) {
                instancia = new ConnectionDB();
            }
            return instancia;
        }
    */
    public void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
        if (conexion != null) {
            System.out.println("Conexión a la base de datos Oracle, EXITOSA");
        } else {
            System.err.println("Conexión a la base de datos Oracle, FALLIDA");
        }
    }

    public void desconectar() throws SQLException {
        if (!conexion.isClosed()) {
            conexion.close();
            System.out.println("Desconexion de la base de datos Oracle, EXITOSA");
        } else {
            System.err.println("Desconexion de la base de datos Oracle, EXITOSA");
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

