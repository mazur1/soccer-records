
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Mazurek
 */
public class TeamEditDto {
    
    private Long id;
    
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
             
        result = prime * result + ((name == null) ? 0 : name.hashCode());

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
        
//        if (!matchesHome.equals(other.getMatchesHome())) {
//            return false;
//        }
//        
//        if (!matchesHome.equals(other.getMatchesAway())) {
//            return false;
//        }
//
//        if (!matchesHome.equals(other.getTeamPlayers())) {
//            return false;
//        }                       
        
        return true;
    }    
}
