/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

/**
 *
 * @author Michaela Bocanova
 */
/*@Component
public class MatchResourceAssembler extends ResourceAssemblerSupport<MatchDto, MatchResource> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(MatchResourceAssembler.class);

    
    public MatchResourceAssembler(@SuppressWarnings("SpringJavaAutowiringInspection") @Autowired EntityLinks entityLinks) {
        super(MatchController.class, MatchResource.class);
        this.entityLinks = entityLinks;
    }
    
    @Override
    public MatchResource toResource(MatchDto dto) {
    
        long id = dto.getId();
        MatchResource resource = new MatchResource(dto);
        try {
            Link catLink = entityLinks.linkForSingleResource(MatchDto.class, id).withSelfRel();
            resource.add(catLink);

        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return resource;
        
    }
    
}*/
