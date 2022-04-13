package DataBaseConnectionDB;

import LogicaNegocio.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioAnalisis extends ConnectionDB{
    private static final String INSERTAR_ANALISIS = "{call insertaAnalisis(?,?,?)}";
    private static final String ELIMINAR_ANALISIS= "{call eliminaAnalisis(?)}";
    private static final String CONSULTAR_ANALISIS = "{?=call buscarAnalisis(?)}";

    public ServicioAnalisis() {
    }

    public void insertarAnalisis(Analisis analisis) throws GlobalException, NoDataException
    {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_ANALISIS);
            pstmt.setString(1,analisis.getConsejero().getAlumno().getCedulaAlumno());
            pstmt.setString(2,analisis.getConsejero().getProfesor().getCedula_Profesor());
            pstmt.setInt(3,analisis.getPrematricula().getIdPrematricula());


            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException ("No se realizo la inserción");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        }
        finally {
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

    public List<Analisis> buscarAnalisis(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el driver");
        }catch (SQLException ex){
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        List<Analisis> revisiones = new ArrayList<>();

        CallableStatement pstmt= null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_ANALISIS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2,id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next()) {

                revisiones.add(new Analisis(
                        new Consejero(new Alumno(rs.getString("consejero_Alumno")),new Profesor(rs.getString("consejero_Profesor"))),
                        new Prematricula(rs.getInt("prematricula_id"))
                ));
            }
        }
        catch (SQLException ex) {
            throw new GlobalException("Sentencia no valida");
        }
        finally {
            try {
                if (rs!=null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            }
            catch(SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return revisiones;

    }

    public void eliminaAnalisis(String id) throws GlobalException, NoDataException
    {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_ANALISIS);
            pstmt.setString(1,id);
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException ("No se pudo eliminar el Comprobante de pago");
            }
            else
            {
                System.out.println("Eliminacion satisfactoria");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
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

