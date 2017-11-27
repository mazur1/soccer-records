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
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;
import soccer.records.services.PlayerResultService;

import soccer.records.config.ServiceConfiguration;
import soccer.records.dao.PlayerResultDao;
/**
 * Testing CRUD methods in PlayerResultService 
 * (and, by extension, PlayerResultDao)
 * 
 * @author Michaela Bocanova
 */

@Transactional
@ContextConfiguration(classes=ServiceConfiguration.class)
public class PlayerResultTest extends AbstractTestNGSpringContextTests {
   
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Mock
    private PlayerResultDao playerResultDao;
    
    @Autowired
    @InjectMocks
    private PlayerResultService playerResultService;   
    
    private PlayerResult playerResult;
        
    @BeforeClass
    public void setUp() throws ServiceException {      
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void init(){
        playerResult = new PlayerResult();
    }
    
    // helper methods with sample data
    private Player newPlayerA() {
        Player p = new Player();
        p.setName("Ján");
        p.setAge(22);
        p.setCaptian(false);
        p.setSurname("Suchý");
        p.setPost(PlayerPost.GOLMAN);
        
        return p;
    }
    private Player newPlayerB() {
        Player p = new Player();
        p.setName("Igor");
        p.setAge(21);
        p.setCaptian(false);
        p.setSurname("Vysoký");
        p.setPost(PlayerPost.GOLMAN);
        
        return p;
    }
    private Team newTeamA() {
        Team t = new Team();
        t.setName("A");
        
        return t;
    }    
    private Team newTeamB() {
        Team t = new Team();
        t.setName("H");
        
        return t;
    }
       
    /**
     * Creates a new result and checks if exists
     */
    @Test
    public void createAndFindPlayerResult() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
           
        Team tA = newTeamA();
        Team tB = newTeamB();
        em.persist(tA);
        em.persist(tB);
        
        Player pA = newPlayerA();
        pA.setTeam(tA);
        em.persist(pA);
        
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        em.persist(m);
        
        playerResult.setMatch(m);
        playerResult.setPlayer(pA);
        playerResult.setGoalsScored(2);       
        playerResultService.create(playerResult); 
        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);

        Assert.assertEquals(playerResult, rows.get(0));

        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Updates a result
     */
    @Test
    public void createAndUpdatePlayerResult() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
          
        Team tA = newTeamA();
        Team tB = newTeamB();

        Player pA =newPlayerA();
        pA.setTeam(tA);
        
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        
        playerResult.setMatch(m);
        playerResult.setPlayer(pA);       
        playerResult.setGoalsScored(2);
        
        playerResultService.create(playerResult);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        playerResult.setGoalsScored(3);
        
        playerResultService.update(playerResult);
        
        List<PlayerResult> rows2 = playerResultService.findAll();
        Assert.assertEquals(rows2.size(), 1);
        
        Assert.assertEquals(rows2.get(0), playerResult);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Deletes a result
     */
    @Test
    public void createAndDeletePlayerResult() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();

        Player pA =newPlayerA();
        pA.setTeam(tA);
  
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
       
        playerResult.setMatch(m);
        playerResult.setPlayer(pA);
        playerResult.setGoalsScored(2);   
        
        playerResultService.create(playerResult);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        playerResultService.delete(playerResult);
        List<PlayerResult> rows2 = playerResultService.findAll();
        Assert.assertEquals(rows2.size(), 0);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Finds results of a specific player
     */
    @Test
    public void createAndFindByPlayer() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();

        Player pA =newPlayerA();
        pA.setTeam(tA);
    
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
      
        playerResult.setMatch(m);
        playerResult.setPlayer(pA);
        playerResult.setGoalsScored(2);     
        
        playerResultService.create(playerResult);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        List<PlayerResult> rows2 = playerResultService.findByPlayer(playerResult.getPlayer());
        Assert.assertEquals(rows2.size(), 1);
        Assert.assertEquals(rows2.get(0), playerResult);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Finds results for specific match
     */
    @Test
    public void createAndFindByMatch() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();

        Player pA =newPlayerA();
        pA.setTeam(tA);
     
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        
        playerResult.setMatch(m);
        playerResult.setPlayer(pA);
        playerResult.setGoalsScored(2);   
        
        playerResultService.create(playerResult);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        List<PlayerResult> rows2 = playerResultService.findByMatch(playerResult.getMatch());
        Assert.assertEquals(rows2.size(), 1);
        Assert.assertEquals(rows2.get(0), playerResult);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Finds results of player of specific match
     */
    @Test
    public void createAndFindByPlayerAndMatch() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();

        Player pA =newPlayerA();
        pA.setTeam(tA);

        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
    
        playerResult.setMatch(m);
        playerResult.setPlayer(pA);
        playerResult.setGoalsScored(2);  
        playerResult.setGoalsScored(2);   
        
        playerResultService.create(playerResult);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        PlayerResult row2 = playerResultService.findByBoth(playerResult.getPlayer(), playerResult.getMatch());
        Assert.assertEquals(row2, playerResult);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /*@Test
    public void createAndFindByIdPlayerResult() {
 
    }*/
    
    /**
     * Retrieves all results
     */
    @Test
    public void createAndFindAllPlayerResults() {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();

        Player pA =newPlayerA();
        pA.setTeam(tA);

        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        
        Player pB =newPlayerB();
        pB.setTeam(tB);

        playerResult.setMatch(m);
        playerResult.setPlayer(pB);
        playerResult.setGoalsScored(2);   
        
        PlayerResult playerResult2 = new PlayerResult(pB, m);
        playerResult2.setGoalsScored(1);
        
        playerResultService.create(playerResult);
        playerResultService.create(playerResult2);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
                
        em.getTransaction().commit();
        em.close();
        
    }
}
