package DataBaseConnectionDB;

import LogicaNegocio.Ciclo;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ServicioCiclo extends ConnectionDB {
    private static final String INSERTAR_CICLO = "{call insertaCiclo(?,?,?,?)}";
    private static final String MODIFICAR_CICLO = "{call modificaCiclo(?,?,?,?)}";

    private static final String CONSULTAR_CICLO = "{?=call buscarCiclo(?)}";
    private static final String LISTAR_CICLO = "{?=call listarCiclo()}";
    private static final String ELIMINAR_CICLO = "{call eliminaCiclo(?, ?)}";


    public ServicioCiclo() {
    }

    public void insertarCiclo(Ciclo ciclo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        CallableStatement pstmt = null;

        try {

            pstmt = conexion.prepareCall(INSERTAR_CICLO);
            pstmt.setInt(1, ciclo.getAnnio());
            pstmt.setString(2, ciclo.getNumero());
            pstmt.setDate(3, parse(ciclo.getFecha_Incio()));
            pstmt.setDate(4, parse(ciclo.getFecha_Finalizacion()));

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

    public void modificaCiclo(Ciclo ciclo) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {

            pstmt = conexion.prepareStatement(MODIFICAR_CICLO);
            pstmt.setInt(1, ciclo.getAnnio());
            pstmt.setString(2, ciclo.getNumero());
            pstmt.setDate(3, parse(ciclo.getFecha_Incio()));
            pstmt.setDate(4, parse(ciclo.getFecha_Finalizacion()));

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

    private java.sql.Date parse(java.util.Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date);
        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
        return date1;
    }

    public Ciclo buscarCiclo(int annio) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        Ciclo ciclo = new Ciclo();

        CallableStatement pstmt = null;
        try {

            pstmt = conexion.prepareCall(CONSULTAR_CICLO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, annio);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {

                ciclo = new Ciclo(rs.getInt("annio"),
                        rs.getString("numero"),
                        rs.getDate("fecha_Incio"),
                        rs.getDate("fecha_Finalizacion"));
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
        return ciclo;

    }

    public List<Ciclo> listarCiclo() throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException ex) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        ResultSet rs = null;
        List<Ciclo> ciclos = new ArrayList<>();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(LISTAR_CICLO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ciclos.add(new Ciclo(rs.getInt("annio"), rs.getString("numero"),
                        rs.getDate("fecha_Incio"), rs.getDate("fecha_Finalizacion")));
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

        if (ciclos == null || ciclos.size() == 0) {
            throw new NoDataException("No hay datos relacionados con el Comprobante de pago");
        }
        return ciclos;
    }

    public void eliminarCiclo(int id, String numero) throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("Driver no escontrado");
        } catch (SQLException e) {
            throw new NoDataException("No se encuentra la base de datos");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINAR_CICLO);
            pstmt.setInt(1, id);
            pstmt.setString(2, numero);
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

