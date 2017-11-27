/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.tests;

import java.util.Arrays;
import java.util.List;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.PlayerResultService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeTest;

import soccer.records.config.ServiceConfiguration;
import soccer.records.dao.PlayerResultDao;

/**
 * Service tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerResultTest extends AbstractTestNGSpringContextTests {
   
    @Mock
    private PlayerResultDao playerResultDao;
    
    @Autowired
    @InjectMocks
    private PlayerResultService playerResultService;   
    
    /*@Mock
    private TeamDao teamDao;
    @Mock
    private PlayerDao playerDao;
    @Mock
    private MatchDao matchDao;*/
       
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void resetMock() {
        Mockito.reset(playerResultDao);
    }
    
    private Team t1;
    private Team t2;
    private Player p1;
    private Player p2;
    private Match m1;
    private Match m2;
    private PlayerResult pr1;
    private PlayerResult pr2;
    
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
        m2 = new Match();
        m2.setId(2L);
        m2.setTeamAway(t2);
        m2.setTeamHome(t1);
        //matchDao.create(m2);
        pr1 = new PlayerResult();
        pr1.setId(1L);
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        //playerResultDao.create(pr1);
        pr2 = new PlayerResult();
        pr2.setId(2L);
        pr2.setMatch(m1);
        pr2.setPlayer(p2);
        //playerResultDao.create(pr2);
    }        
       
    /**
     * Creates a new result 
     */
    @Test
    public void createPlayerResult() {        
        //ArgumentCaptor<PlayerResult> arg = ArgumentCaptor.forClass(PlayerResult.class);
        List<PlayerResult> rows = playerResultService.findAll();
        //Assert.assertEquals(rows.size(), 0);
        
        playerResultService.create(pr1);
        Mockito.verify(playerResultDao).create(pr1);
        
        List<PlayerResult> rows2 = playerResultService.findAll();
        //Assert.assertEquals(rows2.size(), 1);
    }
    
    /**
     * Updates a result
     */
    @Test
    public void updatePlayerResult() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        pr1.setGoalsScored(3);
        playerResultService.update(pr1);
        ArgumentCaptor<PlayerResult> arg = ArgumentCaptor.forClass(PlayerResult.class);
        verify(playerResultDao).update(pr1);
        //Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(pr1));
        
        List<PlayerResult> rows2 = playerResultService.findAll();
        //Assert.assertEquals(rows2.size(), 2);
    }
    
    /**
     * Deletes a result
     */
    //@Test
    public void deletePlayerResult() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
        playerResultService.delete(pr1);
        ArgumentCaptor<PlayerResult> arg = ArgumentCaptor.forClass(PlayerResult.class);
        Mockito.verify(playerResultDao, Mockito.atLeast(0)).delete(arg.capture());
        Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(pr1));
        
        List<PlayerResult> rows2 = playerResultService.findAll();
        Assert.assertEquals(rows2.size(), 1);     
    }
    
    /**
     * Finds results of a specific player
     */
    //@Test
    public void findByPlayer() {
 
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
        when(playerResultDao.findByPlayerID(p1.getId())).thenReturn(Arrays.asList(pr1));
        List<PlayerResult> actual = playerResultService.findByPlayer(p1);
        
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Arrays.asList(pr1));       
    }
    
    /**
     * Finds results for specific match
     */
    //@Test
    public void findByMatch() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
        when(playerResultDao.findByMatchID(m1.getId())).thenReturn(Arrays.asList(pr1, pr2));
        List<PlayerResult> actual = playerResultService.findByMatch(m1);
        
        Assert.assertEquals(actual.size(), 2);   
        Assert.assertEquals(actual, Arrays.asList(pr1, pr2));   
    }
    
    /**
     * Finds results of player of specific match
     */
    @Test
    public void FindByPlayerAndMatch() {
 
        List<PlayerResult> rows = playerResultService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(playerResultDao.findByBoth(p1.getId(), m1.getId())).thenReturn(pr1);
        PlayerResult actual = playerResultService.findByBoth(p1, m1);
        
        Assert.assertEquals(actual, pr1);
        
    }
    
    @Test
    public void FindByIdPlayerResult() {
        when(playerResultDao.findByID(pr1.getId())).thenReturn(pr1);
        PlayerResult actual = playerResultService.findByID(pr1.getId());
        Assert.assertEquals(actual, pr1);
    }
    
    /**
     * Retrieves all results
     */
    @Test
    public void FindAllPlayerResults() {
                
        when(playerResultDao.findAll()).thenReturn(Arrays.asList(pr1, pr2));

        List<PlayerResult> actual = playerResultService.findAll();

        Assert.assertEquals(actual.size(), 2);   
        Assert.assertEquals(actual, Arrays.asList(pr1, pr2));  
    }
}
