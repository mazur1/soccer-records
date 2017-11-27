/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import javax.inject.Inject;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;

/**
 *
 * @author Michaela Bocanova
 */
public class PlayerResultDaoTest extends AbstractTestNGSpringContextTests {
    
    @Inject
    private PlayerResultDao playerResultDao;
    @Inject
    private PlayerDao playerDao;
    @Inject
    private TeamDao teamDao;
    @Inject
    private MatchDao matchDao;
    
    private PlayerResult pr1;
    
    @BeforeMethod
    public void setUp() {
        
        Team t1 = new Team();
        t1.setName("A");
        teamDao.create(t1);
        Team t2 = new Team();
        t2.setName("H");
        teamDao.create(t2);
        
        Player p1 = new Player();
        p1.setName("Ján");
        p1.setAge(22);
        p1.setCaptian(false);
        p1.setSurname("Suchý");
        p1.setPost(PlayerPost.GOLMAN);
        p1.setTeam(t1);
        playerDao.create(p1);
        Player p2 = new Player();
        p2.setName("Igor");
        p2.setAge(21);
        p2.setCaptian(false);
        p2.setSurname("Vysoký");
        p2.setPost(PlayerPost.GOLMAN);
        p2.setTeam(t2);
        playerDao.create(p2);
        
        Match m1 = new Match();
        m1.setTeamAway(t1);
        m1.setTeamHome(t2);
        matchDao.create(m1);
        
        PlayerResult pr1 = new PlayerResult();
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        playerResultDao.create(pr1);
    }
    
    @Test
    public void testNonExistingResultReturnsNull() {
        Assert.assertNull(playerResultDao.findByID(123l));
    }

    @Test
    public void testExistingResult() {
        PlayerResult actual = playerResultDao.findByID(pr1.getId());
        Assert.assertEquals(actual, pr1);
    }
    
}
