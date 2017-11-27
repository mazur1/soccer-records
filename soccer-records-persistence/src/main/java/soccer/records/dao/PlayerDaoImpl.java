package soccer.records.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;

import soccer.records.exceptions.dao.DataAccessExceptions;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Player findById(Long id) {
        try {
            return em.find(Player.class, id);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public Player findByPlayer(Player p) {
        try {
            return em.find(Player.class, p.getId());
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public List<Player> findAll() {
        try {
            return em.createQuery("select p from Player p", Player.class).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public void create(Player c) {
        try {
            em.persist(c);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public void update(Player c) {
        try {
            em.merge(c);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public void delete(Player c) {
        try {
            em.remove(em.merge(c));
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public List<Player> findByName(String name, String surname) {
        try {
            return em.createQuery("select p from Player p where p.name = :name AND p.surname = :surname", Player.class).setParameter("name", name).setParameter("surname", surname).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    /*public void addPlayerResult(PlayerResult r)
        {
            
        }
        
        public void removePlayerResult(PlayerResult r)
        {
        
        }*/
}
