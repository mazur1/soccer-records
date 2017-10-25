package soccer.records.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tomas
 */
@Entity
public class Match {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
        
    public Match(Long categoryId) {
        this.id = categoryId; 
    }

    public Match() {
    }
        
    public Long getId() {
        return id;
    }

        
        
}
