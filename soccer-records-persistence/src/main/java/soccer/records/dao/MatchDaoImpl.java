package soccer.records.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import soccer.records.entity.Location;
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

    // filters 
    
    private List<Long> getIds(List<Match> matches) {
        List<Long> ids = new ArrayList<>(); 
        for(Match m : matches) {
            ids.add(m.getId());
        }
        return ids;
    }
    
    @Override
    public List<Match> filterByTeam(Team t, List<Match> matches) throws DataAccessExceptions {
        List<Long> ids = getIds(matches);
        if(ids.isEmpty())
            return new ArrayList<>();
        
        try{
            return em.createQuery("select m from Match m WHERE m.id in (:ids) and m.teamHome = :team OR m.teamAway = :team", Match.class).setParameter("ids", ids).setParameter("team", t).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
    
    @Override
    public List<Match> filterByTeams(Team t1, Team t2, List<Match> matches) throws DataAccessExceptions {
        List<Long> ids = getIds(matches);
        if(ids.isEmpty())
            return new ArrayList<>();
        
        try{
            return em.createQuery("select m from Match m WHERE m.id in (:ids) and m.teamHome IN (:teams) AND m.teamAway IN (:teams)", Match.class).setParameter("ids", ids).setParameter("teams", Arrays.asList(t1, t2)).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
    @Override
    public List<Match> filterByLocation(Location l, List<Match> matches) throws DataAccessExceptions {
        List<Long> ids = getIds(matches);
        if(ids.isEmpty())
            return new ArrayList<>();
        
        try{
            return em.createQuery("select m from Match m where m.id in (:ids) and m.location = :l", Match.class).setParameter("ids", ids).setParameter("l", l).getResultList();
        } catch(Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
    @Override
    public List<Match> filterByDateAndTime(Date d, List<Match> matches) throws DataAccessExceptions {
        List<Long> ids = getIds(matches);
        if(ids.isEmpty())
            return new ArrayList<>();
        
        try{
            return em.createQuery("select m from Match m where m.id in (:ids) and m.dateAndTime = :d", Match.class).setParameter("ids", ids).setParameter("d", d).getResultList();
        } catch(Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
}
