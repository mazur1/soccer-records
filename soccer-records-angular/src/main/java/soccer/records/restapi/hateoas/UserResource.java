/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import soccer.records.dto.AppUserAuthenticationDto;

/**
 *
 * @author Radim Vidlák
 */

@Relation(value = "user", collectionRelation = "users")
@JsonPropertyOrder({"userId", "username", "password"})
public class UserResource extends ResourceSupport{

    @JsonProperty("id") 
    private Long userId;
    private String username;
    private String password;
    
    public UserResource(AppUserAuthenticationDto dto) {
        this.userId = dto.getId();
        username = dto.getUsername();
        password = dto.getPassword();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
