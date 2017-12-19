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
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;
import soccer.records.entity.Match;
import soccer.records.entity.Team;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.TeamService;

/**
 * Facade tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock 
    private MatchService matchService;
    @Mock 
    private TeamService teamService;
    
    @Mock
    private BeanMappingService mappingService;
    
    //@Inject
    @InjectMocks
    private MatchFacade matchFacade = new MatchFacadeImpl();
               
    @BeforeTest
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    //@BeforeMethod
    public void resetMock() {
        Mockito.reset(matchService);
    }
    
    @Mock
    private MatchDto m1Dto;
    @Mock
    private MatchCreateDto m1CDto;
    @Mock
    private MatchEditDto m1EDto;
    @Mock
    private Match m1;
    @Mock
    private Team t1;
    @Mock
    private Team t2;
 
    @Test
    public void createMatch() {
        when(mappingService.mapTo(m1CDto, Match.class)).thenReturn(m1);
        when(teamService.findById(m1CDto.getTeamHomeId())).thenReturn(t1);
        when(teamService.findById(m1CDto.getTeamAwayId())).thenReturn(t1);
        
        matchFacade.createMatch(m1CDto);
        Mockito.verify(matchService).create(m1);
    }
    
    @Test
    public void updateMatch() {        
        Mockito.when(mappingService.mapTo(m1EDto, Match.class)).thenReturn(m1);
        matchFacade.updateMatch(m1EDto);
        Mockito.verify(matchService).update(m1);
    }
    
    @Test
    public void deleteMatch() {
        Mockito.when(matchService.findById(1L)).thenReturn(m1);
        matchFacade.deleteMatch(1L);
        Mockito.verify(matchService).delete(m1);
    }
    
    @Test
    public void findAllMatchs() {
    
        List<Match> list = Arrays.asList(m1);
        Mockito.when(matchService.findAll()).thenReturn(list);
        List<MatchDto> listDto = Arrays.asList(m1Dto);
        Mockito.when(mappingService.mapTo(list, MatchDto.class)).thenReturn(listDto);
        
        List<MatchDto> actual = matchFacade.findAllMatches();
        Mockito.verify(matchService).findAll();
        Mockito.verify(mappingService).mapTo(list, MatchDto.class);
        
        Assert.assertEquals(actual, listDto);
    }
    
    @Test
    public void findMatchById() {
        Mockito.when(matchService.findById(1L)).thenReturn(m1);
        Mockito.when(mappingService.mapTo(m1, MatchDto.class)).thenReturn(m1Dto);
        
        MatchDto actual = matchFacade.findMatchById(1L);
        Mockito.verify(matchService, times(2)).findById(1L);
        Mockito.verify(mappingService).mapTo(m1, MatchDto.class);
        
        Assert.assertEquals(actual, m1Dto);
    }
}
