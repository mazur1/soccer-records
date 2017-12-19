package soccer.records.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Team;

import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 *
 * @author Tomas
 */
@Repository
public class TeamDaoImpl extends DefaultCrudDaoImpl<Team,Long> implements TeamDao {

    /*@PersistenceContext
    private EntityManager em;*/

    public TeamDaoImpl() {        
        super(Team.class, Long.class);
    }
    
    /*@Override
    public void create(Team t) throws DataAccessExceptions {
        try {
            em.persist(t);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public void update(Team t) {
        try {
            em.merge(t);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    */

    @Override
    public void delete(Team t) {
        try {
            t.setIsActive(false);
            em.merge(t);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public List<Team> findAll() throws DataAccessExceptions {
        try {
            return em.createQuery("select t from Team t", Team.class).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
    @Override
    public List<Team> filterActive(List<Team> par0) throws DataAccessExceptions {
        try {
            return par0.stream().filter(p -> p.isIsActive() == true).collect(Collectors.toList());//return em.createQuery("select p from Team p where p.isActive = :active", Team.class).setParameter("active", true).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    /*@Override
    public Team findById(Long id) {
        try {
            return em.find(Team.class, id);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }*/

    @Override
    public Team findByName(String name) throws DataAccessExceptions {
        try {
            return em.createQuery("select t from Team t where t.name = :name", Team.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
}
