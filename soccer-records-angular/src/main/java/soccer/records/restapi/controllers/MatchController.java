package soccer.records.restapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing REST requests for match resource.
 * @author Michaela Bocanova
 */
@RestController
@RequestMapping("/matches")
//@ExposesResourceFor(MatchDto.class)
public class MatchController {
    
    private final static Logger log = LoggerFactory.getLogger(MatchController.class);

    /*public MatchController(
            @Autowired MatchFacade matchFacade,
            @Autowired PlayerResultFacade playerResultFacade,
            @Autowired TeamFacade teamFacade,
            @Autowired MatchResourceAssembler matchResourceAssembler,
            @Autowired PlayerResultResourceAssembler playerResultResourceAssembler,
            @Autowired TeamResourceAssembler teamResourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
            @Autowired EntityLinks entityLinks
    ) {
        this.matchFacade = matchFacade;
        this.teamFacade = teamFacade;
        this.teamFacade = teamFacade;
        this.matchResourceAssembler = matchResourceAssembler;
        this.playerResultResourceAssembler = playerResultResourceAssembler;
        this.teamResourceAssembler = teamResourceAssembler;
        this.entityLinks = entityLinks;
    }

    private MatchFacade matchFacade;
    private PlayerResultFacade playerResultFacade;
    private TeamFacade teamFacade;
    private MatchResourceAssembler matchResourceAssembler;
    private PlayerResultResourceAssembler playerResultResourceAssembler;
    private TeamResourceAssembler teamResourceAssembler;
    private EntityLinks entityLinks;*/
    
}
