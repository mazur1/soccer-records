package soccer.records.restapi.controllers;


import soccer.records.dto.TeamDto;
import soccer.records.facade.TeamFacade;

import soccer.records.restapi.exceptions.InvalidRequestException;
import soccer.records.restapi.exceptions.ResourceNotFoundException;

import soccer.records.restapi.hateoas.TeamResource;
import soccer.records.restapi.hateoas.TeamResourceAssembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import soccer.records.dto.MatchDto;
import soccer.records.dto.TeamCreateDto;
import soccer.records.dto.TeamResultDto;
import soccer.records.facade.MatchFacade;

/**
 * SpringMVC controller for managing REST requests for the category resources. Conforms to HATEOAS principles.
 *
 */

@RestController
@ExposesResourceFor(TeamDto.class)
@RequestMapping("/teams")
public class TeamRestController {

    private final static Logger log = LoggerFactory.getLogger(TeamRestController.class);

    public TeamRestController() {

    }

    @Autowired
    private TeamFacade teamFacade;
    
    @Autowired
    private MatchFacade macthFacade;
    
    @Autowired
    private TeamResourceAssembler teamResourceAssembler;
    
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;

    /**
     * Produces list of all teams in JSON.
     *
     * @return list of teams
     */
    
    @ResponseBody 
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<TeamResource>> teams() {
        log.info("rest teams()");
        List<TeamDto> allTeams = teamFacade.findAllTeams();
        Resources<TeamResource> teamResources = new Resources<>(
                teamResourceAssembler.toResources(allTeams),
                linkTo(TeamRestController.class).withSelfRel(),
                linkTo(TeamRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(teamResources, HttpStatus.OK);
    }

    /**
     * Produces team detail.
     *
     * @param id team identifier
     * @return team detail
     * @throws ResourceNotFoundException if team not found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<TeamResource> getTeam(@PathVariable("id") long id) throws Exception {
        log.info("attempt to get team({})", id);
        TeamDto teamDTO = teamFacade.findTeamById(id);
        if (teamDTO == null) throw new ResourceNotFoundException("team " + id + " not found");
        TeamResource teamResource = teamResourceAssembler.toResource(teamDTO);
        teamResource.setMatchesHome(teamFacade.getMatchesHome(id));
        teamResource.setMatchesAway(teamFacade.getMatchesAway(id));

        TeamResultDto tt = macthFacade.getTeamResult(id);
        teamResource.setTies(tt.getTies());
        teamResource.setWins(tt.getWins());
        teamResource.setLooses(tt.getWins());
        
        return new ResponseEntity<>(teamResource, HttpStatus.OK);
    }
    
     /**
     * Delete one team by id 
     *
     * @param id identifier for team
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteTeam(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteTeam({})", id);
        try {
            teamFacade.deleteTeam(id);
        } catch (Exception ex) {
            log.debug("Cannot delete team with id {}", id);
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }
    
     /**
     * Create one team from json data
     *
     * 
     * @throws InvalidRequestException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<TeamResource> createProduct(@RequestBody @Valid TeamCreateDto teamCreateDto, BindingResult bindingResult) throws Exception {
        log.debug("rest createTeam()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        Long id = teamFacade.createTeam(teamCreateDto);
        TeamResource resource = teamResourceAssembler.toResource(teamFacade.findTeamById(id));
        
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }





}


