/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entities.Sport;
import entities.SportTeam;

/**
 *
 * @author baske
 */
public class SportTeamDTO {
    private Integer id;
    private int pricePerYear;
    private String teamName;
    private int minAge;
    private int maxAge;
    private Sport sport;
    

    public SportTeamDTO() {
    }

    public SportTeamDTO(SportTeam st) {
        if(st.getId() != null)this.id = st.getId();
        this.sport = st.getSport();
        this.pricePerYear = st.getPricePerYear();
        this.teamName = st.getTeamName();
        this.minAge = st.getMinAge();
        this.maxAge = st.getMaxAge();
        
    }

    public SportTeamDTO(int pricePerYear, String teamName, int minAge, int maxAge, Sport sport) {
        this.pricePerYear = pricePerYear;
        this.teamName = teamName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.sport = sport;
    }


    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPricePerYear() {
        return pricePerYear;
    }

    public void setPricePerYear(int pricePerYear) {
        this.pricePerYear = pricePerYear;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
    
    
    
}
