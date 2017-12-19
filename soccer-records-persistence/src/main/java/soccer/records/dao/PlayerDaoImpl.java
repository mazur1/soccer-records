package soccer.records.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import soccer.records.entity.Player;

import soccer.records.exceptions.dao.DataAccessExceptions;

@Repository
public class PlayerDaoImpl extends DefaultCrudDaoImpl<Player,Long> implements PlayerDao {

    /*@PersistenceContext
    private EntityManager em;*/

    public PlayerDaoImpl() {        
        super(Player.class, Long.class);
    }
    
    /*@Override
    public Player findById(Long id) throws DataAccessExceptions {
        try {
            return em.find(Player.class, id);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }*/

    @Override
    public Player findByPlayer(Player p) throws DataAccessExceptions {
        try {
            return em.find(Player.class, p.getId());
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public List<Player> findAll() throws DataAccessExceptions {
        try {
            return em.createQuery("select p from Player p", Player.class).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
    @Override
    public List<Player> findAllActive() throws DataAccessExceptions {
        try {
            return em.createQuery("select p from Player p where p.isActive = :active", Player.class).setParameter("active", true).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    /*@Override
    public void create(Player c) throws DataAccessExceptions {
        try {
            em.persist(c);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public void update(Player c) throws DataAccessExceptions {
        try {
            em.merge(c);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    */

    @Override
    public void delete(Player c) throws DataAccessExceptions{
        try {
            c.setIsActive(false);
            em.merge(c);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

    @Override
    public List<Player> findByName(String name, String surname) throws DataAccessExceptions {
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
