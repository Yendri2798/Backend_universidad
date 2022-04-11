package Main;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import DataBaseConnectionDB.ServicioAlumno;
import LogicaNegocio.Alumno;
import LogicaNegocio.Carrera;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrearDatos {


    public static void crearDatos(){
        crearDatosAlumno();
    }

    private static void crearDatosAlumno(){
        try{

            Carrera c = new Carrera();
            c.setCodigo_Carrera("INGE200");
            c.setNombre("Innieria en sistemas");
            c.setTitulo("Bachillerato");


            //servicio.insertarCarrera(c);

            //servicio.modificaCurso(a);
            //servicio.modificaProfesor(p);
            //ciclo.setAnnio(2022);
            // ciclo.setNumero("II Ciclo");
            //ciclo.setFecha_Inicio(date1);
            //ciclo.setFecha_Finalizacion(date2);
            //servicio.modificaCiclo(ciclo);

            //for (Curso pr : servicio.listarCurso()) {
            //System.out.println(pr.toString());
            // }

            //servicio.buscarProfesor("501520362");
            //servicio.buscarCarrera("INGE200");

            //System.out.println(servicio1.buscarCarrera("INGE200").toString());

            Alumno a = new Alumno();
            ServicioAlumno sa = new ServicioAlumno();
            a.setCedulaAlumno("702610004");
            a.setNombre("Yendri");
            a.setPrimer_apellido("Masis Brenes");
            a.setTelefono(85862025);
            a.setEmail("knnsjcsc");
            String fecha1 = "2023/03/10";
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha1);
            a.setFechaNacimiento(date1);
            a.setCarrera(c);
            sa.insertarAlumno(a);
            //sa.modificaAlumno(a);

            //for (Alumno pr : sa.listarAlumno()) {
            //System.out.println(pr.toString());
            //}
            //System.out.println(sa.buscarAlumno("702610004").toString());
            //sa.eliminarAlumno("702610004");

        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
