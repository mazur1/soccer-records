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
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;
import soccer.records.entity.Match;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;

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
    private BeanMappingService mappingService;
    
    @Inject
    @InjectMocks
    private MatchFacade matchFacade;
               
    @BeforeClass
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void resetMock() {
        Mockito.reset(matchService);
    }
    
    @Mock
    private MatchDto pr1Dto;
    @Mock
    private MatchCreateDto pr1CDto;
    @Mock
    private MatchEditDto pr1EDto;
    @Mock
    private Match pr1;
 
    //@Test
    public void createMatch() {
        Mockito.when(mappingService.mapTo(pr1CDto, Match.class)).thenReturn(pr1);
        matchFacade.createMatch(pr1CDto);
        Mockito.verify(matchService).create(pr1);
    }
    
    //@Test
    public void updateMatch() {        
        Mockito.when(mappingService.mapTo(pr1EDto, Match.class)).thenReturn(pr1);
        matchFacade.updateMatch(pr1EDto);
        Mockito.verify(matchService).update(pr1);
    }
    
    //@Test
    public void deleteMatch() {
        Mockito.when(matchService.findById(1L)).thenReturn(pr1);
        matchFacade.deleteMatch(1L);
        Mockito.verify(matchService).delete(pr1);
    }
    
    //@Test
    public void findAllMatchs() {
    
        List<Match> list = Arrays.asList(pr1);
        Mockito.when(matchService.findAll()).thenReturn(list);
        List<MatchDto> listDto = Arrays.asList(pr1Dto);
        Mockito.when(mappingService.mapTo(list, MatchDto.class)).thenReturn(listDto);
        
        List<MatchDto> actual = matchFacade.findAllMatches();
        Mockito.verify(matchService).findAll();
        Mockito.verify(mappingService).mapTo(list, MatchDto.class);
        
        Assert.assertEquals(actual, listDto);
    }
    
    //@Test
    public void findMatchById() {
        Mockito.when(matchService.findById(1L)).thenReturn(pr1);
        Mockito.when(mappingService.mapTo(pr1, MatchDto.class)).thenReturn(pr1Dto);
        
        MatchDto actual = matchFacade.findMatchById(1L);
        Mockito.verify(matchService).findById(1L);
        Mockito.verify(mappingService).mapTo(pr1, MatchDto.class);
        
        Assert.assertEquals(actual, pr1Dto);
    }
}
