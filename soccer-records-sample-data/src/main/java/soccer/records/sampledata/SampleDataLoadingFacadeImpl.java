
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
        
        Player p21 = player( "Radek", "Buchta", 24, PlayerPost.ATTACKER , true, brno, "Praha", "Czech Republic");
        Player p22 = player( "Toman", "Chico", 22, PlayerPost.ATTACKER , false, brno, "Praha", "Czech Republic");
        Player p23 = player( "David", "Gac", 27, PlayerPost.ATTACKER , false, brno, "Praha", "Czech Republic");
        Player p24 = player( "Tomáš", "Jablonský", 22, PlayerPost.DEFENDER , true, brno, "Praha", "Czech Republic");
        Player p25 = player( "Mihailo", "Jovanoviè", 28, PlayerPost.DEFENDER , false, brno, "Praha", "Czech Republic");
        Player p26 = player( "Tadas", "Kijanskas", 26, PlayerPost.DEFENDER , false, brno, "Praha", "Czech Republic");
        Player p27 = player( "Petr", "Pavlík", 26, PlayerPost.MIDFIELDER , false, brno, "Praha", "Czech Republic");
        Player p28 = player( "Lukáš", "Vraštil", 25, PlayerPost.MIDFIELDER , false, brno, "Praha", "Czech Republic");
        Player p29 = player( "Jakub", "Šural", 22, PlayerPost.MIDFIELDER , false, brno, "Praha", "Czech Republic");
        Player p210 = player( "Alvin", "Fortes", 31, PlayerPost.MIDFIELDER , false, brno, "Praha", "Czech Republic");
        Player p211 = player( "Dominik", "Janosek", 33, PlayerPost.GOLMAN , false, brno, "Praha", "Czech Republic");


        Player p31 = player("Martin", "Juhar", 24, PlayerPost.ATTACKER , true, liberec, "Praha", "Czech Republic");
        Player p32 = player("Luboš", "Košuliè", 22, PlayerPost.ATTACKER , false, liberec, "Praha", "Czech Republic");
        Player p33 = player("Ladislav", "Krejèí", 27, PlayerPost.ATTACKER , false, liberec, "Praha", "Czech Republic");
        Player p34 = player("Milan", "Lutonský", 22, PlayerPost.DEFENDER , true, liberec, "Praha", "Czech Republic");
        Player p35 = player("Tomáš", "Pilík", 28, PlayerPost.DEFENDER , false, liberec, "Praha", "Czech Republic");
        Player p36 = player("Jan", "Polák", 26, PlayerPost.DEFENDER , false, liberec, "Praha", "Czech Republic");
        Player p37 = player("Jakub", "Pøichystal", 26, PlayerPost.MIDFIELDER , false, liberec, "Praha", "Czech Republic");
        Player p38 = player("Jan", "Sedlák", 25, PlayerPost.MIDFIELDER , false, liberec, "Praha", "Czech Republic");
        Player p39 = player("Marek", "Vintr", 22, PlayerPost.MIDFIELDER , false, liberec, "Praha", "Czech Republic");
        Player p310 = player("Francis", "Kone", 31, PlayerPost.MIDFIELDER , false, liberec, "Praha", "Czech Republic");
        Player p311 = player("Miloš", "Kratochvíl", 33, PlayerPost.GOLMAN , false, liberec, "Praha", "Czech Republic");


        Player p41 = player("Jiøí", "Bederka", 24, PlayerPost.ATTACKER , true, jablonec, "Praha", "Czech Republic");
        Player p42 = player("Jiøí", "Funda", 22, PlayerPost.ATTACKER , false, jablonec, "Praha", "Czech Republic");
        Player p43 = player("Dominik", "Hašek", 27, PlayerPost.ATTACKER , false, jablonec, "Praha", "Czech Republic");
        Player p44 = player("Karel", "Knejzlik", 22, PlayerPost.DEFENDER , true, jablonec, "Praha", "Czech Republic");
        Player p45 = player("Marek", "Krátký", 28, PlayerPost.DEFENDER , false, jablonec, "Praha", "Czech Republic");
        Player p46 = player("Jakub", "Martinec", 26, PlayerPost.DEFENDER , false, jablonec, "Praha", "Czech Republic");
        Player p47 = player("Jan", "Mudra", 26, PlayerPost.MIDFIELDER , false, jablonec, "Praha", "Czech Republic");
        Player p48 = player("Martin", "Nosek", 25, PlayerPost.MIDFIELDER , false, jablonec, "Praha", "Czech Republic");
        Player p49 = player("Adam", "Pajer", 22, PlayerPost.MIDFIELDER , false, jablonec, "Praha", "Czech Republic");
        Player p410 = player("Marek", "Plašil", 31, PlayerPost.MIDFIELDER , false, jablonec, "Praha", "Czech Republic");
        Player p411 = player("Jan", "Shejbal", 33, PlayerPost.GOLMAN , false, jablonec, "Praha", "Czech Republic");

        Match m01 = match(plzen, sparta, "16-12-2017 10:00", "Sportovní areál Hamr Braník");
        Match m02 = match(brno, liberec, "17-12-2017 13:00", "Stadion Kozel");
        Match m03 = match(plzen, brno, "11-12-2017 10:30", "Sportovní areál Hamr Braník");
        Match m04 = match(liberec, plzen, "31-12-2017 11:00", "LIBEREC aréna");
        Match m05 = match(plzen, jablonec, "24-12-2017 10:00", "Stadion Kozel");
        


        PlayerResult pr01 = playerResult(m01, p01, 2);
        PlayerResult pr02 = playerResult(m01, p02, 1);
        

        PlayerResult pr03 = playerResult(m02, p36, 1);
        
        PlayerResult pr04 = playerResult(m03, p11, 2);
        PlayerResult pr05 = playerResult(m03, p29, 1);
        
        PlayerResult pr06 = playerResult(m04, p110, 3);

        
        PlayerResult pr08 = playerResult(m05, p44, 1);
        PlayerResult pr09 = playerResult(m05, p45, 1);
        PlayerResult pr10 = playerResult(m05, p46, 2);
        PlayerResult pr11 = playerResult(m05, p110, 1);


        
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
        p.setTeam(team);
        p.setPost(post);
        p.setCity(city);
        p.setCountry(Country);
        
        playerService.create(p);
        //teamService.addPlayer(team, p);

        return p;
    }
    
    private Match match(Team home, Team away, String date, String locName) throws IOException {
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
        loc.setName(locName);
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
