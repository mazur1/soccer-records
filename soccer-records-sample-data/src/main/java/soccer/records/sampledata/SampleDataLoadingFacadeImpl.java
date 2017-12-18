
package soccer.records.sampledata;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import soccer.records.entity.*;
import soccer.records.enums.AppRole;
import soccer.records.enums.PlayerPost;
import soccer.records.services.AppUserService;
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
    
    @Autowired
    private AppUserService userService;
        
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        
        Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
        
        // test records
        Team plzen = team("Plzen");
        Team sparta = team("Sparta Praha");
        Team brno = team("Brno");
        Team liberec = team("Liberec");
        Team jablonec = team("Jablonec");
        
        log.info("teams loaded");
        
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

        Match m01 = match(plzen, sparta, "16-12-2017 10:00");
        Match m02 = match(brno, liberec, "17-12-2017 13:00");
        Match m03 = match(plzen, brno, "11-12-2017 10:30");
        Match m04 = match(liberec, plzen, "31-12-2017 11:00");
        Match m05 = match(plzen, jablonec, "24-12-2017 10:00");
        


        PlayerResult pr01 = playerResult(m01, p01, 2);
        PlayerResult pr02 = playerResult(m01, p02, 1);
        


        
        AppUser admin = new AppUser();
        admin.addRole(AppRole.ADMIN);
        admin.setEmail("admin@localhost.com");
        admin.setUserName("admin");
        userService.registerUser(admin, "admin");

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
        p.addTeam(team);
        p.setPost(post);
        p.setCity(city);
        p.setCountry(Country);
        
        playerService.create(p);
        //teamService.addPlayer(team, p);

        return p;
    }
    
    private Match match(Team home, Team away, String date) throws IOException {
        Match m = new Match();
        m.setTeamAway(away);
        m.setTeamHome(home);
        
        Date d;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            d = sdf.parse(date);
            m.setDateAndTime(d);
        } catch(ParseException e) {}
        
        Location loc = new Location();
        loc.setName("Sportovní areál Hamr Braník");
        loc.setCity("Praha 4");
        loc.setState("CZ");
        loc.setStreet("Vltavanù 1542");
        m.setLocation(loc);
        
        m.setTeamAwayGoalsScored(1, true);
        m.setTeamAwayGoalsScored(3, false);
        m.setTeamHomeGoalsScored(2, true);
        m.setTeamHomeGoalsScored(3, false);
        
        matchService.create(m);
        return m;
    }
    
    private PlayerResult playerResult(Match match, Player player, int scored) throws IOException {
        PlayerResult pr = new PlayerResult();
        pr.setMatch(match);
        pr.setPlayer(player);
        pr.setGoalsScored(scored);
        
        playerResultService.create(pr);
        return pr;
    }
}
