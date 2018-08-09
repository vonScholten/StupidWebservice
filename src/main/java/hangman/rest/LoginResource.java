/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.rest;

import brugerautorisation.data.Bruger;
import brugerautorisation.data.Diverse;
import brugerautorisation.transport.rmi.Brugeradmin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author s145005
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of hangman.rest.LoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    /**
     *
     * @param user
     * @return
     * @throws RemoteException
     */
    @POST
    @Path("authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(User user) throws RemoteException {
        System.out.print(user.getUsername());
        
        String loginData;
        Brugeradmin ba;
        Bruger b;

        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            b = ba.hentBruger(user.getUsername(), user.getPassword());

            loginData = "User: " + b + ", " + "Data: " + Diverse.toString(b);

        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(LoginResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Status.UNAUTHORIZED).build();
        }
        
        System.out.println(loginData);
        
        return Response.status(Status.ACCEPTED).build();
    }
    
}
