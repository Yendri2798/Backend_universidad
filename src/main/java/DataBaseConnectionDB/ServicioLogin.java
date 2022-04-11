package DataBaseConnectionDB;

import LogicaNegocio.Login;
import oracle.jdbc.internal.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioLogin extends ConnectionDB {

    private static final String obtenerLogin = "{?=call obtenerLogin(?)}";

    public Login obtenerLogin(String id) throws GlobalException, NoDataException {


        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;

        Login ptrLogin = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(obtenerLogin);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ptrLogin = new Login(rs.getString("cedula"),
                        rs.getString("contraseña"),
                        rs.getString("rol"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (ptrLogin == null) {
            throw new NoDataException("No hay datos");
        }
        return ptrLogin;
    }

    public boolean verificarLogin(String id, String pwd) throws NoDataException, GlobalException {
        Login ptr = this.obtenerLogin(id);
        return ptr.getCedula() == id && ptr.getContrasena() == pwd;
    }

    public String obtenerRolLogin(String id) throws NoDataException, GlobalException {
        Login ptr = this.obtenerLogin(id);
        return this.obtenerLogin(id).getRol();
    }

}
