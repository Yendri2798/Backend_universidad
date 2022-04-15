package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Nota;
import Model.NotaModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/nota")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class NotaController {

    private final NotaModel notaModel = NotaModel.obtenerInstancia();

    @POST
    @Path("/insertarNota")
    public Response insertarNota(Nota nota) throws NoDataException, GlobalException {
        System.out.println("metodo insertar nota");
        notaModel.insertarNota(nota);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarNota")
    public Response buscarCarrera(Nota nota) throws NoDataException, GlobalException {
        List<Nota> list = notaModel.obtenerNota(nota.getId_Curso());
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarNota")
    public Response modificarNota(Nota nota)  {
        System.out.println("metodo modificar nota");
        try {
            notaModel.modificar(nota);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica nota").build();
    }


}


