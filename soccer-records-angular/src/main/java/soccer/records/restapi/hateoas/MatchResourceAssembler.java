package soccer.records.restapi.hateoas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import soccer.records.dto.MatchDto;
import soccer.records.restapi.controllers.MatchController;

/**
 *
 * @author Michaela Bocanova
 */
@Component
public class MatchResourceAssembler extends ResourceAssemblerSupport<MatchDto, MatchResource> {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(MatchResourceAssembler.class);

    
    public MatchResourceAssembler() {
        super(MatchController.class, MatchResource.class);
    }
    
    @Override
    public MatchResource toResource(MatchDto dto) {
    
        Long id = dto.getId();
        MatchResource resource = new MatchResource(dto);
        try {
            Link catLink = entityLinks.linkForSingleResource(MatchDto.class, id).withSelfRel();
            resource.add(catLink);
            
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return resource;
        
    }
    
}
