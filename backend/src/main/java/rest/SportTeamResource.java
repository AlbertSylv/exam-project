/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import DTOs.SportDTO;
import DTOs.SportTeamDTO;
import DTOs.SportTeamsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.InvalidInputException;
import errorhandling.NotFoundException;
import exceptions.MissingInputException;
import facades.SportTeamFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author baske
 */
@Path("sportteam")
public class SportTeamResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final SportTeamFacade FACADE = SportTeamFacade.getSportTeamFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello SportTeam World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllSportTeams() {
        SportTeamsDTO sportTeams = FACADE.getAllSportTeams();
        return GSON.toJson(sportTeams);
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getSportTeamById(@PathParam("id") Integer id) throws NotFoundException {
        SportTeamDTO STDTO = FACADE.getSportTeam(id);
        return GSON.toJson(STDTO);
    }
    
  /*  
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public String addSportTeam( String sportTeamDTO) throws MissingInputException, InvalidInputException, NotFoundException {
        SportTeamDTO stDTO = GSON.fromJson(sportTeamDTO, SportTeamDTO.class);
        int id = stDTO.getSport().getId();
        SportTeamDTO stDTOAdded = FACADE.addSportTeam(id, stDTO);
        return GSON.toJson(stDTOAdded);
    }
*/

    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("admin")
    @Path("{id}")
    public String editSportTeam(String sportTeam, @PathParam("id") Integer id) throws NotFoundException, MissingInputException, InvalidInputException, errorhandling.MissingInputException {
        SportTeamDTO stDTO = GSON.fromJson(sportTeam, SportTeamDTO.class);
        stDTO.setId(id);

        SportTeamDTO stEdited = FACADE.editSportTeam(stDTO);

        return GSON.toJson(stEdited);
    }
    
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("admin")
    @Path("{id}")
    public String deleteSportTeam(@PathParam("id") Integer id) throws NotFoundException, MissingInputException, InvalidInputException, errorhandling.MissingInputException {
        SportTeamDTO STDTO = FACADE.getSportTeam(id);

        FACADE.deleteSportTeam(id);

        return GSON.toJson(STDTO);
    }
    
}
