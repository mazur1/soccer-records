package soccer.records.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Team;

/**
 *
 * @author Tomas
 */
@Repository
public class TeamDaoImpl implements TeamDao {
        
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Team t) {
        em.persist(t);
    }
    
    @Override
    public void update(Team t) {
        em.merge(t);
    }
    
    @Override
    public void delete(Team t) {
        em.remove(t);
    }
    
    @Override
    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }
    
    @Override
    public Team findById(Long id) {
        return em.find(Team.class, id);
    }
    
    @Override
    public Team findByName(String name) {
        try {
                return em.createQuery("select t from Team t where t.name = :name",Team.class).setParameter("name", name).getSingleResult();
            } catch (NoResultException nrf) {
                return null;
            }
    }
}
