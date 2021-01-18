/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.SportDTO;
import DTOs.SportTeamDTO;
import DTOs.SportsDTO;
import entities.Sport;
import entities.SportTeam;
import errorhandling.InvalidInputException;
import errorhandling.NotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author baske
 */
public class SportFacade {
    private static SportFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private SportFacade() {
    }
    
    public static SportFacade getSportFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SportFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
        
    public SportsDTO getAllSports() {  
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("SELECT s FROM Sport s");
            List<Sport> sportList = query.getResultList();
            SportsDTO sDTO = new SportsDTO(sportList);
            return sDTO;

        } catch (Exception e) {
            
        } finally {

            em.close();
        }
        return null;
    }

    
    public SportDTO addSport(SportDTO sportDTO) throws InvalidInputException, NotFoundException {
        EntityManager em = getEntityManager();
        Sport sport = new Sport(sportDTO);

        try {
            em.getTransaction().begin();
            em.persist(sport);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new SportDTO(sport);
    }
    
        public SportDTO getSport(Integer id) throws NotFoundException {
        EntityManager em = getEntityManager();

        try {
            Sport sport = em.find(Sport.class, id);

            if (sport == null) {
                throw new NotFoundException(String.format("Sport with id: \"(%d)\" not found.", id));
            } else {
                SportDTO sportDTO = new SportDTO(sport);

                return sportDTO;
            }
        } finally {
            em.close();
        }
    }
}
