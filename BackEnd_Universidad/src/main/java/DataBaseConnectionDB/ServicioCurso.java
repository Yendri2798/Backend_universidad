package DataBaseConnectionDB;

import LogicaNegocio.Carrera;
import LogicaNegocio.Curso;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCurso extends ConnectionDB {

    private static final String INSERTAR_CURSO = "{call insertaCurso(?,?,?,?,?)}";
    private static final String MODIFICAR_CURSO = "{call modificaCurso(?,?,?,?,?)}";

    private static final String CONSULTAR_CURSO = "{?=call buscarCurso(?)}";
    private static final String LISTAR_CURSO = "{?=call listarCurso()}";
    private static final String ELIMINAR_CURSO = "{call eliminaCurso(?)}";
    private static final String CONSULTAR_CURSO_CARRERA = "{?=call buscarCursoCarrera (?)}";

    public void insertarCurso(Curso curso) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_CURSO);
            pstmt.setString(1, curso.getCodigo_Curso());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras_semanales());
            pstmt.setString(5, curso.getCarrera().getCodigo_Carrera());

            boolean result = pstmt.execute();
            if (result == true) {
                throw new NoDataException("No se insertó");
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

    public void modificaCurso(Curso curso) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareStatement(MODIFICAR_CURSO);
            pstmt.setString(1, curso.getCodigo_Curso());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras_semanales());
            pstmt.setString(5, curso.getCarrera().getCodigo_Carrera());

            int result = pstmt.executeUpdate();

            if (result == 0) {
                throw new NoDataException("No se modificó");
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

    public Curso buscarCurso(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        Curso curso = new Curso();
        Carrera carrera = new Carrera();
        ServicioCarrera service = new ServicioCarrera();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_CURSO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = service.buscarCarrera(rs.getString("carrera_codigo"));
                curso = new Curso(
                        rs.getString("codigo_Curso"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getInt("horas_semanales"),
                        carrera
                );
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
        return curso;

    }

    public List<Curso> listarCurso() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        List<Curso> curso = new ArrayList<>();
        Carrera carrera = new Carrera();
        ServicioCarrera service = new ServicioCarrera();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_CURSO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = service.buscarCarrera(rs.getString("carrera_codigo"));
                curso.add(new Curso(
                        rs.getString("codigo_Curso"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getInt("horas_semanales"),
                        carrera
                ));
            }
        } catch (SQLException /*| ParseException*/ ex) {
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

        if (curso == null || curso.size() == 0) {
            throw new NoDataException("No hay datos relacionados con el Comprobante de pago");
        }
        return curso;
    }

    public void eliminarCurso(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_CURSO);
            pstmt.setString(1, id);
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se pudo eliminar el Comprobante de pago");
            } else {
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

    public List<Curso> buscarCarreraCurso(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        List<Curso> cursos = new ArrayList<>();
        Curso curso = new Curso();
        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_CURSO_CARRERA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso.setCodigo_Curso(rs.getString("codigo_Curso"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCreditos(rs.getInt("creditos"));
                curso.setHoras_semanales(rs.getInt("horas_semanales"));
                cursos.add(curso);
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
        return cursos;

    }

}
