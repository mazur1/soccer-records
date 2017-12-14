package soccer.records.restapi.hateoas;

import soccer.records.dto.PlayerDto;
import soccer.records.restapi.controllers.PlayerRestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Assembles a HATEOS-compliant representation of a category from a CategoryDTO.
 *
 * @author Tomas Mazurek
 */

@Component
public class PlayerResourceAssembler extends ResourceAssemblerSupport<PlayerDto, PlayerResource> {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;
    private final static Logger log = LoggerFactory.getLogger(PlayerResourceAssembler.class);

    public PlayerResourceAssembler() {
        super(PlayerRestController.class, PlayerResource.class);
    }

    @Override
    public PlayerResource toResource(PlayerDto playerDto) {
        long id = playerDto.getId();
        PlayerResource playerResource = new PlayerResource(playerDto);
        try {
            Link catLink = entityLinks.linkForSingleResource(PlayerDto.class, id).withSelfRel();
            playerResource.add(catLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return playerResource;
    }   
}
