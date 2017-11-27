/**
 *
 * @author Filip Sutovsky
 */
package soccer.records.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.service.spi.ServiceException;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dao.PlayerResultDao;
import soccer.records.dao.TeamDao;
import soccer.records.entity.Match;

import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.MatchService;
import soccer.records.services.MatchServiceImpl;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.PlayerServiceImpl;
import soccer.records.services.TeamService;
import soccer.records.services.TeamServiceImpl;

/*@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamTest extends AbstractTestNGSpringContextTests {
   
    @Mock
    private TeamDao playerResultDao;
    
    @Autowired
    @InjectMocks
    private TeamService teamService;   
           
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void resetMock() {
        Mockito.reset(teamDao);
    }
    
    private Team t1;
    private Team t2;
    private Player p1;
    private Player p2;
    private Match m1;
    private Match m2;
    private Team pr1;
    private Team pr2;
    
    @BeforeTest 
    public void prepareTeams() {
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
        pr1 = new Team();
        pr1.setId(1L);
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        //teamDao.create(pr1);
        pr2 = new Team();
        pr2.setId(2L);
        pr2.setMatch(m1);
        pr2.setPlayer(p2);
        //teamDao.create(pr2);
    }        
       
    /**
     * Creates a new result 
     */
    /*@Test
    public void createTeam() {        
        //ArgumentCaptor<Team> arg = ArgumentCaptor.forClass(Team.class);
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 0);
        
        teamService.create(pr1);
        Mockito.verify(teamDao).create(pr1);
        
        //List<Team> rows2 = teamService.findAll();
        //Assert.assertEquals(rows2.size(), 1);
    }
    
    /**
     * Updates a result
     */
    /*@Test
    public void updateTeam() {
        
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        pr1.setGoalsScored(3);
        teamService.update(pr1);
        ArgumentCaptor<Team> arg = ArgumentCaptor.forClass(Team.class);
        verify(teamDao).update(pr1);
        //Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(pr1));
        
        //List<Team> rows2 = teamService.findAll();
        //Assert.assertEquals(rows2.size(), 2);
    }
    
    /**
     * Deletes a result
     */
    /*@Test
    public void deleteTeam() {
        
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        teamService.delete(pr1);
        ArgumentCaptor<Team> arg = ArgumentCaptor.forClass(Team.class);
        Mockito.verify(teamDao, Mockito.atLeast(0)).delete(arg.capture());
        Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(pr1));
        
        //List<Team> rows2 = teamService.findAll();
        //Assert.assertEquals(rows2.size(), 1);     
    }
    
    /**
     * Finds results of a specific player
     */
    /*@Test
    public void findByPlayer() {
 
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByPlayerID(p1.getId())).thenReturn(Arrays.asList(pr1));
        List<Team> actual = teamService.findByPlayer(p1);
        
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Arrays.asList(pr1));   
        
        verify(teamDao).findByPlayerID(p1.getId());
    }
    
    @Test
    public void findByPlayerNone() {
 
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByPlayerID(123L)).thenReturn(null);//empptu list?
        Player p = new Player();
        p.setId(123L);
        Assert.assertNull(teamService.findByPlayer(p));
        
        verify(teamDao).findByPlayerID(123L);      
    }
    
    /**
     * Finds results for specific match
     */
    /*@Test
    public void findByMatch() {
        
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByMatchID(m1.getId())).thenReturn(Arrays.asList(pr1, pr2));
        List<Team> actual = teamService.findByMatch(m1);
        
        Assert.assertEquals(actual.size(), 2);   
        Assert.assertEquals(actual, Arrays.asList(pr1, pr2));   
        
        verify(teamDao).findByMatchID(m1.getId());
    }
    
    @Test
    public void findByMatchNone() {
        
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByMatchID(123L)).thenReturn(null);
        Match m = new Match();
        m.setId(123L);
        Assert.assertNull(teamService.findByMatch(m));
        
        verify(teamDao).findByMatchID(123L);
    }
    
    /**
     * Finds results of player of specific match
     */
    /*@Test
    public void findByPlayerAndMatch() {
 
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByBoth(p1.getId(), m1.getId())).thenReturn(pr1);
        Team actual = teamService.findByBoth(p1, m1);
        
        Assert.assertEquals(actual, pr1);
        verify(teamDao).findByBoth(p1.getId(), m1.getId());
    }
    
    @Test
    public void findByPlayerAndMatchNone() {
 
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByBoth(p1.getId(), 123L)).thenReturn(null);
        Match m =new Match();
        m.setId(123L);
        Assert.assertNull(teamService.findByBoth(p1, m));
        
        verify(teamDao).findByBoth(p1.getId(), 123L);
        
    }
    
    @Test
    public void findByIdTeam() {
        when(teamDao.findByID(pr1.getId())).thenReturn(pr1);
        Team actual = teamService.findByID(pr1.getId());
        Assert.assertEquals(actual, pr1);
        
        verify(teamDao).findByID(pr1.getId());
    }
    
    @Test
    public void findByIdTeamNone() {
        Mockito.when(teamDao.findByID(123L)).thenReturn(null);
        Assert.assertNull(teamService.findByID(123L));
        
        verify(teamDao).findByID(123L);
    }
    
    /**
     * Retrieves all results
     */
    /*@Test
    public void findAllTeams() {
                
        when(teamDao.findAll()).thenReturn(Arrays.asList(pr1, pr2));

        List<Team> actual = teamService.findAll();

        Assert.assertEquals(actual.size(), 2);   
        Assert.assertEquals(actual, Arrays.asList(pr1, pr2));  
        
        verify(teamDao).findAll();
    }
    
    @Test
    public void findAllTeamsNone() {
                
        when(teamDao.findAll()).thenReturn(new ArrayList<>());

        List<Team> actual = teamService.findAll();

        Assert.assertEquals(actual.size(), 0);     
        
        verify(teamDao).findAll();
    }
    
    @Test
    public void changeGoalsValid() {
        try {
            teamService.changeGoals(pr1, 2);
        
        } catch(ServiceException e) {
            fail("service exception is not supposed to occure");
        }        
    }
    
    @Test
    public void changeGoalsInValid() {
        try {
            teamService.changeGoals(pr1, -2);
            fail("service exception is supposed to occure");
        } catch(ServiceException e) {  
            
        } 
    }
    
}*/

