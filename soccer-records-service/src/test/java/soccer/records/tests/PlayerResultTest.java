/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.tests;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import soccer.records.PersistenceAppContext;
import soccer.records.dao.PlayerResultDao;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultServiceImpl;
import soccer.records.services.TeamServiceImpl;
import soccer.records.services.MatchServiceImpl;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.PlayerServiceImpl;
import soccer.records.services.TeamService;
import org.hibernate.service.spi.ServiceException;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import soccer.records.config.ServiceConfiguration;

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
    
    @Mock
    private TeamService teamService;
    @Mock
    private MatchService matchService;
    @Mock
    private PlayerService playerService;
       
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Team t1;
    @BeforeMethod
    public void createTeam1() {
        t1 = new Team();
        t1.setName("A");
        teamService.create(t1);        
    }
    private Team t2;
    @BeforeMethod
    public void createTeam2() {
        t2 = new Team();
        t2.setName("H");
        teamService.create(t2);
    }
    private Player p1;
    @BeforeMethod
    public void createPlayer1() {
        p1 = new Player();
        p1.setName("Ján");
        p1.setAge(22);
        p1.setCaptian(false);
        p1.setSurname("Suchý");
        p1.setPost(PlayerPost.GOLMAN);
        p1.setTeamId(t1.getId());
        playerService.create(p1);
    }
    private Player p2;
    @BeforeMethod
    public void createPlayer2() {
        p2 = new Player();
        p2.setName("Igor");
        p2.setAge(21);
        p2.setCaptian(false);
        p2.setSurname("Vysoký");
        p2.setPost(PlayerPost.GOLMAN);
        p2.setTeamId(t2.getId());
        playerService.create(p2);
    }
    private Match m1;
    @BeforeMethod
    public void createMatch1() {
        m1 = new Match();
        m1.setTeamAway(t1);
        m1.setTeamHome(t2);
        matchService.create(m1);
    }
    private PlayerResult pr1;
    @BeforeMethod
    public void createPlayerResult1() {
        pr1 = new PlayerResult();
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        playerResultService.create(pr1);
    }
    private PlayerResult pr2;
    @BeforeMethod
    public void createPlayerResult2() {
        pr2 = new PlayerResult();
        pr2.setMatch(m1);
        pr2.setPlayer(p2);
        playerResultService.create(pr2);
    }        
       
    /**
     * Creates a new result 
     */
    //@Test
    public void createPlayerResult() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);

    }
    
    /**
     * Updates a result
     */
    //@Test
    public void updatePlayerResult() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        pr1.setGoalsScored(3);
        playerResultService.update(pr1);
        
        List<PlayerResult> rows2 = playerResultService.findAll();
        Assert.assertEquals(rows2.size(), 2);
        Assert.assertEquals(playerResultService.findByID(pr1.getId()), pr1);
    }
    
    /**
     * Deletes a result
     */
    //@Test
    public void deletePlayerResult() {
           
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
        playerResultService.delete(pr1);
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
        
        List<PlayerResult> rows2 = playerResultService.findByPlayer(pr1.getPlayer());
        Assert.assertEquals(rows2.size(), 1);
        Assert.assertEquals(rows2.get(0), pr1);
       
    }
    
    /**
     * Finds results for specific match
     */
    //@Test
    public void findByMatch() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
        List<PlayerResult> rows2 = playerResultService.findByMatch(pr1.getMatch());
        Assert.assertEquals(rows2.size(), 2);
        
    }
    
    /**
     * Finds results of player of specific match
     */
    //@Test
    public void FindByPlayerAndMatch() {
 
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
        PlayerResult row2 = playerResultService.findByBoth(pr1.getPlayer(), pr1.getMatch());
        Assert.assertEquals(row2, pr1);
        
    }
    
    //@Test
    public void FindByIdPlayerResult() {
        PlayerResult actual = playerResultService.findByID(pr1.getId());
        Assert.assertEquals(actual, pr1);
    }
    
    /**
     * Retrieves all results
     */
    //@Test
    public void FindAllPlayerResults() {
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
        
    }
}
