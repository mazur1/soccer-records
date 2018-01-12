/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.List;
import soccer.records.entity.Player;
import soccer.records.entity.Team;
import soccer.records.entity.Match;
/**
 *
 * @author 
 */
public interface TeamService {

    Long create(Team t);

    List<Team> findAll();

    Team findById(Long id);

    Team findByName(String name);

    void remove(Team t) throws IllegalArgumentException;

    void update(Team t);
    
    void addPlayer(Team t, Player p);
    void removePlayer(Team t, Player p);
    
    void addMatchHome(Team t, Match m);
    void removeMatchHome(Team t, Match m);
    
    void addMatchAway(Team t, Match m);
    void removeMatchAway(Team t, Match m);
    
    void setNullAllPlayersByTeam(Long id);

    List<Team> filterActive(List<Team> par0);
    
}
