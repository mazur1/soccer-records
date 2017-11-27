/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.entity.Location;
import soccer.records.entity.Match;
import soccer.records.entity.Team;
import soccer.records.services.MatchService;
import soccer.records.services.TeamService;

/**
 *
 * @author Tomas Mazurek
 */
@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MatchTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Mock
    private MatchService matchService;

    @Mock
    private TeamService teamService;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    private Team newTeam (String name) {
        Team homeTeam = new Team();
        homeTeam.setName(name);
        return homeTeam;
    }
    
    @Test
    public void createEmptyMatch() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Match match = new Match();
        
        Team homeTeam = newTeam("Barcelona");
        Team awayTeam = newTeam("RealMadrid");
        
        match.setTeamHome(homeTeam);
        match.setTeamAway(awayTeam);

        teamService.create(homeTeam);
        teamService.create(awayTeam);        
        matchService.create(match);

        Long matchId = match.getId();        
        Assert.assertEquals(matchId, matchService.findById(matchId).getId());
        
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createFilledMatch() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Match match = new Match();
        
        Team homeTeam = newTeam("Barcelona");
        Team awayTeam = newTeam("RealMadrid");
        
        Calendar cal = Calendar.getInstance();
        Date f = cal.getTime();
        System.out.print("time: ");
        System.out.println(f);
        
        Location location = new Location();
        location.setCity("Barcelona");
        location.setState("Spain");
        location.setStreet("Main");
        location.setName("Arena");
        location.setZip("6372");
        
        match.setTeamHome(homeTeam);
        match.setTeamAway(awayTeam);
        match.setTeamHomeGoalsScored(2, false);
        match.setTeamHomeGoalsScored(1, true);
        match.setTeamAwayGoalsScored(1, false);
        match.setTeamHomeGoalsScored(0, true);
        match.setLocation(location);
        match.setDateAndTime(cal.getTime());
        
        
        teamService.create(awayTeam);
        teamService.create(homeTeam);
        matchService.create(match);

        Long matchId = match.getId(); 
        Assert.assertEquals(matchService.findById(matchId), match);
        
        
        //get match by findAll()
        List<Match> findAllResult = matchService.findAll();
        Match findResult = findAllResult.get(0);
        
        Assert.assertEquals(findResult, match);
        Assert.assertEquals(findAllResult.size(), 1);
        Assert.assertEquals(findResult.getTeamHomeGoalsScored(false), findResult.getTeamAwayGoalsReceived(false));
        Assert.assertEquals(findResult.getTeamAwayGoalsScored(false), findResult.getTeamHomeGoalsReceived(false));
        
        em.getTransaction().commit();
        em.close();

    }
    
    @Test
    public void DeleteMatch() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Match match = new Match();
        
        Team homeTeam = newTeam("Barcelona");
        Team awayTeam = newTeam("RealMadrid");
        
        match.setTeamHome(homeTeam);
        match.setTeamAway(awayTeam);

        teamService.create(homeTeam);
        teamService.create(awayTeam);        
        matchService.create(match);
        
        matchService.delete(match);
              
        Assert.assertEquals(true, matchService.findAll().isEmpty());
        
        em.getTransaction().commit();
        em.close();

    }
    
    @Test
    public void UpdateMatch() {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Match match = new Match();
        Team homeTeam = newTeam("Barcelona");
        Team awayTeam = newTeam("RealMadrid");
        match.setTeamHome(homeTeam);
        match.setTeamAway(awayTeam);
        
        teamService.create(homeTeam);
        teamService.create(awayTeam); 
        matchService.create(match);
        Long matchId = match.getId();
        Team homeTeam2 = newTeam("Chelsea");
        match.setTeamHome(homeTeam2);     
        matchService.update(match);
        
        Assert.assertEquals("Chelsea", matchService.findById(matchId).getTeamHome().getName());
        Assert.assertEquals("RealMadrid", matchService.findById(matchId).getTeamAway().getName());
        
        em.getTransaction().commit();
        em.close();
        
        
    }
    
    @Test
    public void findByTeamTest() {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        //first match
        Match match = new Match();
        Team homeTeam = newTeam("Barcelona");
        Team awayTeam = newTeam("RealMadrid");
        match.setTeamHome(homeTeam);
        match.setTeamAway(awayTeam);
        
        teamService.create(homeTeam);
        teamService.create(awayTeam); 
        matchService.create(match);
        
        //second match
        Match match2 = new Match();
        Team homeTeam2 = newTeam("Chelsea");
        Team awayTeam2 = newTeam("Juventus");
        match2.setTeamHome(homeTeam2);
        match2.setTeamAway(awayTeam2);
        
        teamService.create(homeTeam2);
        teamService.create(awayTeam2); 
        matchService.create(match2);
        
        List<Match> matches = matchService.findByTeam(awayTeam2);
        Assert.assertEquals(matches.size(),1);
        Assert.assertEquals(match2, matches.get(0));
        
        em.getTransaction().commit();
        em.close();
        
        
    }
}
