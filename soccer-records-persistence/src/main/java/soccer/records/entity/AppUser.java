package soccer.records.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import soccer.records.enums.AppRole;

/**
 *
 * @author Michaela Bocanova
 */
@Entity
@Table(name="AppUser")
public class AppUser extends Auditable<String,Long> {
    
    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\....?", message = "invalid address")
    @NotNull
    @Size(max = 30, message = "not longer than 30 chars")
    private String email;
    
    @Column(nullable = false, unique = true)
    @Size(max = 30, message = "not longer than 30 chars")
    private String username;
    	
    private String passwordHash;
	
    //@Transient
    //private boolean admin;
    
    // works?
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AppRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")})    
    private List<AppRole> roles = new ArrayList<>();
    
    public List<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRole> roles) {
        this.roles = roles;
    }
    
    public void addRole(AppRole r) {
        roles.add(r);
    }
    
    public void removeRole(AppRole r) {
        roles.remove(r);
    }

    public AppUser() {
    }

    public String getPasswordHash() {
	return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
    }

    public String getEmail() {
    	return email;
    }

    public void setEmail(String email) {
    	this.email = email;
    }

    public String getUsername() {
	return username;
    }

    public void setUserName(String username) {
    	this.username = (username == null || username.trim().isEmpty()) ? email : username;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.email);
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
        final AppUser other = (AppUser) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AppUser{" + "email=" + email + ", username=" + username + ", roles=" + roles + '}';
    }
    
}
