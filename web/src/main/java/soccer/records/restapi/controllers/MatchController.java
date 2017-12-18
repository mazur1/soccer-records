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
    
    @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<MatchResource>> getMatches() {
        
        log.debug("rest: getMatches()");
        List<MatchResource> resourceCollection = matchResourceAssembler.toResources(matchFacade.findAllMatches());
        
        Resources<MatchResource> matchResources = new Resources<>(resourceCollection,
                linkTo(MatchController.class).withSelfRel(),
                linkTo(MatchController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(matchResources, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<MatchResource> getMatch(@PathVariable("id") Long id) throws Exception {
        
        log.debug("rest: getMatch(" + String.valueOf(id) + ")");
        MatchDto matchDto = matchFacade.findMatchById(id);
        if (matchDto == null) 
            throw new ResourceNotFoundException("match " + id + " not found");
        
        MatchResource resource = matchResourceAssembler.toResource(matchDto);
        resource.setPlayerResults(matchFacade.getPlayerResults(id));
        
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
    
    @RequestMapping(value = "/{id}/results", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    
    @RequestMapping(value = "/{id}/results", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<MatchResource> deletePlayerResult(@PathVariable("id") Long id, @RequestBody Long resultId) throws Exception {
    
        log.debug("rest: removePlayerResult(" + String.valueOf(id) + ", result id = " + String.valueOf(resultId) + ")");
        try {
            matchFacade.removePlayerResult(id, resultId);
        } catch (Throwable ex) {
            log.error("cannot delete result " + String.valueOf(resultId));
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
