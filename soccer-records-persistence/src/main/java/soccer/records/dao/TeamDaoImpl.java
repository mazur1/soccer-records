package soccer.records.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Team;

import soccer.records.exceptions.dao.DataAccessExceptions;

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

    @Override
    public void delete(Team t) {
        try {
            em.remove(findById(t.getId()));
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public List<Team> findAll() {
        try {
            return em.createQuery("select t from Team t", Team.class).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public Team findById(Long id) {
        try {
            return em.find(Team.class, id);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public Team findByName(String name) {
        try {
            return em.createQuery("select t from Team t where t.name = :name", Team.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
}
