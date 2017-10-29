package soccer.records.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Match;
import soccer.records.entity.Team;


/**
 *
 * @author Tomas
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
        em.persist(m);
    }
    
    @Override
    public void delete(Match m) {
        em.remove(m);
    }
        
    @Override
    public Match findById(Long id){
        return em.find(Match.class, id);
    }
        
    @Override        
    public List<Match> findAll() {
        return em.createQuery("select m from SoccerMatch m", Match.class).getResultList();
    }

    @Override
    public List<Match> findByTeam(Team t) {
       return em.createQuery("select m from SoccerMatch m WHERE m.teamHome = :teamId OR m.teamAway = :teamId", Match.class).setParameter(":teamId", t.getId()).getResultList(); 
    }
    
}
