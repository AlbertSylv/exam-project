/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Sport;
import entities.SportTeam;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author baske
 */
public class SportResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        
        httpServer.shutdownNow();
    }
    
        @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
        List<SportTeam> liste1 = new ArrayList<>();
        List<SportTeam> liste2 = new ArrayList<>();
        Sport sport1 = new Sport("Swimming", "Going forward in water", liste1);
        sport1.setId(1);
        Sport sport2 = new Sport("Basket", "Throw ball through hoop", liste2);
        sport2.setId(2);
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
    
    @Test
    public void testGetAllSports() {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .get("/sport/all").then()
                .statusCode(200)
                .body(notNullValue());
    }
    @Disabled
    @Test
    public void testGetSport() {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .get("/sport/1").then()
                .statusCode(200)
                .body("name",equalTo("Swimming"));
    }
    
    @Disabled
    @Test
    public void testAddSport() {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .body("{\"name\":\"ridning\",\"description\":\"Sidde på hest\"}")
                .when()
                .get("/sport").then()
                .statusCode(200)
                .body("name",equalTo("ridning"));
    }
}
