/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.TeamCreateDto;
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
    
    //@Inject
    @InjectMocks
    private TeamFacade teamFacade = new TeamFacadeImpl();
               
    @BeforeTest
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    //@BeforeMethod
    public void resetMock() {
        Mockito.reset(teamService);
    }
    
    @Mock
    private TeamDto t1Dto;
    
    @Mock
    private TeamCreateDto tCreate1Dto;
    
    @Mock
    private Team t1;
 
    @Test
    public void createTeam() {
        when(mappingService.mapTo(tCreate1Dto, Team.class)).thenReturn(t1);
        
        teamFacade.createTeam(tCreate1Dto);
        Mockito.verify(teamService).create(t1);
    }
    
    @Test
    public void updateTeam() {
        Mockito.when(mappingService.mapTo(t1Dto, Team.class)).thenReturn(t1);
        teamFacade.updateTeam(t1Dto);
        Mockito.verify(teamService).update(t1);
    }
    
    @Test
    public void deleteTeam() {
        //Mockito.when(mappingService.mapTo(t1Dto, Team.class)).thenReturn(t1);
        Mockito.when(teamService.findById(1L)).thenReturn(t1);
        teamFacade.deleteTeam(1L);
        Mockito.verify(teamService).remove(t1);
    }
    
    @Test
    public void findAllTeams() {
    
        List<Team> list = Arrays.asList(t1);
        Mockito.when(teamService.findAll()).thenReturn(list);
        List<TeamDto> listDto = Arrays.asList(t1Dto);
        Mockito.when(mappingService.mapTo(list, TeamDto.class)).thenReturn(listDto);
        
        List<TeamDto> actual = teamFacade.findAllTeams();
        Mockito.verify(teamService).findAll();
        Mockito.verify(mappingService).mapTo(list, TeamDto.class);
        
        Assert.assertEquals(actual, listDto);
    }
    
    @Test
    public void findTeamById() {
        Mockito.when(teamService.findById(1L)).thenReturn(t1);
        Mockito.when(mappingService.mapTo(t1, TeamDto.class)).thenReturn(t1Dto);
        
        TeamDto actual = teamFacade.findTeamById(1L);
        Mockito.verify(teamService, times(2)).findById(1L);
        Mockito.verify(mappingService).mapTo(t1, TeamDto.class);
        
        Assert.assertEquals(actual, t1Dto);
    }
}
