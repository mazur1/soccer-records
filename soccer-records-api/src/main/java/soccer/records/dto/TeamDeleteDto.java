
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
public class TeamDeleteDto {
    
    private Long id;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if(id != null)
            return prime * result + id.hashCode();    

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
        
        TeamCreateDto other = (TeamCreateDto) obj;                       
        
        return true;
    }    
}
