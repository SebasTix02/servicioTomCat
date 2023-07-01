package Callback;

import Model.User;
import Model.UserDAO;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


/**
 *
 * @author ariel
 */
    
//Correspondiente a la peticion get
@Path("/getInfo")
public class Rest {
    
    private UserDAO ud=new UserDAO();
    
    /*
    //como recibe los valores el el path si mando por url
    @Path("/resource2/{id}")
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getID(@QueryParam("name") String name){
        return "hola padrino: "+name;
    }*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //public int getResource() {
    public String getUsersList() {
        //Lógica para obtener el recurso con el ID proporcionado
        Gson gson= new Gson();
        String json= gson.toJson(ud.list());
        return json;

    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUsers(MultivaluedMap<String, String> formParams) { 
        // Lógica para actualizar el recurso con el ID proporcionado
        String dni= formParams.getFirst("dni");
        String name= formParams.getFirst("name");
        String lastname= formParams.getFirst("lastname");
        String ageStr= formParams.getFirst("age");
        int age = Integer.parseInt(ageStr);
        User user= new User(dni, name, lastname, age);
        return Response.ok(ud.edit(user)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsers(MultivaluedMap<String, String> formParams) {
        // Lógica para eliminar el recurso con el ID proporcionado
        String dni= formParams.getFirst("dni");
        return Response.ok(ud.delete(dni)).build();
    }

    /*
    @POST
    public Response postData(
    */
    
    /*funciona con los parametros codificados a application/x-www-form-urlencoded
    ver en word 
    
    @POST
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Consumes(MediaType.MULTIPART_FORM_DATA) con este noa vale
    @Produces(MediaType.TEXT_PLAIN)
    public Response  InsertUsers(@FormParam("dni") String dni, 
            @FormParam("name") String name, @FormParam("lastname") String lastname, 
            @FormParam("age") String age) {
        // Lógica para crear un nuevo recurso con los datos proporcionados
        int ageInt= Integer.parseInt(age);
        User user= new User(dni, name, lastname, ageInt);
        return Response.ok(ud.add(user)).build();
        //return ud.add(user);
    }*/
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Produces(MediaType.TEXT_PLAIN)
    public Response  InsertUsers(MultivaluedMap<String, String> formParams) {
        String dni= formParams.getFirst("dni");
        String name= formParams.getFirst("name");
        String lastname= formParams.getFirst("lastname");
        String ageStr= formParams.getFirst("age");
        int age = Integer.parseInt(ageStr);
        User user= new User(dni, name, lastname, age);
        return Response.ok(ud.add(user)).build();
        //return ud.add(user);
    }

    
    //POST para el login returns true or false 
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(MultivaluedMap<String, String> formParams){
        String username= formParams.getFirst("username");
        String password= formParams.getFirst("password");
        return Response.ok(ud.adminExist(username, password)).build();
    }
    
}
