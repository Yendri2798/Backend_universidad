package DataBaseConnectionDB;

import LogicaNegocio.Alumno;
import LogicaNegocio.Consejero;
import LogicaNegocio.Profesor;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioConsejero extends ConnectionDB {
    private static final String INSERTAR_CONSEJERO = "{call insertaConsejero(?,?)}";
    private static final String CONSULTAR_CON_ALUMNO = "{?=call buscarConsejeroAlumno(?)}";
    private static final String CONSULTAR_CON_PROFE = "{?=call buscarConsejeroProfe(?)}";

    public ServicioConsejero() {
    }

    public void insertarConsejero(Consejero consejero) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_CONSEJERO);
            pstmt.setString(1, consejero.getAlumno().getCedulaAlumno());
            pstmt.setString(2, consejero.getProfesor().getCedula_Profesor());


            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserción");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public Consejero buscarConsejeroAlumno(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException ex) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        Consejero consejero = new Consejero();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_CON_ALUMNO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                consejero = new Consejero(new Alumno(rs.getString("alumno_Cedula"))
                        , new Profesor(rs.getString("profesor_cedula")));
            }
        } catch (SQLException ex) {
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
        return consejero;

    }

    public List<Consejero> buscarConsejeroProfe(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException ex) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        List<Consejero> consejeros = new ArrayList<>();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_CON_PROFE);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                consejeros.add(new Consejero(new Alumno(rs.getString("alumno_Cedula"))
                        , new Profesor(rs.getString("profesor_cedula"))));
            }
        } catch (SQLException ex) {
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
        return consejeros;

    }
}

