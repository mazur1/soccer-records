package soccer.records.facade;

import java.util.Collection;
import java.util.List;
import soccer.records.dto.AppUserAuthenticationDto;
import soccer.records.dto.AppUserDto;
import soccer.records.dto.AppUserRegisterDto;
import soccer.records.enums.AppRole;

/**
 *
 * @author Michaela Bocanova
 */
public interface AppUserFacade {

    boolean authenticate(AppUserAuthenticationDto u);

    Collection<AppUserDto> findAllUsers();

    AppUserDto findUserById(Long userId);

    AppUserDto findUserByUsername(String name);

    boolean authorize(AppUserDto u, List<AppRole> roleAccess);

    void registerUser(AppUserRegisterDto userDto);

    List<AppUserDto> findAllActiveUsers();
    
}
