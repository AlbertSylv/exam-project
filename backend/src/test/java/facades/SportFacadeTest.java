/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.SportDTO;
import DTOs.SportsDTO;
import entities.Sport;
import entities.SportTeam;
import errorhandling.InvalidInputException;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author baske
 */
public class SportFacadeTest {
    private static EntityManagerFactory emf;
    private static SportFacade facade;

    public SportFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = SportFacade.getSportFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
        List<SportTeam> liste1 = new ArrayList<>();
        List<SportTeam> liste2 = new ArrayList<>();
        Sport sport1 = new Sport("Swimming", "Going forward in water", liste1);
        Sport sport2 = new Sport("Basket", "Throw ball through hoop", liste2);
        SportTeam sportTeam1 = new SportTeam(2000, "Team swim", 10, 32, sport1);
        SportTeam sportTeam2 = new SportTeam(1500, "Team children swim", 3, 10, sport1);
        SportTeam sportTeam3 = new SportTeam(700, "Team hoop", 10, 32, sport2);
        SportTeam sportTeam4 = new SportTeam(1650, "Team children hoop", 5, 10, sport2);
        liste1.add(sportTeam1);
        liste1.add(sportTeam2);
        liste2.add(sportTeam3);
        liste2.add(sportTeam4);
            
            em.getTransaction().begin();
            em.createQuery("delete from SportTeam").executeUpdate();
            em.createQuery("delete from Sport").executeUpdate();
            em.persist(sport1);
            em.persist(sport2);
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    @Test
     public void testGetAllSports() throws IOException, InterruptedException, ExecutionException, TimeoutException {
         
        SportsDTO sportDTOList = facade.getAllSports();
        System.out.println("Test get sports");
        int expectedResult = 2;

        SportsDTO result = sportDTOList;

        //assertThat(sportDTOList, containsInAnyOrder(hasProperty("name", is("Swimming"))));
        assertEquals(result.getAll().size(), expectedResult);
        
    }
    @Disabled 
    @Test
    public void testGetSport() throws IOException, InterruptedException, ExecutionException, TimeoutException, NotFoundException {

        SportDTO sportDTO = facade.getSport(2);
        System.out.println("Test get sport");
        String expectedResult = "Basket";

        String result = sportDTO.getName();

        assertEquals(expectedResult, result);
        
    }
    
    @Test
    public void testAddSport() throws IOException, InterruptedException, ExecutionException, TimeoutException, NotFoundException, InvalidInputException {
        Sport sport = new Sport("tunfisk", "Throw tuna through hoop", new ArrayList());
        SportDTO sportDTO = new SportDTO(sport);
        SportDTO sDTO = facade.addSport(sportDTO);
        System.out.println("Test add sport");
        
        
        String result = sDTO.getName();
        

        String expectedResult = sport.getName();

        assertEquals(expectedResult, result);
        
    }
}


