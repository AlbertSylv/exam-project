/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DTOs.SportTeamDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author baske
 */
@Entity
public class SportTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int pricePerYear;
    private String teamName;
    private int minAge;
    private int maxAge;
    
    @ManyToOne
    private Sport sport;

    public SportTeam() {
    }

    public SportTeam(int pricePerYear, String teamName, int minAge, int maxAge, Sport sport) {
        this.pricePerYear = pricePerYear;
        this.teamName = teamName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.sport = sport;
    }
        
    public SportTeam(SportTeamDTO sportTeamDTO) {
        this.pricePerYear = sportTeamDTO.getPricePerYear();
        this.teamName = sportTeamDTO.getTeamName();
        this.minAge = sportTeamDTO.getMinAge();
        this.maxAge = sportTeamDTO.getMaxAge();
        this.sport = sportTeamDTO.getSport();
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


    
}
