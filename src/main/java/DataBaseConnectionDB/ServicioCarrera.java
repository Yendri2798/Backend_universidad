package DataBaseConnectionDB;

import LogicaNegocio.Carrera;
import LogicaNegocio.Ciclo;
import LogicaNegocio.Curso;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCarrera extends ConnectionDB{

    private final String INSERTAR_CARRERA = "{call insertaCarrera(?,?,?)}";
    private final String MODIFICAR_CARRERA = "{call modificaCarrera(?,?,?)}";

    private final String CONSULTAR_CARRERA = "{?=call buscarCarrera(?)}";
    private final String LISTAR_CARRERA = "{?=call listarCarrera()}";
    private final String ELIMINAR_CARRERA = "{call eliminaCarrera(?)}";

    public ServicioCarrera(){

    }

    public void insertarCarrera(Carrera carrera) throws GlobalException, NoDataException
    {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_CARRERA);
            pstmt.setString(1,carrera.getCodigo_Carrera());
            pstmt.setString(2,carrera.getNombre());
            pstmt.setString(3,carrera.getTitulo());

            boolean result = pstmt.execute();
            if (result == true) {
                throw new NoDataException ("No se insertó");
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

    public void modificaCarrera(Carrera carrera) throws GlobalException, NoDataException  {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareStatement(MODIFICAR_CARRERA);
            pstmt.setString(1,carrera.getCodigo_Carrera());
            pstmt.setString(2,carrera.getNombre());
            pstmt.setString(3,carrera.getTitulo());

            int result = pstmt.executeUpdate();

            if (result == 0) {
                throw new NoDataException ("No se modificó");
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

    public Carrera buscarCarrera(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("Driver no escontrado");
        }catch (SQLException ex){
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        Carrera carrera = new Carrera();

        ServicioCurso servicio = new ServicioCurso();
        List<Curso> cursos = new ArrayList<>();
        cursos = servicio.buscarCarreraCurso(id);
        CallableStatement pstmt= null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_CARRERA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2,id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next()) {

                carrera = new Carrera(rs.getString("codigo_Carrera"),rs.getString("nombre")
                        ,rs.getString("titulo"), cursos);
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
        return carrera;

    }

    public List<Carrera> listarCarrera() throws GlobalException, NoDataException {
        try {
            conectar();
        }catch (ClassNotFoundException ex){
            throw new GlobalException("Driver no escontrado");
        }catch (SQLException ex){
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        List<Carrera> carreras = new ArrayList<>();
        ServicioCurso service = new ServicioCurso();
        CallableStatement pstmt= null;
        try {
            pstmt = conexion.prepareCall(LISTAR_CARRERA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next()) {


                carreras.add(new Carrera(rs.getString("codigo_Carrera"),rs.getString("nombre"),
                        rs.getString("titulo"), service.buscarCarreraCurso(rs.getString("codigo_Curso"))));
            }
        }
        catch (SQLException /*| ParseException*/ ex) {
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

        if (carreras == null || carreras.size()==0) {
            throw new NoDataException("No hay datos relacionados con el Comprobante de pago");
        }
        return carreras;
    }

    public void eliminarCarrera(String id) throws GlobalException, NoDataException
    {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_CARRERA);
            pstmt.setString(1,id);
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException ("No se pudo eliminar el Comprobante de pago");
            }
            else
            {
                System.out.println("Se ha eliminado exitosamente");
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

