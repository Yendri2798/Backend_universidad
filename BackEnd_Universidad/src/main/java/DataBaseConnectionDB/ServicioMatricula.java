package DataBaseConnectionDB;

import LogicaNegocio.Matricula;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ServicioMatricula extends ConnectionDB {
    private static final String INSERTAR_MATRICULA = "{call insertaMatricula(?,?)}";
    private static ServicioMatricula instancia = null;

    private ServicioMatricula() {
        super();
    }

    public static ServicioMatricula obtenerInstancia() {
        return instancia == null ? new ServicioMatricula() : instancia;
    }

    public void insertarMatricula(Matricula matricula) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_MATRICULA);
            pstmt.setInt(1, matricula.getPrematricula().getIdPrematricula());
            pstmt.setString(2, matricula.getAlumno().getCedulaAlumno());


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

}

