
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Mazurek
 */
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class TeamEditDto {
    private Long id;
    
    private String name;
    
    @JsonIgnore
    private List<PlayerDto> players = new ArrayList<PlayerDto>();
    
    @JsonIgnore
    private List<MatchDto> matchesHome = new ArrayList<MatchDto>();
    
    @JsonIgnore
    private List<MatchDto> matchesAway = new ArrayList<MatchDto>();
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<PlayerDto> getTeamPlayers() {
        return this.players;
    }
    
    public List<MatchDto> getMatchesHome() {
        return this.matchesHome;
    }
    
    public List<MatchDto> getMatchesAway() {
        return this.matchesAway;
    }
    
    public void addMatchHome(MatchDto m){
        matchesHome.add(m);
    }
    
    public void addMatchAway(MatchDto m){
        matchesAway.add(m);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
             
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((players == null) ? 0 : players.hashCode());
        result = prime * result + ((matchesHome == null) ? 0 : matchesHome.hashCode());
        result = prime * result + ((matchesAway == null) ? 0 : matchesAway.hashCode());
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
        
        TeamEditDto other = (TeamEditDto) obj;        
        
        if (name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        
        if (!matchesHome.equals(other.getMatchesHome())) {
            return false;
        }
        
        if (!matchesHome.equals(other.getMatchesAway())) {
            return false;
        }

        if (!matchesHome.equals(other.getTeamPlayers())) {
            return false;
        }                       
        
        return true;
    }    
}
