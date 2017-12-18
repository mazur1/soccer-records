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
import soccer.records.dto.PlayerResultDto;

import soccer.records.restapi.controllers.PlayerResultRestController;

/**
 *
 * @author Radim Vidlák
 */

@Component
public class PlayerResultResourceAssembler extends ResourceAssemblerSupport<PlayerResultDto, PlayerResultResource> {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(MatchResourceAssembler.class);

    public PlayerResultResourceAssembler() {
        super(PlayerResultRestController.class, PlayerResultResource.class);
    }
    
    @Override
    public PlayerResultResource toResource(PlayerResultDto dto) {
    
        long id = dto.getId();
        PlayerResultResource resource = new PlayerResultResource(dto);
        try {
            Link catLink = entityLinks.linkForSingleResource(PlayerResultDto.class, id).withSelfRel();
            resource.add(catLink);
            
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return resource;
        
    }
    
}
