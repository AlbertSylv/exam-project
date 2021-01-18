/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;


import entities.Sport;
import entities.SportTeam;
import java.util.List;

/**
 *
 * @author baske
 */
public class SportDTO {
    private Integer id;
    private String name;
    private String description;
    private List<SportTeamDTO> teams;

    public SportDTO() {
    }

    public SportDTO(Sport s) {
        if(s.getId() != null)this.id = s.getId();
        this.name = s.getName();
        this.description = s.getDescription();
/*        for (SportTeam st : s.getTeams()) {
            this.teams.add(new SportTeamDTO(st));
            int count = 0;
            System.out.println("hej" + count++);
          }
   */     
    }

    public SportDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<SportTeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<SportTeamDTO> teams) {
        this.teams = teams;
    }
    
    
    
}
