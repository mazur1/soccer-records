/**
 *
 * @author Filip Sutovsky
 */
package soccer.records.tests;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.dao.TeamDao;
import soccer.records.entity.Match;

import soccer.records.entity.Player;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.MatchServiceImpl;
import soccer.records.services.PlayerServiceImpl;
import soccer.records.services.TeamServiceImpl;

@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamTest extends AbstractTestNGSpringContextTests {

    private static final String TEST_TEAM_NAME = "testTeam";
    private static final String TEST_PLAYER_NAME = "testPlayer";
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Autowired
    private TeamServiceImpl teamService;
    
    @Autowired
    private PlayerServiceImpl playerService;
    
    @Autowired
    private MatchServiceImpl matchService;
    
    private Team createTeam(String name) {
        Team testTeam = new Team();
        testTeam.setName(name);
        return testTeam;
    }
    
    private Player createPlayer(String name, String surname, PlayerPost post, int age, boolean captain) {
        Player testPlayer = new Player();
        testPlayer.setName(name);
        testPlayer.setAge(age);
        testPlayer.setCaptian(false);
        testPlayer.setSurname(surname);
        testPlayer.setPost(post);
        return testPlayer;
    }
    
    @Test
    public void createTeamTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Team testTeam = createTeam(TEST_TEAM_NAME);
        teamService.create(testTeam);
        
        List<Team> rows = teamService.findAll();
        Assert.assertEquals(rows.size(), 1);
        Assert.assertEquals(testTeam, rows.get(0));
        
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createAndUpdateTeamTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Team testTeam = createTeam(TEST_TEAM_NAME);
        teamService.create(testTeam);
        
        List<Team> rows = teamService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        testTeam.setName("updatedName");
        teamService.update(testTeam);
        
        Assert.assertEquals(testTeam, teamService.findByName("updatedName"));
        
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createAndRemoveTeamTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Team testTeam = createTeam(TEST_TEAM_NAME);
        teamService.create(testTeam);
        
        List<Team> rows = teamService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        teamService.remove(testTeam);
        
        List<Team> rows2 = teamService.findAll();
        Assert.assertEquals(rows2.size(), 0);
        
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createAndAddPlayerTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Team testTeam = createTeam(TEST_TEAM_NAME);
        teamService.create(testTeam);
        Player testPlayer = createPlayer(TEST_PLAYER_NAME, "surname", PlayerPost.ATTACKER, 22, false);
        testPlayer.setTeam(testTeam);
        testTeam.teamPlayers().add(testPlayer);
        playerService.create(testPlayer);
        
        List<Team> rows = teamService.findAll();
        Assert.assertEquals(rows.size(), 1);
        Assert.assertEquals(testTeam, rows.get(0));
        
        Assert.assertEquals(rows.get(0).teamPlayers().size(), 1);
        Assert.assertEquals(rows.get(0).teamPlayers().get(0), testPlayer);
        
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createAndFindByNameTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Team testTeam = createTeam(TEST_TEAM_NAME);
        teamService.create(testTeam);
        
        List<Team> rows = teamService.findAll();
        Assert.assertEquals(rows.size(), 1);
        Assert.assertEquals(testTeam.getName(), teamService.findByName(TEST_TEAM_NAME).getName());
        
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createAndAddMatchesTest() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Team testTeam1 = createTeam(TEST_TEAM_NAME + '1');
        Team testTeam2 = createTeam(TEST_TEAM_NAME + '2');
        
        Match match1 = new Match();
        Match match2 = new Match();
        Match match3 = new Match();
        
        match1.setTeamHome(testTeam1);
        match1.setTeamAway(testTeam2);
        
        match2.setTeamHome(testTeam1);
        match2.setTeamAway(testTeam2);
        
        match3.setTeamHome(testTeam2);
        match3.setTeamAway(testTeam1);
        
        teamService.create(testTeam1);
        teamService.create(testTeam2);
        
        matchService.create(match1);
        matchService.create(match2);
        matchService.create(match3);
        
        testTeam1.matchesHome().add(match1);
        testTeam1.matchesHome().add(match2);
        testTeam1.matchesAway().add(match3);
        
        testTeam2.matchesAway().add(match1);
        testTeam2.matchesAway().add(match2);
        testTeam2.matchesHome().add(match3);
        
        List<Team> teamsRows = teamService.findAll();
        Assert.assertEquals(teamsRows.size(), 2);
        
        List<Match> matchesRows = matchService.findAll();
        Assert.assertEquals(matchesRows.size(), 3);
        
        Assert.assertEquals(teamsRows.get(0).matchesHome().size(), 2);
        Assert.assertEquals(teamsRows.get(0).matchesAway().size(), 1);
        
        Assert.assertEquals(teamsRows.get(1).matchesHome().size(), 1);
        Assert.assertEquals(teamsRows.get(1).matchesAway().size(), 2);
        
        em.getTransaction().commit();
        em.close();
    }
}
