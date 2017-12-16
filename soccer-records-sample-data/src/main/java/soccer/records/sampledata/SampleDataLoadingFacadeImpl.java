
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
        Team plzen = team("Plzen");
        Team sparta = team("Sparta Praha");
        
        Player p01 = player("David", "Bièík", 24, PlayerPost.ATTACKER , true, sparta, "Praha", "Czech Republic");
        Player p02 = player("Martin", "Dúbravka", 22, PlayerPost.ATTACKER , false, sparta, "Praha", "Czech Republic");
        Player p03 = player("Vojtìch", "Vorel", 27, PlayerPost.ATTACKER , false, sparta, "Praha", "Czech Republic");
        Player p04 = player("David", "Hovorka", 22, PlayerPost.DEFENDER , true, sparta, "Praha", "Czech Republic");
        Player p05 = player("Michal", "Kadlec", 28, PlayerPost.DEFENDER , false, sparta, "Praha", "Czech Republic");
        Player p06 = player("Jan", "Pavlík", 26, PlayerPost.DEFENDER , false, sparta, "Praha", "Czech Republic");
        Player p07 = player("Matìj", "Sváta", 26, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p08 = player("Ondøej", "Zahustel", 25, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p09 = player("Vojtìch", "Èmelík", 22, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p010 = player("Lukáš", "Štetina", 31, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p011 = player("Lukáš", "Juliš", 33, PlayerPost.GOLMAN , false, sparta, "Praha", "Czech Republic");
        
        Player p11 = player("Roman", "Hubník", 26, PlayerPost.ATTACKER, true, plzen, "Praha", "Czech Republic");
        Player p12 = player("Tomáš", "Hájek", 27, PlayerPost.ATTACKER, false, plzen, "Praha", "Czech Republic");
        Player p13 = player("David", "Limberský", 33, PlayerPost.ATTACKER, false, plzen, "Praha", "Czech Republic");
        Player p14 = player("Filip", "Vacovsky", 32, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p15 = player("Radim", "Øezník", 36, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p16 = player("Jan", "Kopic", 22, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p17 = player("Jan", "Kovaøík", 21, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p18 = player("Milan", "Petržela", 25, PlayerPost.MIDFIELDER, false, plzen, "Praha", "Czech Republic");
        Player p19 = player("Václav", "Pilaø", 28, PlayerPost.MIDFIELDER, false, plzen, "Praha", "Czech Republic");
        Player p110 = player("Jonas", "Vais", 33, PlayerPost.MIDFIELDER, false, plzen, "Praha", "Czech Republic");
        Player p111 = player("Marek", "Bakoš", 30, PlayerPost.GOLMAN, false, plzen, "Praha", "Czech Republic");
 
        //Match match1 = match(barcelona, real);
        
        //PlayerResult pr = playerResult(match1, ronaldo, 2);
    }

     private Team team(String name) throws IOException {
        Team t = new Team();
        t.setName(name);
        teamService.create(t);
        return t;
    }
     
    private Player player(String name, String surname, int age, PlayerPost post, boolean captain, Team team, String city, String Country) throws IOException {
        
        Player p = new Player();
        p.setName(name);
        p.setSurname(surname);
        p.setAge(age);
        p.setCaptain(captain);
        p.setTeam(team);
        p.setPost(post);
        p.setCity(city);
        p.setCountry(Country);
        
        playerService.create(p);
        teamService.addPlayer(team, p);

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
