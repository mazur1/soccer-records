package soccer.records.dto;

/**
 *
 * @author Michaela Bocanova
 */
public class AppUserAuthenticationDto {
    
    private Long userId;
    private String password;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
}
