/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

//import org.hibernate.service.spi.FacadeException;
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
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.PlayerResultEditDto;
import soccer.records.dto.TeamDto;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;

/**
 * Facade tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerResultFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private PlayerResultService playerResultService;
    @Mock
    private MatchService matchService;
    @Mock
    private PlayerService playerService;
        
    @Mock
    private BeanMappingService mappingService;
    
    //@Inject
    @InjectMocks
    private PlayerResultFacade playerResultFacade = new PlayerResultFacadeImpl();
                   
    @BeforeTest
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    //@BeforeMethod
    public void resetMock() {
        Mockito.reset(playerResultService);
    }
    
    @Mock
    private PlayerResultDto pr1Dto;

    @Mock
    private PlayerResultCreateDto pr1CreateDto;

    @Mock
    private PlayerResultEditDto pr1EditDto;
    
    @Mock
    private PlayerResult pr1;
    @Mock
    private TeamDto t1Dto;
    private TeamDto t2Dto;
    @Mock
    private Player p1;
    private PlayerDto p2Dto;
    @Mock
    private Match m1;
       
    
    @Test
    public void createPlayerResult() {
        when(mappingService.mapTo(pr1CreateDto, PlayerResult.class)).thenReturn(pr1);
        when(matchService.findById(pr1CreateDto.getMatchId())).thenReturn(m1);
        when(playerService.findById(pr1CreateDto.getPlayerId())).thenReturn(p1);
        
        playerResultFacade.createPlayerResult(pr1CreateDto);
        Mockito.verify(playerResultService).create(pr1);
    }
    
    @Test
    public void updatePlayerResult() {
        Mockito.when(mappingService.mapTo(pr1EditDto, PlayerResult.class)).thenReturn(pr1);
        playerResultFacade.updatePlayerResult(pr1EditDto);
        Mockito.verify(playerResultService).update(pr1);
    }
    
    @Test
    public void deletePlayerResult() {
        //Mockito.when(mappingService.mapTo(pr1Dto, PlayerResult.class)).thenReturn(pr1);
        Mockito.when(playerResultService.findById(1L)).thenReturn(pr1);
        playerResultFacade.deletePlayerResult(1L);
        Mockito.verify(playerResultService).delete(pr1);
    }
    
    @Test
    public void findAllPlayerResults() {
    
        List<PlayerResult> list = Arrays.asList(pr1);
        Mockito.when(playerResultService.findAll()).thenReturn(list);
        List<PlayerResultDto> listDto = Arrays.asList(pr1Dto);
        Mockito.when(mappingService.mapTo(list, PlayerResultDto.class)).thenReturn(listDto);
        
        List<PlayerResultDto> actual = playerResultFacade.findAllPlayerResults();
        Mockito.verify(playerResultService).findAll();
        Mockito.verify(mappingService).mapTo(list, PlayerResultDto.class);
        
        Assert.assertEquals(actual, listDto);
    }
    
    @Test
    public void findPlayerResultById() {
        Mockito.when(playerResultService.findById(1L)).thenReturn(pr1);
        Mockito.when(mappingService.mapTo(pr1, PlayerResultDto.class)).thenReturn(pr1Dto);
        
        PlayerResultDto actual = playerResultFacade.findPlayerResultById(1L);
        Mockito.verify(playerResultService, times(2)).findById(1L);
        Mockito.verify(mappingService).mapTo(pr1, PlayerResultDto.class);
        
        Assert.assertEquals(actual, pr1Dto);
    }
    
}
