package soccer.records.sampledata;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import soccer.records.entity.*;
import soccer.records.enums.PlayerPost;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;

/**
 *
 * @author Tomas Mazurek
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private MatchService matchService;
        
    @Autowired
    private PlayerResultService playerResultService;
    
    
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        // test records
   
        Team barcelona = team("Barcelona");
        Team real = team("Real Madrid");
        Player ronaldo = player("Cristiano","Ronaldo", 24, PlayerPost.ATTACKER,true,barcelona);
        
        Player novak = player("Jan", "Novak", 21, PlayerPost.DEFENDER, false, real);
        
        Match match1 = match(barcelona, real);
        
        //PlayerResult pr = playerResult(match1, ronaldo, 2);
    }

     private Team team(String name) throws IOException {
        Team t = new Team();
        t.setName(name);
        teamService.create(t);
        return t;
    }
     
    private Player player(String name, String surname, int age, PlayerPost post, boolean captain, Team team) throws IOException {
        Player p = new Player();
        p.setName(name);
        p.setSurname(surname);
        p.setAge(age);
        p.setCaptian(captain);
        p.setTeam(team);
        p.setPost(post);
        
        playerService.create(p);

        return p;
    }
    
    private Match match(Team home, Team away) throws IOException {
        Match m = new Match();
        m.setTeamAway(away);
        m.setTeamHome(home);
        m.setDateAndTime(null);
        m.setLocation(null);
        m.setTeamAwayGoalsScored(0, true);
        m.setTeamAwayGoalsScored(0, false);
        m.setTeamHomeGoalsScored(0, true);
        m.setTeamHomeGoalsScored(0, false);
        
        matchService.create(m);
        return m;
    }
    
    private PlayerResult playerResult(Match match, Player player, int scored) throws IOException {
        PlayerResult pr = new PlayerResult();
        pr.setMatch(match);
        pr.setPlayer(player);
        pr.setGoalsScored(scored);
        //todo other attributes
        playerResultService.create(pr);
        return pr;
    }
}
