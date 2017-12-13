package soccer.records.dao;

import java.util.Arrays;
import java.util.List;
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
public class MatchDaoImpl extends DefaultCrudDaoImpl<Match,Long> implements MatchDao {
               
    public MatchDaoImpl() {        
        super(Match.class, Long.class);
    }
    
    @Override        
    public List<Match> findAll() throws DataAccessExceptions {
        try{
            return em.createQuery("select m from Match m", Match.class).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }

    @Override
    public List<Match> findByTeam(Team t) throws DataAccessExceptions {
        try{
            return em.createQuery("select m from Match m WHERE m.teamHome = :team OR m.teamAway = :team", Match.class).setParameter("team", t).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
    
    @Override
    public List<Match> findByTeams(Team t1, Team t2) throws DataAccessExceptions {
        try{
            return em.createQuery("select m from Match m WHERE m.teamHome IN (:teams) AND m.teamAway IN (:teams)", Match.class).setParameter("teams", Arrays.asList(t1, t2)).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
}
