/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import DTOs.SportDTO;
import DTOs.SportTeamDTO;
import DTOs.SportTeamsDTO;
import entities.Sport;
import entities.SportTeam;
import errorhandling.InvalidInputException;
import errorhandling.MissingInputException;
import errorhandling.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author baske
 */
public class SportTeamFacade {
    private static SportTeamFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private SportTeamFacade() {
    }
    
    public static SportTeamFacade getSportTeamFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SportTeamFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public SportTeamsDTO getAllSportTeams() {
        EntityManager em = getEntityManager();
        try {
            return new SportTeamsDTO( em.createQuery("SELECT s FROM SportTeam s", SportTeam.class
            ).getResultList());
        } finally {
            em.close();
        }
    }
    
    public SportTeamDTO getSportTeam(Integer id) throws NotFoundException {
        
        
        EntityManager em = getEntityManager();

        try {
            SportTeam ST = em.find(SportTeam.class, id);

            if (ST == null) {
                throw new NotFoundException(String.format("SportTeam with id: \"(%d)\" not found.", id));
            } else {
                SportTeamDTO sportTeamDTO = new SportTeamDTO(ST);

                return sportTeamDTO;
            }
        } finally {
            em.close();
        }
    }
        
    public SportTeamDTO deleteSportTeam(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        SportTeam ST = em.find(SportTeam.class,id);
        if (ST == null) {
            throw new NotFoundException(String.format("SportTeam with id: (%d) not found", id));
        } else {
            try {
                em.getTransaction().begin();
                em.remove(ST);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new SportTeamDTO(ST);
        }
    }
    
    public SportTeamDTO editSportTeam(SportTeamDTO sportTeamDTO) throws MissingInputException, InvalidInputException {
        EntityManager em = getEntityManager();
        SportTeam ST = em.find(SportTeam.class, sportTeamDTO.getId());

        try {
            ST.setTeamName(sportTeamDTO.getTeamName());
            ST.setMaxAge(sportTeamDTO.getMaxAge());
            ST.setMinAge(sportTeamDTO.getMinAge());
            ST.setPricePerYear(sportTeamDTO.getPricePerYear());

            em.getTransaction().begin();
            em.merge(ST);
            em.getTransaction().commit();

            System.out.println(ST);
            return new SportTeamDTO(ST);
        } finally {
            em.close();
        }
    }
        
 /*   public SportTeamDTO addSportTeam(int sportID, SportTeamDTO sportTeamDTO) throws InvalidInputException, NotFoundException {
        EntityManager em = getEntityManager();
        SportFacade facade = SportFacade.getSportFacade(emf);
        SportDTO sportDTO = facade.getSport(sportID);
        Sport sport = new Sport(sportDTO);
        SportTeam sportTeam = new SportTeam(sportTeamDTO);

        try {
            em.getTransaction().begin();
            sport.addTeam(sportTeam);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new SportTeamDTO(sportTeam);
        }
   */ 

}