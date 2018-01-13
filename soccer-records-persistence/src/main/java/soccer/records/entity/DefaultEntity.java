package soccer.records.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

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
    
    @NotNull
    @Column(nullable = false)
    protected boolean isActive=true;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.isActive ? 1 : 0);
        return hash;
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
        final DefaultEntity<?> other = (DefaultEntity<?>) obj;
        if (this.isActive != other.isActive) {
            return false;
        }
        return true;
    }
    
    
}
