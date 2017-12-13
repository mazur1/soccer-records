/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.entity;

import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Michaela Bocanova
 */
@Configurable
public class TimestampStringAuditListener {
    
    @PrePersist
    public void touchForCreate(Auditable target) {
        Date now = new Date();
        target.setCreationDate(now);
        target.setLastModifiedDate(now);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            target.setCreatedBy("N/A");
            target.setLastModifiedBy("N/A");
        }
        else {
            AppUser u = (AppUser) authentication.getPrincipal();
            target.setCreatedBy(u.getUsername());
            target.setLastModifiedBy(u.getUsername());
        }
    }

    @PreUpdate
    public void touchForUpdate(Auditable target) {
        target.setLastModifiedDate(new Date());
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            target.setLastModifiedBy("N/A");
        }
        else {
            target.setLastModifiedBy(((AppUser) authentication.getPrincipal()).getUsername());
        }
    }
}
