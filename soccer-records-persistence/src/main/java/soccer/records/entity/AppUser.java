/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotEmpty;
import soccer.records.enums.AppRole;

/**
 *
 * @author Michaela Bocanova
 */
@Entity
@Table(name="AppUser")
public class AppUser {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".+@.+\\....?", message = "invalid address")
    @NotNull
    @Size(max = 30, message = "not longer than 30 chars")
    private String email;
    
    @Column(nullable = false, unique = true)
    @Size(max = 30, message = "not longer than 30 chars")
    private String username;
    	
    private String passwordHash;
	
    @Transient
    private boolean admin;
    
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
            return true;
	if (obj == null)
            return false;
	if (!(obj instanceof AppUser))
            return false;
	AppUser other = (AppUser) obj;
	if (email == null) {
            if (other.getEmail() != null)
		return false;
	} else if (!email.equals(other.getEmail()))
            return false;
	return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", admin=" + admin +
                '}';
    }   
}
