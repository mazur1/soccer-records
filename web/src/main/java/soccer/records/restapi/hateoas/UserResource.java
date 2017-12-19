/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.core.Relation;

import soccer.records.dto.AppUserDto;

/**
 *
 * @author Radim Vidlák
 */

@Relation(value = "user", collectionRelation = "users")
@JsonPropertyOrder({"userId", "username"})
public class UserResource extends AuditableResource<String> {

    @JsonProperty("id") 
    private Long userId;
    private String username;
    
    public UserResource(AppUserDto dto) {
        super(dto);
        this.userId = dto.getId();
        username = dto.getUsername();
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

}
