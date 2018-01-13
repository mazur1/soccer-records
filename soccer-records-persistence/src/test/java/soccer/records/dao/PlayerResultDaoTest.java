/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 * Dao tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerResultDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private PlayerResultDao playerResultDao;
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private MatchDao matchDao;
    
    private PlayerResult pr1, pr2;
    private Match m1;
    private Team t1,t2;
    private Player p1, p2;
    
    @BeforeMethod
    public void setUp() {
        
        t1 = new Team();
        t1.setName("A");
        teamDao.create(t1);
        t2 = new Team();
        t2.setName("H");
        teamDao.create(t2);
        
        p1 = new Player();
        p1.setName("Ján");
        p1.setAge(22);
        p1.setCaptain(false);
        p1.setSurname("Suchý");
        p1.setPost(PlayerPost.GOLMAN);
        p1.setTeam(t1);
        playerDao.create(p1);
        p2 = new Player();
        p2.setName("Igor");
        p2.setAge(21);
        p2.setCaptain(false);
        p2.setSurname("Vysoký");
        p2.setPost(PlayerPost.GOLMAN);
        p2.setTeam(t2);
        playerDao.create(p2);
        
        m1 = new Match();
        m1.setTeamAway(t1);
        m1.setTeamHome(t2);
        matchDao.create(m1);
        
        pr1 = new PlayerResult();
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        playerResultDao.create(pr1);
        pr2 = new PlayerResult();
        pr2.setMatch(m1);
        pr2.setPlayer(p2);
        playerResultDao.create(pr2);
    }
    
    @Test
    public void findByPlayer() {
        List<PlayerResult> actual = playerResultDao.findByPlayerID(pr1.getPlayer().getId());
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), pr1);
    }
    
    @Test
    public void findByMatch() {
        List<PlayerResult> actual = playerResultDao.findByMatchID(pr1.getMatch().getId());
        Assert.assertEquals(actual.size(), 2);
        //Assert.assertEquals(actual.get(0), pr1);
    }
    
    @Test
    public void findByPlayerMatch() {
        PlayerResult actual = playerResultDao.findByBoth(pr1.getPlayer().getId(), pr1.getMatch().getId());
        Assert.assertEquals(actual, pr1);
    }
    
    @Test
    public void findById() {
        PlayerResult actual = playerResultDao.findById(pr1.getId());
        Assert.assertEquals(actual, pr1);
    }
    
    @Test
    public void findAll() {
        List<PlayerResult> actual = playerResultDao.findAll();
        Assert.assertEquals(actual.size(), 2);
    }
    
    @Test(expectedExceptions = DataAccessExceptions.class)
    public void createWithEmptyPlayer() {
        PlayerResult pr = new PlayerResult();
        pr.setGoalsScored(0);
        pr.setMatch(m1);
        playerResultDao.create(pr);
    }
    
    @Test(expectedExceptions = DataAccessExceptions.class)
    public void createWithEmptyMatch() {
        PlayerResult pr = new PlayerResult();
        pr.setGoalsScored(0);
        pr.setPlayer(p1);
        playerResultDao.create(pr);
    }
}
