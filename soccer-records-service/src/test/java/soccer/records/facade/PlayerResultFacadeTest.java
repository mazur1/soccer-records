/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

//import org.hibernate.service.spi.FacadeException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;
import soccer.records.entity.PlayerResult;
import soccer.records.enums.PlayerPost;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerResultService;

/**
 *
 * @author Michaela Bocanova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerResultFacadeTest extends AbstractTestNGSpringContextTests {
        
    @Mock 
    private PlayerResultService playerResultService;
    
    @Mock
    private BeanMappingService mappingService;
    
    @Autowired
    @InjectMocks
    private PlayerResultFacade playerResultFacade;
    
    /*@Mock
    private TeamFacade teamFacade;
    @Mock
    private MatchFacade matchFacade;
    @Mock
    private PlayerFacade playerFacade;*/
    
    @Mock
    private PlayerResultDto playerResultDto;
    @Mock
    private PlayerResult playerResult;
       
    @BeforeClass
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createPlayerResult() {
        Mockito.when(mappingService.mapTo(playerResultDto, PlayerResult.class)).thenReturn(playerResult);
        playerResultFacade.createPlayerResult(new PlayerResultDto());
        Mockito.verify(playerResultService).create(playerResult);
    }
    
}
