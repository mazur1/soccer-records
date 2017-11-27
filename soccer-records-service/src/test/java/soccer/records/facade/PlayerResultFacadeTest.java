/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

//import org.hibernate.service.spi.FacadeException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;
import soccer.records.enums.PlayerPost;

/**
 *
 * @author Michaela Bocanova
 */
public class PlayerResultFacadeTest extends AbstractTestNGSpringContextTests {
        
    @Autowired
    @InjectMocks
    private PlayerResultFacade playerResultFacade;
    
    @Mock
    private TeamFacade teamFacade;
    @Mock
    private MatchFacade matchFacade;
    @Mock
    private PlayerFacade playerFacade;
       
    @BeforeClass
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private TeamDto t1;
    @BeforeMethod
    public void createTeam1() {
        t1 = new TeamDto();
        t1.setName("A");
        teamFacade.createTeam(t1);        
    }
    private TeamDto t2;
    @BeforeMethod
    public void createTeam2() {
        t2 = new TeamDto();
        t2.setName("H");
        teamFacade.createTeam(t2);
    }
    private PlayerDto p1;
    @BeforeMethod
    public void createPlayer1() {
        p1 = new PlayerDto();
        p1.setName("Ján");
        p1.setAge(22);
        p1.setCaptian(false);
        p1.setSurname("Suchý");
        p1.setPost(PlayerPost.GOLMAN);
        p1.setTeamId(t1.getId());
        playerFacade.createPlayer(p1);
    }
    private PlayerDto p2;
    @BeforeMethod
    public void createPlayer2() {
        p2 = new PlayerDto();
        p2.setName("Igor");
        p2.setAge(21);
        p2.setCaptian(false);
        p2.setSurname("Vysoký");
        p2.setPost(PlayerPost.GOLMAN);
        p2.setTeamId(t2.getId());
        playerFacade.createPlayer(p2);
    }
    private MatchDto m1;
    @BeforeMethod
    public void createMatch1() {
        m1 = new MatchDto();
        m1.setTeamAway(t1);
        m1.setTeamHome(t2);
        matchFacade.createMatch(m1);
    }
    private PlayerResultDto pr1;
    @BeforeMethod
    public void createPlayerResult1() {
        pr1 = new PlayerResultDto();
        pr1.setMatch(m1);
        pr1.setPlayer(p1);
        playerResultFacade.createPlayerResult(pr1);
    }
    private PlayerResultDto pr2;
    @BeforeMethod
    public void createPlayerResult2() {
        pr2 = new PlayerResultDto();
        pr2.setMatch(m1);
        pr2.setPlayer(p2);
        playerResultFacade.createPlayerResult(pr2);
    }  
    
    
}
