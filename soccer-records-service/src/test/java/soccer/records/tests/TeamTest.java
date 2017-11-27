/**
 *
 * @author Filip Sutovsky, Michaela Bocanova
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

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamTest extends AbstractTestNGSpringContextTests {
   
    @Mock
    private TeamDao teamDao;
    
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
    }        
       
    /**
     * Creates a new team
     */
    @Test
    public void createTeam() {        
        //ArgumentCaptor<Team> arg = ArgumentCaptor.forClass(Team.class);
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 0);
        
        teamService.create(t1);
        Mockito.verify(teamDao).create(t1);
        
        //List<Team> rows2 = teamService.findAll();
        //Assert.assertEquals(rows2.size(), 1);
    }
    
    /**
     * Updates a team
     */
    @Test
    public void updateTeam() {
        
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        t1.setName("lala");
        teamService.update(t1);
        ArgumentCaptor<Team> arg = ArgumentCaptor.forClass(Team.class);
        verify(teamDao).update(t1);
        //Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(pr1));
        
        //List<Team> rows2 = teamService.findAll();
        //Assert.assertEquals(rows2.size(), 2);
    }
    
    /**
     * Deletes a team
     */
    @Test
    public void deleteTeam() {
        
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        teamService.remove(t1);
        ArgumentCaptor<Team> arg = ArgumentCaptor.forClass(Team.class);
        Mockito.verify(teamDao).delete(arg.capture());
        Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(t1));
        
        //List<Team> rows2 = teamService.findAll();
        //Assert.assertEquals(rows2.size(), 1);     
    }
    
    /**
     * Finds a team by its name
     */
    @Test
    public void findByName() {
 
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByName(t1.getName())).thenReturn(t1);
        Team actual = teamService.findByName(t1.getName());
        
        Assert.assertEquals(actual, t1);   
        
        verify(teamDao).findByName(t1.getName());
    }
    
    @Test
    public void findByNameNone() {
 
        //List<Team> rows = teamService.findAll();
        //Assert.assertEquals(rows.size(), 2);
        
        when(teamDao.findByName("lala")).thenReturn(null);
        Assert.assertNull(teamService.findByName("lala"));
        
        verify(teamDao).findByName("lala");      
    }
    
    @Test
    public void findByIdTeam() {
        
        when(teamDao.findById(t1.getId())).thenReturn(t1);
        Team actual = teamService.findById(t1.getId());
        Assert.assertEquals(actual, t1);
        
        verify(teamDao).findById(t1.getId());
    }
    
    @Test
    public void findByIdTeamNone() {
        
        Mockito.when(teamDao.findById(123L)).thenReturn(null);
        Assert.assertNull(teamService.findById(123L));
        
        verify(teamDao).findById(123L);
    }
    
    /**
     * Retrieves all teams
     */
    @Test
    public void findAllTeams() {
                
        when(teamDao.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Team> actual = teamService.findAll();

        Assert.assertEquals(actual.size(), 2);   
        Assert.assertEquals(actual, Arrays.asList(t1, t2));  
        
        verify(teamDao).findAll();
    }
    
    @Test
    public void findAllTeamsNone() {
                
        when(teamDao.findAll()).thenReturn(new ArrayList<>());

        List<Team> actual = teamService.findAll();

        Assert.assertEquals(actual.size(), 0);     
        
        verify(teamDao).findAll();
    }
    
}

/*@ContextConfiguration(classes = PersistenceAppContext.class)
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
}*/
