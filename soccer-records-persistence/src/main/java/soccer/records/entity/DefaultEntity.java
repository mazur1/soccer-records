package soccer.records.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Michaela Bocanova
 * @param <TKey>
 */
@MappedSuperclass
public abstract class DefaultEntity<TKey> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected TKey id;
    
    public TKey getId() {
        return id;
    }

    public void setId(TKey id) {
        this.id = id;
    }
}
