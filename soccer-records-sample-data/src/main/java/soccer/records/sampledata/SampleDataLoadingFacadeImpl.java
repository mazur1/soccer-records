
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
        
        Player p01 = player("David", "Bi��k", 24, PlayerPost.ATTACKER , true, sparta, "Praha", "Czech Republic");
        Player p02 = player("Martin", "D�bravka", 22, PlayerPost.ATTACKER , false, sparta, "Praha", "Czech Republic");
        Player p03 = player("Vojt�ch", "Vorel", 27, PlayerPost.ATTACKER , false, sparta, "Praha", "Czech Republic");
        Player p04 = player("David", "Hovorka", 22, PlayerPost.DEFENDER , true, sparta, "Praha", "Czech Republic");
        Player p05 = player("Michal", "Kadlec", 28, PlayerPost.DEFENDER , false, sparta, "Praha", "Czech Republic");
        Player p06 = player("Jan", "Pavl�k", 26, PlayerPost.DEFENDER , false, sparta, "Praha", "Czech Republic");
        Player p07 = player("Mat�j", "Sv�ta", 26, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p08 = player("Ond�ej", "Zahustel", 25, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p09 = player("Vojt�ch", "�mel�k", 22, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p010 = player("Luk�", "�tetina", 31, PlayerPost.MIDFIELDER , false, sparta, "Praha", "Czech Republic");
        Player p011 = player("Luk�", "Juli�", 33, PlayerPost.GOLMAN , false, sparta, "Praha", "Czech Republic");
        
        Player p11 = player("Roman", "Hubn�k", 26, PlayerPost.ATTACKER, true, plzen, "Praha", "Czech Republic");
        Player p12 = player("Tom�", "H�jek", 27, PlayerPost.ATTACKER, false, plzen, "Praha", "Czech Republic");
        Player p13 = player("David", "Limbersk�", 33, PlayerPost.ATTACKER, false, plzen, "Praha", "Czech Republic");
        Player p14 = player("Filip", "Vacovsky", 32, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p15 = player("Radim", "�ezn�k", 36, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p16 = player("Jan", "Kopic", 22, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p17 = player("Jan", "Kova��k", 21, PlayerPost.DEFENDER, false, plzen, "Praha", "Czech Republic");
        Player p18 = player("Milan", "Petr�ela", 25, PlayerPost.MIDFIELDER, false, plzen, "Praha", "Czech Republic");
        Player p19 = player("V�clav", "Pila�", 28, PlayerPost.MIDFIELDER, false, plzen, "Praha", "Czech Republic");
        Player p110 = player("Jonas", "Vais", 33, PlayerPost.MIDFIELDER, false, plzen, "Praha", "Czech Republic");
        Player p111 = player("Marek", "Bako�", 30, PlayerPost.GOLMAN, false, plzen, "Praha", "Czech Republic");
 
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
