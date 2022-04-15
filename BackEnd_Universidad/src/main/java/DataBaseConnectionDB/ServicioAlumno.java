package DataBaseConnectionDB;

import LogicaNegocio.Alumno;
import LogicaNegocio.Carrera;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ServicioAlumno extends ConnectionDB {

    private static final String INSERTAR_ALUMNO = "{call insertaAlumno(?,?,?,?,?,?,?)}";
    private static final String MODIFICAR_ALUMNO = "{call modificaAlumno(?,?,?,?,?,?,?)}";
    private static final String CONSULTAR_ALUMNO = "{?=call buscarAlumno(?)}";
    private static final String LISTAR_ALUMNO = "{?=call listarAlumno()}";
    private static final String ELIMINAR_ALUMNO = "{call eliminaAlumno(?)}";
    private static ServicioAlumno instancia = null;


    private ServicioAlumno() {
        super();
    }

    public static ServicioAlumno obtenerInstancia() {
        return instancia == null ? new ServicioAlumno() : instancia;
    }


    public void insertarAlumno(Alumno alumno) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_ALUMNO);
            pstmt.setString(1, alumno.getCedulaAlumno());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getPrimer_apellido());
            pstmt.setInt(4, alumno.getTelefono());
            pstmt.setString(5, alumno.getEmail());
            pstmt.setDate(6, parse(alumno.getFechaNacimiento()));
            pstmt.setString(7, alumno.getCarrera().getCodigo_Carrera());

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

    public void modificaAlumno(Alumno alumno) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareStatement(MODIFICAR_ALUMNO);
            pstmt.setString(1, alumno.getCedulaAlumno());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getPrimer_apellido());
            pstmt.setInt(4, alumno.getTelefono());
            pstmt.setString(5, alumno.getEmail());
            pstmt.setDate(6, parse(alumno.getFechaNacimiento()));
            pstmt.setString(7, alumno.getCarrera().getCodigo_Carrera());

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

    public Alumno buscarAlumno(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        Alumno alumno = new Alumno();
        Carrera carrera = new Carrera();
        ServicioCarrera service =  ServicioCarrera.obtenerInstancia();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_ALUMNO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                carrera = service.buscarCarrera(rs.getString("carrera_codigo"));
                alumno = new Alumno(
                        rs.getString("cedula_Alumno"),
                        rs.getString("nombre"),
                        rs.getString("primer_apellido"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_Nacimiento"),
                        carrera
                );
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
        return alumno;

    }

    public List<Alumno> listarAlumno() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;

        List<Alumno> alumnos = new ArrayList<>();
        Carrera carrera = new Carrera();
        ServicioCarrera servicioCarrera =  ServicioCarrera.obtenerInstancia();

        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_ALUMNO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                carrera = servicioCarrera.buscarCarrera(rs.getString("carrera_codigo"));
                alumnos.add(new Alumno(rs.getString("cedula_Alumno"),
                        rs.getString("nombre"),
                        rs.getString("primer_apellido"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_Nacimiento"),
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

        if (alumnos == null || alumnos.size() == 0) {
            throw new NoDataException("No hay datos relacionados con el Comprobante de pago");
        }
        return alumnos;
    }

    public void eliminarAlumno(String id) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_ALUMNO);
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

    private java.sql.Date parse(java.util.Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date);
        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
        return date1;
    }
}