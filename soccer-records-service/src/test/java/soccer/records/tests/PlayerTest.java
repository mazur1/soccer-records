package soccer.records.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.entity.Location;

import soccer.records.entity.PlayerResult;
import soccer.records.entity.Player;
import soccer.records.entity.Team;
import soccer.records.entity.Match;
import soccer.records.enums.PlayerPost;
import soccer.records.services.PlayerService;

import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 *
 * @author Radim Vidlák
 */
@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private Player playerResultDao;

    @Autowired
    @InjectMocks
    private PlayerService playerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Player createPlayer() {
        Player p1 = new Player();
        p1.setName("Karel");
        p1.setSurname("Novák");
        p1.setAge(10);
        p1.setCaptian(true);
        p1.setCity("Jihlava");
        p1.setCountry("Czech Republic");
        p1.setPost(PlayerPost.GOLMAN);

        return p1;
    }

    private Team createTeam(String name) {
        Team t1 = new Team();
        t1.setName(name);

        return t1;
    }

    private Match createMatch(Team t1, Team t2) {

        Location l = new Location();
        l.setCity("Jihlava");
        l.setState("Czech republic");
        l.setStreet("ulice");
        l.setZip("586 01");

        Match m1 = new Match();
        m1.setLocation(l);
        m1.setTeamAway(t1);
        m1.setTeamHome(t2);
        m1.setTeamAwayGoalsScored(1, false);
        m1.setTeamAwayGoalsScored(2, false);

        return m1;
    }

    private PlayerResult createPlayerResult(Match m, Player p, int goals) {

        PlayerResult pr = new PlayerResult(p, m);
        pr.setGoalsScored(goals);

        return pr;
    }

    @Test(expectedExceptions = DataAccessExceptions.class)
    public void notNullTest() {
        playerService.create(null);
    }

    @Test
    public void playerCreateTest() {

        Player pl = createPlayer();

        playerService.create(pl);
        Assert.assertEquals(pl.getName(), playerService.findByName("Karel", "Novák").get(0).getName());

    }

    @Test
    public void playerUpdateTest() {

        Player p = createPlayer();
        playerService.create(p);

        p.setName("Matìj");
        p.setCity("Semerád");

        playerService.update(p);

        Assert.assertEquals(p.getName(), playerService.findByName("Matìj", "Semerád").get(0).getName());
        Assert.assertEquals(p.getSurname(), playerService.findByName("Matìj", "Semerád").get(0).getSurname());
    }

    @Test
    public void playerDeleteTest() {

        Player p = createPlayer();
        playerService.create(p);

        playerService.remove(p);
        Assert.assertEquals(0, playerService.findAll().size());

    }

}
