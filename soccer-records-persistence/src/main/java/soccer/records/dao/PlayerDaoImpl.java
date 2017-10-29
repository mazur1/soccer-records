package soccer.records.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import soccer.records.entity.Player;

@Repository
public class PlayerDaoImpl implements PlayerDao {

	@PersistenceContext
        private EntityManager em;

        @Override
	public Player findById(Long id) {
            return em.find(Player.class, id);
	}

	@Override
	public List<Player> findAll() {
            return em.createQuery("select p from Player p", Player.class).getResultList();
	}

	@Override
	public void create(Player c) {
            em.persist(c);
	}

        @Override
	public void update(Player c) {
            em.merge(c);
	}
        
	@Override
	public void delete(Player c) {
            em.remove(em.merge(c));
	}

	@Override
	public List<Player> findByName(String name) {
            try {
                return em.createQuery("select p from Player p where p.name = :name",Player.class).setParameter("name", name).getResultList();
            } catch (NoResultException nrf) {
                return null;
            }
	}
}
