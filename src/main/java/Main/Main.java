package Main;

import DataBaseConnectionDB.ConnectionDB;
import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioCiclo;
import LogicaNegocio.Ciclo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, NoDataException, GlobalException {
       try{
        //ConnectionDB conector = new ConnectionDB();
       // conector.conectar();
        //conector.desconectar();
       Ciclo ciclo = new Ciclo();
        String fecha1 = "2023/03/10";
        String fecha2 = "2023/06/12";
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha1);
        Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha2);
        //ciclo.setAnnio(2022);
        //ciclo.setNumero("I Ciclo");
        //ciclo.setFecha_Inicio(date1);
        //ciclo.setFecha_Finalizacion(date2);

       // ServicioCiclo servicio = new ServicioCiclo();
        //servicio.insertarCiclo(ciclo);

        ServicioCiclo servicio = new ServicioCiclo();
        //ciclo.setAnnio(2022);
           // ciclo.setNumero("II Ciclo");
            //ciclo.setFecha_Inicio(date1);
            //ciclo.setFecha_Finalizacion(date2);
            //servicio.modificaCiclo(ciclo);

        //for (Ciclo pr : servicio.listarCiclo()) {
            //System.out.println(pr.toString());
       // }

           //servicio.buscarCiclo(2022);
           servicio.eliminarCiclo(1,"II Ciclo");

    } catch (GlobalException | NoDataException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
