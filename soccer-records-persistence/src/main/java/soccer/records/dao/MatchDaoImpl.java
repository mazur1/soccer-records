package soccer.records.dao;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Match;
import soccer.records.entity.Team;


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
        em.persist(m);
    }
        
    @Override
    public void update(Match m) {
        em.merge(m);
    }
    
    @Override
    public void delete(Match m) {
        em.remove(findById(m.getId()));
    }
        
    @Override
    public Match findById(Long id){
        return em.find(Match.class, id);
    }
        
    @Override        
    public List<Match> findAll() {
        return em.createQuery("select m from Match m", Match.class).getResultList();
    }

    @Override
    public List<Match> findByTeam(Team t) {
       return em.createQuery("select m from Match m WHERE m.teamHome = :team OR m.teamAway = :team", Match.class).setParameter("team", t).getResultList(); 
    }
    
    @Override
    public List<Match> findByTeams(Team t1, Team t2) {
       return em.createQuery("select m from Match m WHERE m.teamHome IN (:teams) AND m.teamAway IN (:teams)", Match.class).setParameter("teams", Arrays.asList(t1, t2)).getResultList(); 
    }
    
}