@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamTest extends AbstractTestNGSpringContextTests {

    private static final String TEST_TEAM_NAME = "testTeam";
    private static final String TEST_PLAYER_NAME = "testPlayer";
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Mock
    private MatchService matchService;

    @Mock
    private TeamService teamService;
    
    @Mock
    private PlayerService playerService;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }   
    
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
        testTeam.getTeamPlayers().add(testPlayer);
        playerService.create(testPlayer);
        
        List<Team> rows = teamService.findAll();
        Assert.assertEquals(rows.size(), 1);
        Assert.assertEquals(testTeam, rows.get(0));
        
        Assert.assertEquals(rows.get(0).getTeamPlayers().size(), 1);
        Assert.assertEquals(rows.get(0).getTeamPlayers().get(0), testPlayer);
        
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
        
        testTeam1.getMatchesHome().add(match1);
        testTeam1.getMatchesHome().add(match2);
        testTeam1.getMatchesAway().add(match3);
        
        testTeam2.getMatchesAway().add(match1);
        testTeam2.getMatchesAway().add(match2);
        testTeam2.getMatchesHome().add(match3);
        
        List<Team> teamsRows = teamService.findAll();
        Assert.assertEquals(teamsRows.size(), 2);
        
        List<Match> matchesRows = matchService.findAll();
        Assert.assertEquals(matchesRows.size(), 3);
        
        Assert.assertEquals(teamsRows.get(0).getMatchesHome().size(), 2);
        Assert.assertEquals(teamsRows.get(0).getMatchesAway().size(), 1);
        
        Assert.assertEquals(teamsRows.get(1).getMatchesHome().size(), 1);
        Assert.assertEquals(teamsRows.get(1).getMatchesAway().size(), 2);
        
        em.getTransaction().commit();
        em.close();
    }
}
