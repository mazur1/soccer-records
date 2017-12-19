package soccer.records.tests;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import soccer.records.dao.PlayerDao;
import soccer.records.entity.Location;

import soccer.records.entity.PlayerResult;
import soccer.records.entity.Player;
import soccer.records.entity.Team;
import soccer.records.entity.Match;
import soccer.records.enums.PlayerPost;
import soccer.records.services.PlayerService;


import soccer.records.config.ServiceConfiguration;

/**
 *
 * @author Radim Vidlák
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PlayerDao playerDao;

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
        p1.setCaptain(true);
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
        m1.setTeamAwayGoalsScored(1);
        m1.setTeamAwayGoalsScored(2);

        return m1;
    }

    private PlayerResult createPlayerResult(Match m, Player p, int goals) {

        PlayerResult pr = new PlayerResult(p, m);
        pr.setGoalsScored(goals);

        return pr;
    }

    @Test
    public void playerCreateTest() {

        Player pl = createPlayer();
        playerService.create(pl);
        Mockito.verify(playerDao).create(pl);

    }

    @Test
    public void playerUpdateTest() {

        Player p = createPlayer();
        
        playerService.create(p);
        //Mockito.verify(playerDao).create(p);

        p.setName("Matìj");
        p.setCity("Semerád");

        playerService.update(p);
        ArgumentCaptor<Player> arg = ArgumentCaptor.forClass(Player.class);
        verify(playerDao).update(p);

    }

    @Test
    public void playerDeleteTest() {

        Player p = createPlayer();
        playerService.create(p);
        //Mockito.verify(playerDao).create(p);        
        
        playerService.remove(p);
        ArgumentCaptor<Player> arg = ArgumentCaptor.forClass(Player.class);
        Mockito.verify(playerDao, Mockito.atLeast(0)).delete(arg.capture());
        Assert.assertTrue(!arg.getAllValues().isEmpty() && arg.getValue().equals(p));
        
    }

}
