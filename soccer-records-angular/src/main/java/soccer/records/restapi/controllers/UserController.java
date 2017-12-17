/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.controllers;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import soccer.records.dto.AppUserAuthenticationDto;

import soccer.records.restapi.exceptions.ServerProblemException;
import soccer.records.restapi.exceptions.InvalidRequestException;

import soccer.records.dto.AppUserDto;
import soccer.records.facade.AppUserFacade;

/**
 *
 * @author Michaela Bocanova
 */
@RestController
@RequestMapping("/users")
@ExposesResourceFor(AppUserDto.class)
public class UserController {
    
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AppUserFacade userFacade;
    
    public UserController() {       
         
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void loginUser(@RequestBody @Valid AppUserAuthenticationDto userDto, BindingResult bindingResult) throws Exception {
        
        log.debug("rest: createUser(" + userDto.toString() + ")");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        
        try {        
            userFacade.authenticate(userDto);
        } catch (Throwable ex) {
            log.error("cannot create user " + userDto.toString());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
        
    }   
    
    /*@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void registerUser(@RequestBody @Valid AppUserDto userDto, BindingResult bindingResult) throws Exception {
        
        log.debug("rest: createUser(" + userDto.toString() + ")");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        
        try {
            userFacade.registerUser(userDto, userDto.getPasswordHash());
        } catch (Throwable ex) {
            log.error("cannot create user " + userDto.toString());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
    }*/
}
