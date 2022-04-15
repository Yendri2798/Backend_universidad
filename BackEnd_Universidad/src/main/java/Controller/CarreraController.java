package Controller;

import DataBaseConnectionDB.GlobalException;
import DataBaseConnectionDB.NoDataException;
import LogicaNegocio.Carrera;
import Model.CarreraModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/carrera")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class CarreraController {

    private final CarreraModel carreraModel = CarreraModel.obtenerInstancia();

    @POST
    @Path("/insertarCarrera")
    public Response insertarCarrera(Carrera carrera) throws NoDataException, GlobalException {
        System.out.println("metodo insertar carrera");
        carreraModel.insertarCarrera(carrera);
        return Response.ok().build();
    }

    @GET
    @Path("/buscarCarrera")
    public Response buscarCarrera(Carrera carrera) throws NoDataException, GlobalException {
        Carrera ptr = carreraModel.obtenerCarrera(carrera.getCodigo_Carrera());
        System.out.println(carrera);
        System.out.println(ptr);
        return Response.ok(ptr).build();
    }

    @DELETE
    @Path("/eliminarCarrera")
    public Response eliminarCarrera(Carrera carrera) {
        System.out.println("metodo eliminar carrera");
        try {
            carreraModel.eliminarCarrera(carrera);
        } catch (NoDataException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (GlobalException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity("Carrera eliminada").build();
    }

    @GET
    @Path("/listarCarrera")
    public Response listarCarrera() throws NoDataException, GlobalException {
        List<Carrera> list = carreraModel.listarCarrera();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @PUT
    @Path("/modificarCarrera")
    public Response modificarCarrera(Carrera carrera)  {
        System.out.println("metodo modificar carrera");
        try {
            carreraModel.modificar(carrera);
        } catch (NoDataException e) {
            System.out.println("Error1");
            e.printStackTrace();
        } catch (GlobalException e) {
            System.out.println("Error2");
            e.printStackTrace();
        }
        return Response.ok("Modifica carrera").build();
    }


}

