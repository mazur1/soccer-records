/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.List;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;

/**
 * Match service interface
 * 
 * @author Michaela Bocanova
 */
public interface MatchService {

    Long create(Match m);

    void delete(Match m);

    List<Match> findAll();

    Match findById(Long id);

    List<Match> findByTeam(Team t);
    
    List<Match> findByTeams(Team t1, Team t2);

    void update(Match m);
    
    void addPlayerResult(Match m, PlayerResult r);
    
    void removePlayerResult(Match m, PlayerResult r);
            
    /**
     * Computes result of the match
     * @param m
     * @return 
     */
    MatchResult getMatchResult(Match m);
    
    /**
     * Computes result for the team from match results
     * @param t
     * @return 
     */
    TeamResult getTeamResult(Team t);

    List<Match> filterActive(List<Match> par0);
    
    public int getTeamHomeGoalsScored(Match m);
    
    public int getTeamAwayGoalsScored(Match m);
}
