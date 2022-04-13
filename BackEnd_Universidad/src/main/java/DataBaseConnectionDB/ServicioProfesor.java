package DataBaseConnectionDB;

import LogicaNegocio.Profesor;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioProfesor extends ConnectionDB {
    private static final String INSERTAR_PROFESOR = "{call insertaProfesor(?,?,?,?,?)}";
    private static final String MODIFICAR_PROFESOR = "{call modificaProfesor(?,?,?,?,?)}";

    private static final String CONSULTAR_PROFESOR = "{?=call buscarProfesor(?)}";
    private static final String LISTAR_PROFESOR = "{?=call listarProfesor()}";
    private static final String ELIMINAR_PROFESOR = "{call eliminaProfesor(?)}";


    public ServicioProfesor() {
    }

    public void insertarProfesor(Profesor profesor) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.prepareCall(INSERTAR_PROFESOR);
            pstmt.setString(1, profesor.getCedula_Profesor());
            pstmt.setString(2, profesor.getNombre());
            pstmt.setString(3, profesor.getPrimer_apellido());
            pstmt.setInt(4, profesor.getTelefono());
            pstmt.setString(5, profesor.getEmail());

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

    public void modificaProfesor(Profesor profesor) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(MODIFICAR_PROFESOR);
            pstmt.setString(1, profesor.getCedula_Profesor());
            pstmt.setString(2, profesor.getNombre());
            pstmt.setString(3, profesor.getPrimer_apellido());
            pstmt.setInt(4, profesor.getTelefono());
            pstmt.setString(5, profesor.getEmail());

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

    public Profesor buscarProfesor(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        Profesor profesor = new Profesor();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_PROFESOR);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                profesor = new Profesor(rs.getString("cedula_Profesor"),
                        rs.getString("nombre"), rs.getString("primer_apellido"),
                        rs.getInt("telefono"), rs.getString("email")/*, Poner array Grupos*/);
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
        return profesor;

    }

    public List<Profesor> listarProfesor() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        List<Profesor> profesores = new ArrayList<>();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_PROFESOR);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                profesores.add(new Profesor(rs.getString("cedula_Profesor"),
                        rs.getString("nombre"), rs.getString("primer_apellido"),
                        rs.getInt("telefono"), rs.getString("email")/*, Poner array Grupos*/
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

        if (profesores == null || profesores.size() == 0) {
            throw new NoDataException("No hay datos relacionados con el Comprobante de pago");
        }
        return profesores;
    }

    public void eliminarProfesor(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_PROFESOR);
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

}


