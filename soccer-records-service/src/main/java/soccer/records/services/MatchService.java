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
            
    MatchServiceImpl.MatchResult getMatchResult(Match m);
    
    MatchServiceImpl.TeamResult getTeamResult(Team t);
}
