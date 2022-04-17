package DataBaseConnectionDB;

import LogicaNegocio.Ciclo;
import LogicaNegocio.Curso;
import LogicaNegocio.Grupo;
import LogicaNegocio.Profesor;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioGrupo extends ConnectionDB {
    private static final String INSERTAR_GRUPO = "{call insertaGrupo(?,?,?,?,?,?,?,?)}";
    private static final String MODIFICAR_GRUPO = "{call modificaGrupo(?,?,?,?,?,?,?,?)}";

    private static final String CONSULTAR_GRUPO = "{?=call buscarGrupo(?)}";
    private static final String LISTAR_GRUPO = "{?=call listarGrupo()}";
    private static final String ELIMINAR_GRUPO = "{call eliminaGrupo(?)}";

    private static final String MODIFICA_CAMPOS_RESTANTES = "{call modificaGrupoCamposRestantes(?)}";

    private static ServicioGrupo instancia = null;

    private ServicioGrupo() {
        super();
    }

    public static ServicioGrupo obtenerInstancia() {
        return instancia == null ? new ServicioGrupo() : instancia;
    }

    public void insertarGrupo(Grupo grupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_GRUPO);
            pstmt.setInt(1, grupo.getNumero_Grupo());
            pstmt.setString(2, grupo.getHorario());
            pstmt.setInt(3, grupo.getCampos_Restantes());
            pstmt.setInt(4, grupo.getCapacidad_Maxima());
            pstmt.setInt(5, grupo.getCiclo().getAnnio());
            pstmt.setString(6, grupo.getCurso().getCodigo_Curso());
            pstmt.setString(7, grupo.getProfesor().getCedula_Profesor());
            pstmt.setString(8, grupo.getCiclo().getNumero());

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

    public void modificaGrupo(Grupo grupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(MODIFICAR_GRUPO);
            pstmt.setInt(1, grupo.getNumero_Grupo());
            pstmt.setString(2, grupo.getHorario());
            pstmt.setInt(3, grupo.getCampos_Restantes());
            pstmt.setInt(4, grupo.getCapacidad_Maxima());
            pstmt.setInt(5, grupo.getCiclo().getAnnio());
            pstmt.setString(6, grupo.getCurso().getCodigo_Curso());
            pstmt.setString(7, grupo.getProfesor().getCedula_Profesor());
            pstmt.setString(8, grupo.getCiclo().getNumero());

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

    public Grupo buscarGrupo(int id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        Grupo grupo = new Grupo();
        Profesor profe = new Profesor();
        Ciclo ciclo = new Ciclo();
        Curso curso = new Curso();

        ServicioCurso servicioCurso = ServicioCurso.obtenerInstancia();
        ServicioProfesor servicioProfesor = ServicioProfesor.obtenerInstancia();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_GRUPO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                grupo.setNumero_Grupo(rs.getInt("numero_Grupo"));
                grupo.setHorario(rs.getString("horario"));
                grupo.setCampos_Restantes(rs.getInt("campos_Restantes"));
                grupo.setCapacidad_Maxima(rs.getInt("capacidad_Maxima"));
                ciclo.setAnnio(rs.getInt("ciclo_annio"));
                ciclo.setNumero(rs.getString("numero_ciclo"));
                grupo.setCiclo(ciclo);
                curso = servicioCurso.buscarCurso(rs.getString("curso_codigo"));
                profe = servicioProfesor.buscarProfesor(rs.getString("profesor_cedula"));
                grupo.setProfesor(profe);
                grupo.setCurso(curso);
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
        return grupo;

    }

    public List<Grupo> listarGrupo() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        List<Grupo> grupos = new ArrayList<>();
        Grupo grupo = new Grupo();
        Profesor profesor = new Profesor();
        Ciclo ciclo = new Ciclo();
        Curso curso = new Curso();

        ServicioCurso serviceCurso = ServicioCurso.obtenerInstancia();
        ServicioProfesor serviceProfe = ServicioProfesor.obtenerInstancia();
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(LISTAR_GRUPO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ciclo.setAnnio(rs.getInt("ciclo_annio"));
                ciclo.setNumero(rs.getString("numero_ciclo"));
                grupo.setCiclo(ciclo);
                curso = serviceCurso.buscarCurso(rs.getString("curso_codigo"));
                profesor = serviceProfe.buscarProfesor(rs.getString("profesor_cedula"));
                grupos.add(new Grupo(
                        rs.getInt("numero_Grupo"),
                        rs.getString("horario"),
                        ciclo, curso, profesor,
                        rs.getInt("campos_Restantes"),
                        rs.getInt("capacidad_Maxima")
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

        if (grupos == null || grupos.size() == 0) {
            throw new NoDataException("No hay datos relacionados con el Comprobante de pago");
        }
        return grupos;
    }

    public void eliminarGrupo(int id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_GRUPO);
            pstmt.setInt(1, id);
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

    public void modificaCamposGrupo(Grupo grupo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareStatement(MODIFICA_CAMPOS_RESTANTES);
            pstmt.setInt(1, grupo.getNumero_Grupo());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
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


