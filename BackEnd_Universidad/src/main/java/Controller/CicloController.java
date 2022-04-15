package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Ciclo;
import Model.CicloModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/ciclo")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class CicloController {

    private final CicloModel cicloModel = CicloModel.obtenerInstancia();

    @POST
    @Path("/insertarCiclo")
    public Response insertarCiclo(Ciclo ciclo) throws NoDataException, GlobalException {
        System.out.println("metodo insertar ciclo");
        cicloModel.insertarCiclo(ciclo);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarCiclo")
    public Response buscarCiclo(Ciclo ciclo) throws NoDataException, GlobalException {
        Ciclo ptr = cicloModel.obtenerCiclo(ciclo.getAnnio());
        System.out.println(ciclo);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }

    @DELETE
    @Path("/eliminarCiclo")
    public Response eliminarCiclo(Ciclo ciclo) {
        System.out.println("metodo eliminar ciclo");
        try {
            cicloModel.eliminarCiclo(ciclo);
        } catch (NoDataException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (GlobalException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity("Ciclo eliminado").build();
    }

    @GET
    @Path("/listarCiclo")
    public Response listarCiclo() throws NoDataException, GlobalException {
        List<Ciclo> list = cicloModel.listarCiclo();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarCiclo")
    public Response modificarCiclo(Ciclo ciclo)  {
        System.out.println("metodo modificar ciclo");
        try {
            cicloModel.modificarCiclo(ciclo);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica ciclo").build();
    }

}
