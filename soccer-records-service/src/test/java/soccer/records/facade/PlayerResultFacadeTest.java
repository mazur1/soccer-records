/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

//import org.hibernate.service.spi.FacadeException;
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
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.PlayerResultEditDto;
import soccer.records.dto.TeamDto;
import soccer.records.entity.PlayerResult;
import soccer.records.enums.PlayerPost;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerResultService;

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
    private BeanMappingService mappingService;
    
    @Inject
    @InjectMocks
    private PlayerResultFacade playerResultFacade;
                   
    @BeforeClass
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
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
    
    private TeamDto t1Dto;
    private TeamDto t2Dto;
    private PlayerDto p1Dto;
    private PlayerDto p2Dto;
    private MatchDto m1Dto;
    
    @BeforeMethod 
    public void preparePlayerResults() {
        
        t1Dto = new TeamDto();
        t1Dto.setId(1L);
        t1Dto.setName("A");
        //teamFacade.create(t1Dto);  
        
        t2Dto = new TeamDto();
        t2Dto.setId(2L);
        t2Dto.setName("H");
        //teamService.create(t2Dto);
        
        p1Dto = new PlayerDto();
        p1Dto.setId(1L);
        p1Dto.setName("Ján");
        p1Dto.setAge(22);
        p1Dto.setIsCaptain(false);
        p1Dto.setSurname("Suchý");
        p1Dto.setPost(PlayerPost.GOLMAN);
        p1Dto.setTeam(t1Dto);
        //playerDao.create(p1);
        
        p2Dto = new PlayerDto();
        p2Dto.setId(2L);
        p2Dto.setName("Igor");
        p2Dto.setAge(21);
        p2Dto.setIsCaptain(false);
        p2Dto.setSurname("Vysoký");
        p2Dto.setPost(PlayerPost.GOLMAN);
        p2Dto.setTeam(t2Dto);
        //playerDao.create(p2);
        
        m1Dto = new MatchDto();
        m1Dto.setId(1L);
        m1Dto.setTeamAwayId(1L);
        m1Dto.setTeamHomeId(2L);
        //matchDao.create(m1);
        
        pr1Dto = new PlayerResultDto();
        pr1Dto.setId(1L);
        pr1Dto.setMatch(m1Dto);
        pr1Dto.setPlayer(p1Dto);
        pr1Dto.setGoalsScored(3);
        //playerResultDao.create(pr1);
        
        pr1CreateDto = new PlayerResultCreateDto(p1Dto, m1Dto);
        
        pr1EditDto = new PlayerResultEditDto(p1Dto, m1Dto);
        //pr1EditDto.setGoalsScored(3);
        
    }    
    @Test
    public void createPlayerResult() {
        Mockito.when(mappingService.mapTo(pr1CreateDto, PlayerResult.class)).thenReturn(pr1);
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
        Mockito.verify(playerResultService).findById(1L);
        Mockito.verify(mappingService).mapTo(pr1, PlayerResultDto.class);
        
        Assert.assertEquals(actual, pr1Dto);
    }
    
}
