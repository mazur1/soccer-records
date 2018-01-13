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

import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.PlayerResultEditDto;


import soccer.records.facade.PlayerResultFacade;

import soccer.records.restapi.exceptions.InvalidRequestException;
import soccer.records.restapi.exceptions.ResourceNotFoundException;
import soccer.records.restapi.exceptions.ServerProblemException;

import soccer.records.restapi.hateoas.PlayerResultResource;
import soccer.records.restapi.hateoas.PlayerResultResourceAssembler;

/**
 * Controller for managing REST requests for match resource.
 * @author Radim Vidlák
 */
@RestController
@RequestMapping("/results")
@ExposesResourceFor(PlayerResultDto.class)
public class PlayerResultRestController {
    
    private final static Logger log = LoggerFactory.getLogger(PlayerResultRestController.class);

    public PlayerResultRestController() {
    }

    @Autowired
    private PlayerResultFacade playerResultFacade;
    
    @Autowired
    private PlayerResultResourceAssembler PlayerResultResourceAssembler;
    
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;
    
    @RequestMapping(method = RequestMethod.GET)
    public final HttpEntity<Resources<PlayerResultResource>> getPlayerResults() {
        
        log.debug("rest: getPlayersResults()");
        List<PlayerResultDto> all = playerResultFacade.findAllPlayerResults();
        List<PlayerResultResource> resourceCollection = PlayerResultResourceAssembler.toResources(all);
        
        Resources<PlayerResultResource> playerResultResources = new Resources<>(resourceCollection,
                linkTo(PlayerResultRestController.class).withSelfRel(),
                linkTo(PlayerResultRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(playerResultResources, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final HttpEntity<PlayerResultResource> getPlayerResult(@PathVariable("id") Long id) throws Exception {
        
        log.debug("rest: getPlayerResult(" + String.valueOf(id) + ")");
        PlayerResultDto playerResultDto = playerResultFacade.findPlayerResultById(id);
        if (playerResultDto == null) 
            throw new ResourceNotFoundException("Player result with id: " + id + " not found");
        
        PlayerResultResource resource = PlayerResultResourceAssembler.toResource(playerResultDto);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deletePlayerResult(@PathVariable("id") Long id) throws Exception {
        
        log.debug("rest: deletePlayerResult(" + String.valueOf(id) + ")");
        try {
            playerResultFacade.deletePlayerResult(id);
        } catch (IllegalArgumentException ex) {
            log.error("PlayerResult " + id + " not found");
            throw new ResourceNotFoundException("PlayerResult " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete PlayerResult " + String.valueOf(id) + " :" + ex.getMessage());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<PlayerResultResource> createPlayerResult(@RequestBody @Valid PlayerResultCreateDto playerResultDto, BindingResult bindingResult) throws Exception {
        
        log.debug("rest: createPlayerResult(" + playerResultDto.toString() + ")");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        
        Long id;
        try {
            id = playerResultFacade.createPlayerResult(playerResultDto);
        } catch (Throwable ex) {
            log.error("cannot create PlayerResult " + playerResultDto.toString());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
        
        PlayerResultResource resource = PlayerResultResourceAssembler.toResource(playerResultFacade.findPlayerResultById(id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void editPlayerResult(@PathVariable("id") long id, @RequestBody @Valid PlayerResultEditDto dto, BindingResult bindingResult) throws Exception {
        log.debug("rest editPlayerResult({})", id);
        try {
            playerResultFacade.updatePlayerResult(dto);
        } catch (Exception ex) {
            log.debug("Cannot edit result with id {}", id);
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
    
}
