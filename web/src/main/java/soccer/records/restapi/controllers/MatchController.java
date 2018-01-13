package soccer.records.restapi.controllers;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.facade.MatchFacade;
import soccer.records.restapi.exceptions.InvalidRequestException;
import soccer.records.restapi.exceptions.ResourceNotFoundException;
import soccer.records.restapi.exceptions.ServerProblemException;
import soccer.records.restapi.hateoas.MatchResource;
import soccer.records.restapi.hateoas.MatchResourceAssembler;

/**
 * Controller for managing REST requests for match resource.
 * @author Michaela Bocanova
 */
@RestController
@RequestMapping("/matches")
@ExposesResourceFor(MatchDto.class)
public class MatchController {
    
    private final static Logger log = LoggerFactory.getLogger(MatchController.class);

    public MatchController() {
    }

    @Autowired
    private MatchFacade matchFacade;
    @Autowired
    private MatchResourceAssembler matchResourceAssembler;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;
    
    @RequestMapping(/*value = "/{active}",*/ method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<MatchResource>> getMatches(/*@PathVariable("active") boolean active*/) {
        
        log.debug("rest: getMatches()");
        List<MatchDto> all = matchFacade.findAllMatches();
        List<MatchResource> resourceCollection;
        
        //if(active)
            resourceCollection = matchResourceAssembler.toResources(matchFacade.filterActiveMatches(all));
        /*else
            resourceCollection  = matchResourceAssembler.toResources(all);*/
            
        for (MatchResource matchResource : resourceCollection) {
            //matchResource.setTeamHomeGoalsScored(matchFacade.getTeamHomeGoalsScored(matchResource.getDtoId()));
            //matchResource.setTeamAwayGoalsScored(matchFacade.getTeamAwayGoalsScored(matchResource.getDtoId()));
        }    
        
        Resources<MatchResource> matchResources = new Resources<>(resourceCollection,
                linkTo(MatchController.class).withSelfRel(),
                linkTo(MatchController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(matchResources, HttpStatus.OK);

    }

    @RequestMapping(value = /*{active}*/"/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<MatchResource> getMatch(@PathVariable("id") Long id/*, @PathVariable("active") boolean active*/) throws Exception {
        
        log.debug("rest: getMatch(" + String.valueOf(id) + ")");
        MatchDto matchDto = matchFacade.findMatchById(id);
        
        if(matchDto == null){
            throw new ResourceNotFoundException("match " + id + " not found");
        }
        /*else if(active && !matchDto.getIsActive()){
            throw new ResourceNotFoundException("match " + id + " not found");
        }*/
        
        MatchResource resource = matchResourceAssembler.toResource(matchDto);
        resource.setPlayerResults(matchFacade.getPlayerResults(id));
        //resource.setTeamHomeGoalsScored(matchFacade.getTeamHomeGoalsScored(id));
        //resource.setTeamAwayGoalsScored(matchFacade.getTeamAwayGoalsScored(id));
        
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public final void deleteMatch(@PathVariable("id") long id) throws Exception {
        
        log.debug("rest: deleteMatch(" + String.valueOf(id) + ")");
        try {
            matchFacade.deleteMatch(id);
        } catch (IllegalArgumentException ex) {
            log.error("match " + id + " not found");
            throw new ResourceNotFoundException("match " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete match " + String.valueOf(id) + " :" + ex.getMessage());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
    }
    
    @RequestMapping(value = "/{id}/score", method = RequestMethod.PUT)
    public final void updateMatchScore(@PathVariable("id") long id) throws Exception {
        
        log.debug("rest editMatch({})", id);
        try {
            matchFacade.updateMatchScore(id);
        } catch (Exception ex) {
            log.debug("Cannot edit match with id {}", id);
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public final HttpEntity<MatchResource> createMatch(@RequestBody @Valid MatchCreateDto matchDto, BindingResult bindingResult) throws Exception {
        
        log.debug("rest: createMatch(" + matchDto.toString() + ")");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        
        Long id;
        try {
            id = matchFacade.createMatch(matchDto);
        } catch (Throwable ex) {
            log.error("cannot create match " + matchDto.toString());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
        
        MatchResource resource = matchResourceAssembler.toResource(matchFacade.findMatchById(id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void editMatch(@PathVariable("id") long id, @RequestBody @Valid MatchEditDto matchDto, BindingResult bindingResult) throws Exception {
        log.debug("rest editMatch({})", id);
        try {
            matchFacade.updateMatch(matchDto);
        } catch (Exception ex) {
            log.debug("Cannot edit match with id {}", id);
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "/{id}/results/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public final HttpEntity<MatchResource> addPlayerResult(@PathVariable("id") Long id, @RequestBody PlayerResultCreateDto rDto) throws Exception {
    
        log.debug("rest: addPlayerResult(" + String.valueOf(id) + ", result = " + rDto.toString() + ")");
        try {
            
            matchFacade.addPlayerResult(id, rDto);
        } catch (Throwable ex) {
            log.error("cannot add result " + rDto.toString() + " to match " + String.valueOf(id));
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
        
        MatchResource resource = matchResourceAssembler.toResource(matchFacade.findMatchById(id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}/results/{rid}", method = RequestMethod.DELETE/*, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public final HttpEntity<MatchResource> deletePlayerResult(@PathVariable("id") Long id, @PathVariable("rid") Long rid) throws Exception {
    
        log.debug("rest: removePlayerResult(" + String.valueOf(id) + ", result id = " + String.valueOf(rid) + ")");
        try {
            matchFacade.removePlayerResult(id, rid);
        } catch (Throwable ex) {
            log.error("cannot delete result " + String.valueOf(rid));
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
        
        MatchResource resource = matchResourceAssembler.toResource(matchFacade.findMatchById(id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    
}
