package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author Michaela Bocanova
 */
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class AppUserAuthenticationDto {
    
    private Long id;
    private String username;
    private String password;

    public Long getId()
    {
        return id;
    }

    public void setId(Long userId)
    {
        this.id = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
