package DataBaseConnectionDB;

import LogicaNegocio.Alumno;
import LogicaNegocio.Ciclo;
import LogicaNegocio.Grupo;
import LogicaNegocio.Prematricula;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioPrematricula extends ConnectionDB{
    private static final String INSERTAR_PREMATRICULA = "{call insertaPrematricula(?,?,?,?)}";
    private static final String MODIFICAR_PREMATRICULA = "{call modificaPrematricula(?,?)}";
    private static final String ELIMINAR_PREMATRICULA = "{call eliminaPrematricula(?)}";
    private static final String CONSULTAR_PREMATRICULA = "{?=call buscarPrematricula(?)}";

    public ServicioPrematricula() {
    }

    public void insertarPrematricula(Prematricula prematricula) throws GlobalException, NoDataException
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

            pstmt = conexion.prepareCall(INSERTAR_PREMATRICULA);
            pstmt.setString(1,prematricula.getEstado());
            pstmt.setString(2,prematricula.getAlumno().getCedulaAlumno());
            pstmt.setInt(3,prematricula.getGrupo().getNumero_Grupo());
            pstmt.setInt(4,prematricula.getGrupo().getCiclo().getAnnio());


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

    public void modificaPrematricula(Prematricula prematricula) throws GlobalException, NoDataException  {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareStatement(MODIFICAR_PREMATRICULA);
            pstmt.setInt(1,prematricula.getIdPrematricula());
            pstmt.setString(2,prematricula.getEstado());


            int resultado = pstmt.executeUpdate();


            if (resultado == 0) {
                throw new NoDataException ("No se realizo la actualización");
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

    public void eliminarPrematricula(String id) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareStatement(ELIMINAR_PREMATRICULA);
            pstmt.setString(1,id);
            int resultado = pstmt.executeUpdate();

            //si es igual a 0 es porq no sirvio
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

    public List<Prematricula> buscarPrematricula(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("No se ha localizado el driver");
        }catch (SQLException ex){
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        List<Prematricula> prematriculas = new ArrayList<>();

        CallableStatement pstmt= null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_PREMATRICULA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2,id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next()) {

                prematriculas.add(new Prematricula(
                        rs.getInt("idPre"),
                        rs.getString("estado"),
                        new Alumno(rs.getString("alumno_cedula")),
                        new Grupo(rs.getInt("grupo_num"), new Ciclo(rs.getInt("grupo_ciclo")))
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
        return prematriculas;

    }
}
