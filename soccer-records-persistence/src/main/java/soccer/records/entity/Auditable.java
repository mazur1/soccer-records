package soccer.records.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Michaela Bocanova
 * @param <TUser>
 * @param <TKey>
 */
@MappedSuperclass
@EntityListeners(TimestampStringAuditListener.class)
public abstract class Auditable<TUser, TKey> extends DefaultEntity<TKey> {
    
    protected TUser createdBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;
    
    protected TUser lastModifiedBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;

    public TUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TUser createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public TUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(TUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.createdBy);
        hash = 37 * hash + Objects.hashCode(this.creationDate);
        hash = 37 * hash + Objects.hashCode(this.lastModifiedBy);
        hash = 37 * hash + Objects.hashCode(this.lastModifiedDate);
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
        final Auditable<?,?> other = (Auditable<?,?>) obj;
        if (!Objects.equals(this.createdBy, other.createdBy)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedBy, other.lastModifiedBy)) {
            return false;
        }
        if (!Objects.equals(this.lastModifiedDate, other.lastModifiedDate)) {
            return false;
        }
        return true;
    }

}
