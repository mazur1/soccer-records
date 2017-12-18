package soccer.records.restapi.hateoas;

import soccer.records.dto.TeamDto;
import soccer.records.restapi.controllers.TeamRestController;

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
 * @author Radim Vidlák
 */

@Component
public class TeamResourceAssembler extends ResourceAssemblerSupport<TeamDto, TeamResource> {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;
    private final static Logger log = LoggerFactory.getLogger(TeamResourceAssembler.class);

    public TeamResourceAssembler() {
        super(TeamRestController.class, TeamResource.class);
    }

    @Override
    public TeamResource toResource(TeamDto teamDto) {
        long id = teamDto.getId();
        TeamResource teamResource = new TeamResource(teamDto);
        try {
            Link catLink = entityLinks.linkForSingleResource(TeamDto.class, id).withSelfRel();
            teamResource.add(catLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return teamResource;
    }   
}
