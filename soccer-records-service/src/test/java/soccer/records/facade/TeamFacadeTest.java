/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.TeamDto;
import soccer.records.entity.Team;
import soccer.records.services.BeanMappingService;
import soccer.records.services.TeamService;

/**
 * Facade tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock 
    private TeamService teamService;
    
    @Mock
    private BeanMappingService mappingService;
    
    @Inject
    @InjectMocks
    private TeamFacade teamFacade;
               
    @BeforeClass
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void resetMock() {
        Mockito.reset(teamService);
    }
    
    @Mock
    private TeamDto pr1Dto;
    @Mock
    private Team pr1;
 
    @Test
    public void createTeam() {
        Mockito.when(mappingService.mapTo(pr1Dto, Team.class)).thenReturn(pr1);
        teamFacade.createTeam(pr1Dto);
        Mockito.verify(teamService).create(pr1);
    }
    
    @Test
    public void updateTeam() {
        Mockito.when(mappingService.mapTo(pr1Dto, Team.class)).thenReturn(pr1);
        teamFacade.updateTeam(pr1Dto);
        Mockito.verify(teamService).update(pr1);
    }
    
    @Test
    public void deleteTeam() {
        //Mockito.when(mappingService.mapTo(pr1Dto, Team.class)).thenReturn(pr1);
        Mockito.when(teamService.findById(1L)).thenReturn(pr1);
        teamFacade.deleteTeam(1L);
        Mockito.verify(teamService).remove(pr1);
    }
    
    @Test
    public void findAllTeams() {
    
        List<Team> list = Arrays.asList(pr1);
        Mockito.when(teamService.findAll()).thenReturn(list);
        List<TeamDto> listDto = Arrays.asList(pr1Dto);
        Mockito.when(mappingService.mapTo(list, TeamDto.class)).thenReturn(listDto);
        
        List<TeamDto> actual = teamFacade.findAllTeams();
        Mockito.verify(teamService).findAll();
        Mockito.verify(mappingService).mapTo(list, TeamDto.class);
        
        Assert.assertEquals(actual, listDto);
    }
    
    @Test
    public void findTeamById() {
        Mockito.when(teamService.findById(1L)).thenReturn(pr1);
        Mockito.when(mappingService.mapTo(pr1, TeamDto.class)).thenReturn(pr1Dto);
        
        TeamDto actual = teamFacade.findTeamById(1L);
        Mockito.verify(teamService).findById(1L);
        Mockito.verify(mappingService).mapTo(pr1, TeamDto.class);
        
        Assert.assertEquals(actual, pr1Dto);
    }
}
