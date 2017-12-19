/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Mazurek
 */
/*@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")*/
public class TeamDto extends AuditableDto<String> {
    
    private Long id;
    private String name;
 
    //@JsonIgnore
    private List<PlayerDto> players = new ArrayList<>();
    @JsonIgnore
    private List<MatchDto> matchesHome = new ArrayList<>();
    @JsonIgnore
    private List<MatchDto> matchesAway = new ArrayList<>();
    
    public Long getId() {
        return this.id;
    }
    
    public void setId (Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPlayers(List<PlayerDto> players){
        this.players = players;
    }

    public void setMatchesHome(List<MatchDto> matchesHome) {
        this.matchesHome = matchesHome;
    }

    public void setMatchesAway(List<MatchDto> matchesAway) {
        this.matchesAway = matchesAway;
    }


    
    public List<PlayerDto> getPlayers() {
        return this.players;
    }
    
    public List<MatchDto> getMatchesHome() {
        return this.matchesHome;
    }
    
    public List<MatchDto> getMatchesAway() {
        return this.matchesAway;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
             
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        //result = prime * result + ((players == null) ? 0 : players.hashCode());
        //result = prime * result + ((matchesHome == null) ? 0 : matchesHome.hashCode());
        //result = prime * result + ((matchesAway == null) ? 0 : matchesAway.hashCode());
        return result;
        
    }   
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        TeamDto other = (TeamDto) obj;        
        
        if (name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        
        /*if (!matchesHome.equals(other.getMatchesHome())) {
            return false;
        }
        
        if (!matchesHome.equals(other.getMatchesAway())) {
            return false;
        }

        if (!matchesHome.equals(other.getPlayers())) {
            return false;
        } */                      
        
        return true;
    }    
}
