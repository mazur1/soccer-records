package soccer.records.dao;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Match;
import soccer.records.entity.Team;

import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 * Implemented CRUD functionality in a repository
 * 
 * @author Michaela Bocanova
 */
@Repository
public class MatchDaoImpl implements MatchDao {
    
    @PersistenceContext
    private EntityManager em;
                
    @Override        
    public void create(Match m){
        try {
            em.persist(m);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
        
    @Override
    public void update(Match m) {
        try {
            em.merge(m);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
    @Override
    public void delete(Match m) {
        try {
            em.remove(findById(m.getId()));
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
        
    @Override
    public Match findById(Long id){
        try {
            return em.find(Match.class, id);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
        
    @Override        
    public List<Match> findAll() {
        try{
            return em.createQuery("select m from Match m", Match.class).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }

    @Override
    public List<Match> findByTeam(Team t) {
        try{
            return em.createQuery("select m from Match m WHERE m.teamHome = :team OR m.teamAway = :team", Match.class).setParameter("team", t).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
    
    @Override
    public List<Match> findByTeams(Team t1, Team t2) {
        try{
            return em.createQuery("select m from Match m WHERE m.teamHome IN (:teams) AND m.teamAway IN (:teams)", Match.class).setParameter("teams", Arrays.asList(t1, t2)).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
}
