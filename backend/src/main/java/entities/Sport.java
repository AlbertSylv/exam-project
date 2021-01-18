/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DTOs.SportDTO;
import DTOs.SportTeamDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author baske
 */
@Entity
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    
//    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sport")
//    private List<SportTeam> teams;

    public Sport() {
    }

    public Sport(String name, String description, List<SportTeam> teams) {
        this.name = name;
        this.description = description;
        //this.teams = new ArrayList();
    }

    public Sport(SportDTO sportDTO) {
        this.name = sportDTO.getName();
        this.description = sportDTO.getDescription();
 /*     if(sportDTO.getTeams()!= null){
        for (SportTeamDTO st : sportDTO.getTeams()) {
            this.teams.add(new SportTeam(st));
        }}else{
            this.teams = null;
        }
*/
    }


  /*  
    public void addTeam(SportTeam ST) {
        if (ST != null) {
            this.teams.add(ST);
            ST.setSport(this);
        }else{
          this.teams = null;  
        }
    }


    public void removeTeam(SportTeam ST) {
        ST.setSport(null);
        this.teams.remove(ST);
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*    public List<SportTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<SportTeam> teams) {
        this.teams = teams;
    }
  */  
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    
}
