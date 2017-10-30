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
    
        /**
        * Method to find certain Team.
        * @param id id of certain Team to find
        * @return return Team with certain id
         */
	public Team findById(Long id);
        /**
        * Method to create Team in db.
        * @param t Team to create
         */
	public void create(Team t);
        /**
        * Method to update Team in db.
        * @param t Team to update
         */
        public void update(Team t);
        /**
        * Method to delete Team from db.
        * @param t Team to delete
         */
	public void delete(Team t);
         /**
        * Method to find all Teams.
        * @return return List of all teams
         */
	public List<Team> findAll();
        /**
        * Method to update Team in db.
        * @param name name of certain Team to find
        * @return Team with certain name
         */
        public Team findByName(String name);
    
}
