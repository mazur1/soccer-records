package soccer.records.dao;

import java.util.List;
import soccer.records.entity.Match;

/**
 *
 * @author Tomas
 */
public interface MatchDao {
    public Match findById(Long id);
    public void create(Match m);
    public void delete(Match m);
    public List<Match> findAll();
}
