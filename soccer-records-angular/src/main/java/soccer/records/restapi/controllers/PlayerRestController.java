package soccer.records.restapi.controllers;


import soccer.records.dto.PlayerDto;
import soccer.records.facade.PlayerFacade;

import soccer.records.restapi.exceptions.InvalidRequestException;
import soccer.records.restapi.exceptions.ResourceNotFoundException;

import soccer.records.restapi.hateoas.PlayerResource;
import soccer.records.restapi.hateoas.PlayerResourceAssembler;

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

/**
 * SpringMVC controller for managing REST requests for the category resources. Conforms to HATEOAS principles.
 *
 * @author Tomas Mazurek
 */

@RestController
@ExposesResourceFor(PlayerDto.class)
@RequestMapping("/players")
public class PlayerRestController {

    private final static Logger log = LoggerFactory.getLogger(PlayerRestController.class);

    public PlayerRestController() {

    }

    @Autowired
    private PlayerFacade playerFacade;
    
    @Autowired
    private PlayerResourceAssembler playerResourceAssembler;
    
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EntityLinks entityLinks;

    /**
     * Produces list of all players in JSON.
     *
     * @return list of players
     */
    // ../players
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<PlayerResource>> players() {
        log.debug("rest players()");
        List<PlayerDto> allPlayers = playerFacade.findAllPlayers();
        Resources<PlayerResource> playerResources = new Resources<>(
                playerResourceAssembler.toResources(allPlayers),
                linkTo(PlayerRestController.class).withSelfRel(),
                linkTo(PlayerRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(playerResources, HttpStatus.OK);
    }

    /**
     * Produces player detail.
     *
     * @param id player identifier
     * @return player detail
     * @throws Exception if player not found
     */
    // .. /players/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<PlayerResource> player(@PathVariable("id") long id) throws Exception {
        log.debug("rest player({})", id);
        PlayerDto playerDto = playerFacade.findPlayerById(id);
        if (playerDto == null) throw new ResourceNotFoundException("player " + id + " not found");
        PlayerResource playerResource = playerResourceAssembler.toResource(playerDto);
        return new HttpEntity<>(playerResource);
    }

    /**
     * Produces a list of products in the given category.
     *
     * @param id category identifier
     * @return list of products in the category
     */
    /*
    @RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
    public HttpEntity<Resources<ProductResource>> products(@PathVariable("id") long id) {
        log.debug("rest category/{}/products()", id);
        CategoryDTO categoryDTO = categoryFacade.getCategoryById(id);
        if (categoryDTO == null) throw new ResourceNotFoundException("category " + id + " not found");
        List<ProductDTO> products = productFacade.getProductsByCategory(categoryDTO.getName());
        List<ProductResource> resourceCollection = productResourceAssembler.toResources(products);
        Link selfLink = entityLinks.linkForSingleResource(CategoryDTO.class, id).slash("/products").withSelfRel();
        Resources<ProductResource> productsResources = new Resources<>(resourceCollection, selfLink);
        return new ResponseEntity<>(productsResources, HttpStatus.OK);
    }
    */

    /**
     * Creates a new team.
     *
     * @param categoryCreateDTO DTO object containing category name
     * @return newly created category
     * @throws Exception if something goes wrong
     */
    /*
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CategoryResource> createProduct(@RequestBody @Valid CategoryCreateDTO categoryCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("rest createCategory()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        Long id = categoryFacade.createCategory(categoryCreateDTO);
        CategoryResource resource = categoryResourceAssembler.toResource(categoryFacade.getCategoryById(id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    */

}


