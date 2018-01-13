package soccer.records.dto;

import java.util.Objects;

/**
 *
 * @author Michaela Bocanova
 */

public class AppUserAuthenticationDto {
    
    //private Long id;
    private String username;
    private String password;

    /*
    public Long getId()
    {
        return id;
    }

    public void setId(Long userId)
    {
        this.id = userId;
    }
    */

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.username);
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
        final AppUserAuthenticationDto other = (AppUserAuthenticationDto) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    
}
