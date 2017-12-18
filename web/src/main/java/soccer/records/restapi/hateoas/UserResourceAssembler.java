/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import soccer.records.dto.AppUserDto;

import soccer.records.restapi.controllers.UserController;

/**
 *
 * @author Radim Vidlák
 */

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<AppUserDto, UserResource> {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(UserResourceAssembler.class);

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }
    
    @Override
    public UserResource toResource(AppUserDto dto) {
    
        UserResource resource = new UserResource(dto);
        return resource;
        
    }
    
}
