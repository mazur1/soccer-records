/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

//import org.hibernate.service.spi.FacadeException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;

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
    
    //@Autowired
    @InjectMocks
    private PlayerResultFacadeImpl playerResultFacade;
    
    @Mock
    private TeamService teamService;
    @Mock
    private MatchService matchService;
    @Mock
    private PlayerService playerService;
           
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
    private PlayerResult pr1;
    
    private Team t1;
    private Team t2;
    private Player p1;
    private Player p2;
    private Match m1;
    
    @BeforeTest 
    public void preparePlayerResults() {
        t1 = new Team();
        t1.setId(1L);
        t1.setName("A");
        //teamDao.create(t1);  
        t2 = new Team();
        t2.setId(2L);
        t2.setName("H");
        //teamDao.create(t2);
        p1 = new Player();
        p1.setId(1L);
        p1.setName("Ján");
        p1.setAge(22);
        p1.setCaptian(false);
        p1.setSurname("Suchý");
        p1.setPost(PlayerPost.GOLMAN);
        p1.setTeam(t1);
        //playerDao.create(p1);
        p2 = new Player();
        p2.setId(2L);
        p2.setName("Igor");
        p2.setAge(21);
        p2.setCaptian(false);
        p2.setSurname("Vysoký");
        p2.setPost(PlayerPost.GOLMAN);
        p2.setTeam(t2);
        //playerDao.create(p2);
        m1 = new Match();
        m1.setId(1L);
        m1.setTeamAway(t1);
        m1.setTeamHome(t2);
        //matchDao.create(m1);
        //matchDao.create(m2);
        pr1 = new PlayerResult();
        pr1.setId(1L);
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        //playerResultDao.create(pr1);
    }    
    @Test
    public void createPlayerResult() {
        Mockito.when(mappingService.mapTo(pr1Dto, PlayerResult.class)).thenReturn(pr1);
        playerResultFacade.createPlayerResult(pr1Dto);
        Mockito.verify(playerResultService).create(pr1);
    }
    
    @Test
    public void updatePlayerResult() {
        Mockito.when(mappingService.mapTo(pr1Dto, PlayerResult.class)).thenReturn(pr1);
        playerResultFacade.updatePlayerResult(pr1Dto);
        Mockito.verify(playerResultService).update(pr1);
    }
    
    @Test
    public void deletePlayerResult() {
        Mockito.when(playerResultService.findByID(pr1.getId())).thenReturn(pr1);
        playerResultFacade.deletePlayerResult(pr1.getId());
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
        Mockito.when(playerResultService.findByID(pr1.getId())).thenReturn(pr1);
        Mockito.when(mappingService.mapTo(pr1, PlayerResultDto.class)).thenReturn(pr1Dto);
        
        PlayerResultDto actual = playerResultFacade.findPlayerResultById(pr1.getId());
        Mockito.verify(playerResultService).findByID(pr1.getId());
        Mockito.verify(mappingService).mapTo(pr1, PlayerResultDto.class);
        
        Assert.assertEquals(actual, pr1Dto);
    }
    
}
