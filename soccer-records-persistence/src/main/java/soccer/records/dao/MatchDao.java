package soccer.records.dao;

import java.util.List;
import soccer.records.entity.Match;
import soccer.records.entity.Team;

/**
 *
 * @author Tomas
 */
public interface MatchDao {
    
    public void create(Match m);
    public void update(Match m);
    public void delete(Match m);
    public Match findById(Long id);
    public List<Match> findAll();
    public List<Match> findByTeam(Team t);
}
