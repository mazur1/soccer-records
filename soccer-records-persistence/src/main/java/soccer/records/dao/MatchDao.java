package soccer.records.dao;

import java.util.Date;
import java.util.List;
import soccer.records.entity.Location;
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
    public List<Match> filterByTeam(Team t, List<Match> matches) throws DataAccessExceptions;
    /**
     * Finds all matches between two teams
     * @param t1
     * @param t2
     * @return 
     * @throws DataAccessExceptions 
     */
    public List<Match> filterByTeams(Team t1, Team t2, List<Match> matches) throws DataAccessExceptions;

    List<Match> filterByDateAndTime(Date d, List<Match> matches);

    List<Match> filterByLocation(Location l, List<Match> matches);

    List<Match> filterActive(List<Match> par0) throws DataAccessExceptions;

    List<Match> findAll() throws DataAccessExceptions;

    
}
