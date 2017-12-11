package soccer.records.facade;

import java.util.Collection;
import soccer.records.dto.AppUserAuthenticationDto;
import soccer.records.dto.AppUserDto;

/**
 *
 * @author Michaela Bocanova
 */
public interface AppUserFacade {

    boolean authenticate(AppUserAuthenticationDto u);

    Collection<AppUserDto> findAllUsers();

    AppUserDto findUserById(Long userId);

    AppUserDto findUserByUsername(String name);

    boolean isAdmin(AppUserDto u);

    void registerUser(AppUserDto userDto, String unencryptedPassword);
    
}
