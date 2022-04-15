package Main;

import DataBaseConnectionDB.*;
import LogicaNegocio.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, NoDataException, GlobalException {
        try {
            //ConnectionDB conector = new ConnectionDB();
            // conector.conectar();
            //conector.desconectar();
            Ciclo ciclo = new Ciclo();
            Carrera c = new Carrera();
            c.setCodigo_Carrera("EIF");
            c.setNombre("INFO");
            c.setTitulo("tit");
            ServicioCarrera sc =  ServicioCarrera.obtenerInstancia();
            //sc.insertarCarrera(c);


            Alumno a = new Alumno();
            a.setCedulaAlumno("7026102525");
            a.setNombre("Yendri");
            a.setPrimer_apellido("Masis");
            a.setTelefono(7542);
            a.setEmail("anjaa");
            String fecha1 = "2023/03/10";
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha1);
            a.setFechaNacimiento(date1);
            //  a.setCarrera(c);

            //ServicioAlumno sn = new ServicioAlumno();
            //sn.insertarAlumno(a);
            Nota nota = new Nota();
            nota.setId_Curso("EIF500");
            nota.setNota(100);
            nota.setAlumno(a);
            ServicioNota sn =  ServicioNota.obtenerInstancia();
            //sn.buscarNotasAlumno("7026102525");
            Profesor p = new Profesor();
            p.setCedula_Profesor("702645320");
            p.setNombre("Pablo");
            p.setPrimer_apellido("Garro");
            p.setTelefono(545);
            p.setEmail("hsuhus");
            ServicioProfesor sp =  ServicioProfesor.obtenerInstancia();
            //sp.insertarProfesor(p);
            // Consejero c = new Consejero();
            //c.setAlumno(a);
            // c.setProfesor(p);
            //ServicioConsejero sc = new ServicioConsejero();
            //sc.insertarConsejero(c);
            ciclo.setAnnio(2022);
            ciclo.setNumero("I Ciclo");
            String fech1 = "2023/03/10";
            Date dat1 = new SimpleDateFormat("yyyy/MM/dd").parse(fech1);
            String fecha2 = "2023/03/10";
            Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha2);
            ciclo.setFecha_Incio(dat1);
            ciclo.setFecha_Finalizacion(date2);
            ServicioCiclo se =  ServicioCiclo.obtenerInstancia();
            //se.insertarCiclo(ciclo);
            Carrera carrera = new Carrera();

            Curso curso = new Curso();
            curso.setCodigo_Curso("Eifggs");
            curso.setNombre("Progra");
            curso.setCreditos(5);
            curso.setHoras_semanales(5);
            curso.setCarrera(c);
            ServicioCurso sq =  ServicioCurso.obtenerInstancia();
            //sq.insertarCurso(curso);

            Grupo g = new Grupo();
            g.setNumero_Grupo(5);
            g.setHorario("viernes");
            g.setCiclo(ciclo);
            g.setCurso(curso);
            g.setProfesor(p);
            g.setCampos_Restantes(20);
            g.setCapacidad_Maxima(30);
            ServicioGrupo l =  ServicioGrupo.obtenerInstancia();
            l.eliminarGrupo(5);


            // ServicioCiclo servicio = new ServicioCiclo();
            //servicio.insertarCiclo(ciclo);

            ServicioCiclo servicio =  ServicioCiclo.obtenerInstancia();
            //ciclo.setAnnio(2022);
            // ciclo.setNumero("II Ciclo");
            //ciclo.setFecha_Inicio(date1);
            //ciclo.setFecha_Finalizacion(date2);
            //servicio.modificaCiclo(ciclo);

            //for (Ciclo pr : servicio.listarCiclo()) {
            //System.out.println(pr.toString());
            // }

            //servicio.eliminarCiclo(2022,"II Ciclo");


        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

