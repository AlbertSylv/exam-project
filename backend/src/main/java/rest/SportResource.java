/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTOs.SportDTO;
import DTOs.SportsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.InvalidInputException;
import errorhandling.NotFoundException;
import exceptions.MissingInputException;
import facades.SportFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author baske
 */
@Path("sport")
public class SportResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final SportFacade FACADE = SportFacade.getSportFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllSports() {
        SportsDTO sports = FACADE.getAllSports();
        String jsonstring = GSON.toJson(sports);
        return jsonstring;
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getSportById(@PathParam("id") Integer id) throws NotFoundException {
        SportDTO s = FACADE.getSport(id);
        return GSON.toJson(s);
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public String addSport(String sportDTO) throws MissingInputException, InvalidInputException, NotFoundException {
        SportDTO sDTO = GSON.fromJson(sportDTO, SportDTO.class);
        SportDTO sDTOAdded = FACADE.addSport(sDTO);
        return GSON.toJson(sDTOAdded);
    }
    
    
}
