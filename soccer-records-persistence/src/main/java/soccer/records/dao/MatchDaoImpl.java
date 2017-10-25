package soccer.records.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Match;


/**
 *
 * @author Tomas
 */
@Repository
public class MatchDaoImpl implements MatchDao {
    
    	@PersistenceContext
        private EntityManager em;
        
        @Override
        public Match findById(Long id){
            return em.find(Match.class, id);
        }
        
        @Override        
        public void create(Match m){
            em.persist(m);
        }
        
        @Override
        public void delete(Match m) {
            em.remove(m);
        }
        
        @Override        
        public List<Match> findAll() {
            return em.createQuery("select m from Match m", Match.class).getResultList();
        }
        

    
}
