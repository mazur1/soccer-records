/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import java.util.Date;

/**
 *
 * @author Michaela Bocanova
 * @param <TUser>
 * @param <TKey>
 */
public abstract class AuditableDto<TUser> {
        
    protected TUser createdBy;
    
    protected Date creationDate;
    
    protected TUser lastModifiedBy;
    
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
    
}
