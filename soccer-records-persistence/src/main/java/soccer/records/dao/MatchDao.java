package soccer.records.dao;

import java.util.List;
import soccer.records.entity.Match;
import soccer.records.entity.Team;
import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 * CRUD functionality in a repository
 * 
 * @author Michaela Bocanova
 */
public interface MatchDao extends DefaultCrudDao<Match,Long> {
    
    /**
     * Finds all matches the team participated in
     * @param t
     * @return 
     * @throws DataAccessExceptions 
     */
    public List<Match> findByTeam(Team t) throws DataAccessExceptions;
    /**
     * Finds all matches between two teams
     * @param t1
     * @param t2
     * @return 
     * @throws DataAccessExceptions 
     */
    public List<Match> findByTeams(Team t1, Team t2) throws DataAccessExceptions;
}
