/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dto.AppUserAuthenticationDto;
import soccer.records.dto.AppUserDto;
import soccer.records.dto.AppUserRegisterDto;
import soccer.records.entity.AppUser;
import soccer.records.enums.AppRole;
import soccer.records.services.AppUserService;
import soccer.records.services.BeanMappingService;

/**
 *
 * @author Michaela Bocanova
 */
@Service
@Transactional
public class AppUserFacadeImpl implements AppUserFacade {
    
    final static Logger log = LoggerFactory.getLogger(AppUserFacadeImpl.class);

    @Inject
    private AppUserService userService;
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public AppUserDto findUserById(Long userId) {
        AppUser user = userService.findById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, AppUserDto.class);
    }

    @Override
    public AppUserDto findUserByUsername(String name) {
        AppUser user = userService.findByUsername(name);
        return (user == null) ? null : beanMappingService.mapTo(user, AppUserDto.class);
    }

    
    @Override
    public Collection<AppUserDto> findAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), AppUserDto.class);
    }
    
    @Override
    public void registerUser(AppUserRegisterDto userDto) {
        AppUser user = beanMappingService.mapTo(userDto, AppUser.class);
        userService.registerUser(user, userDto.getUnencryptedPassword());
        //userDto.setId(user.getId());
    }

    @Override
    public boolean authenticate(AppUserAuthenticationDto u) {
        return userService.authenticate(
                userService.findByUsername(u.getUsername()), u.getPassword());
    }

    @Override
    public boolean authorize(AppUserDto u, List<AppRole> roleAccess) {
        return userService.authorize(
                userService.findById(u.getId()), roleAccess);
    }
    
    @Override
    public List<AppUserDto> findAllActiveUsers() {
        return beanMappingService.mapTo(userService.findAllActive(), AppUserDto.class);
    }
}
