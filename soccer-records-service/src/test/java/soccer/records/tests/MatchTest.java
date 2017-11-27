/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dao.MatchDao;
import soccer.records.entity.Location;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.services.MatchResult;
import soccer.records.services.MatchService;
import soccer.records.services.TeamService;

/**
 *
 * @author Tomas Mazurek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchTest extends AbstractTestNGSpringContextTests {
    
    
    @Mock
    private MatchDao matchDao;

    @Autowired
    @InjectMocks
    private MatchService matchService;   
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void resetMock() {
        Mockito.reset(matchDao);
    }
    
    private Match testMatch;
    private Match testMatch2;
    
    private Player testPlayer;
    private PlayerResult testPlayerResult;
    
    @BeforeTest
    private void createTestMatch() {
        Team teamHome = newTeam("teamHome");
        Team teamAway = newTeam("teamAway");
        
        testMatch = new Match();
        testMatch.setTeamHome(teamHome);
        testMatch.setTeamAway(teamAway);
        
        Team teamHome2 = newTeam("teamHome2");
        Team teamAway2 = newTeam("teamAway2");
        
        testMatch2 = new Match();
        testMatch2.setTeamHome(teamHome2);
        testMatch2.setTeamAway(teamAway2);
        
        testPlayer = new Player();
        testPlayer.setName("Honza");
        testPlayer.setTeam(teamHome);
        
        testPlayerResult = new PlayerResult(testPlayer, testMatch);
        testPlayerResult.setGoalsScored(1);
        
        List<PlayerResult> playerResultsL = new ArrayList<>();
        playerResultsL.add(testPlayerResult);
        testMatch.setPlayerResults(playerResultsL);
        
        
    }
    
    private Team newTeam (String name) {
        Team homeTeam = new Team();
        homeTeam.setName(name);
        return homeTeam;
    }
    
    @Test
    public void createMatch() {
        
        matchService.create(testMatch);
        Mockito.verify(matchDao).create(testMatch);
        
        matchService.create(testMatch2);
        Mockito.verify(matchDao).create(testMatch2);
    }
    
    @Test
    public void updateMatch() {
        
        Team newAwayTeam = newTeam("new");
        
        testMatch.setTeamAway(newAwayTeam);
        
        matchService.update(testMatch);
        verify(matchDao).update(testMatch);

    }
    
    @Test
    public void findAllMatches() {
            
        when(matchDao.findAll()).thenReturn(Arrays.asList(testMatch, testMatch2));

        List<Match> actual = matchService.findAll();

        Assert.assertEquals(actual.size(), 2);   
        Assert.assertEquals(actual, Arrays.asList(testMatch, testMatch2));  
        
        verify(matchDao).findAll();
    }
    

    
}
