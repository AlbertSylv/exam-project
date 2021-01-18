package utils;


import entities.Role;
import entities.Sport;
import entities.SportTeam;
import entities.User;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    //ups jeg glemte at slette kodermne.. nu bliver min fiktive sportsclub hacket..
    User user = new User("user", "test2");
    User admin = new User("admin", "test2");
    User both = new User("user_admin", "test2");
    
        List<SportTeam> liste1 = new ArrayList<>();
        List<SportTeam> liste2 = new ArrayList<>();
        List<SportTeam> liste3 = new ArrayList<>();
        Sport sport1 = new Sport("Swimming", "Going forward in water", liste1);
        Sport sport2 = new Sport("Basket", "Throw ball through hoop", liste2);
        Sport sport3 = new Sport("Fobold", "Kick ball into net", liste3);
        
        SportTeam sportTeam1 = new SportTeam(2000, "Team swim", 10, 32, sport1);
        SportTeam sportTeam2 = new SportTeam(1500, "Team children swim", 3, 10, sport1);
        SportTeam sportTeam3 = new SportTeam(700, "Team hoop", 10, 32, sport2);
        SportTeam sportTeam4 = new SportTeam(1650, "Team children hoop", 5, 10, sport2);
        liste1.add(sportTeam1);
        liste1.add(sportTeam2);
        liste2.add(sportTeam3);
        liste2.add(sportTeam4);

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
            em.createQuery("delete from SportTeam").executeUpdate();
            em.createQuery("delete from Sport").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.persist(sport1);
            em.persist(sport2);
            em.persist(sport3);
            em.persist(sportTeam1);
            em.persist(sportTeam2);
            em.persist(sportTeam3);
            em.persist(sportTeam4);
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
