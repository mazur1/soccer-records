package soccer.records.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import soccer.records.entity.Team;

/**
 *
 * @author Tomas
 */
public interface TeamDao {
    
	public Team findById(Long id);
	public void create(Team t);
        public void update(Team t);
	public void delete(Team t);
	public List<Team> findAll();
        public Team findByName(String name);
    
}
