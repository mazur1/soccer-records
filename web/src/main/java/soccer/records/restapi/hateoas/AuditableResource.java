/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import java.util.Date;
import org.springframework.hateoas.ResourceSupport;
import soccer.records.dto.AuditableDto;

/**
 *
 * @author Michaela Bocanova
 * @param <TUser>
 * @param <TKey>
 */
public abstract class AuditableResource<TUser> extends ResourceSupport {
        
    protected TUser createdBy;
    
    protected Date creationDate;
    
    protected TUser lastModifiedBy;
    
    protected Date lastModifiedDate;

    public AuditableResource(AuditableDto<TUser> dto) {
        this.createdBy = dto.getCreatedBy();
        this.creationDate = dto.getCreationDate();
        this.lastModifiedBy = dto.getLastModifiedBy();
        this.lastModifiedDate = dto.getLastModifiedDate();
    }

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
