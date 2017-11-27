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

@Repository
public class PlayerDaoImpl implements PlayerDao {

	@PersistenceContext
        private EntityManager em;

        @Override
	public Player findById(Long id) {
            return em.find(Player.class, id);
	}
        
        @Override
        public Player findByPlayer(Player p){
            return em.find(Player.class, p.getId());
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
	public List<Player> findByName(String name,  String surname) {
            try {
                return em.createQuery("select p from Player p where p.name = :name AND p.surname = :surname",Player.class).setParameter("name", name).setParameter("surname", surname).getResultList();
            } catch (NoResultException nrf) {
                return null;
            }
	}
        
        /*public void addPlayerResult(PlayerResult r)
        {
            
        }
        
        public void removePlayerResult(PlayerResult r)
        {
        
        }*/
}
