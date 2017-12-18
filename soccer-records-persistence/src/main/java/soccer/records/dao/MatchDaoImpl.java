package soccer.records.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
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
@Transactional
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

    // filters using streams: reads nicer, doesn't compile when there is a typo
    
    @Override
    public List<Match> filterByTeam(Team t, List<Match> matches) throws DataAccessExceptions {
        
        if(matches == null)
            return new ArrayList<>();
        
        return matches.stream().filter(p -> Objects.equals(t, p.getTeamHome()) 
                    || Objects.equals(t, p.getTeamAway())).collect(Collectors.toList()); 
        /*try{
            return em.createQuery("select m from Match m WHERE m.teamHome == :team OR m.teamAway == :team", Match.class).setParameter("team", t).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        } */
    }
    
    @Override
    public List<Match> filterByTeams(Team t1, Team t2, List<Match> matches) throws DataAccessExceptions {
        
        if(matches == null)
            return new ArrayList<>();
        
        return matches.stream().filter(p -> Objects.equals(t1, p.getTeamAway()) && Objects.equals(t2, p.getTeamHome()) 
                || Objects.equals(t1, p.getTeamHome()) && Objects.equals(t2, p.getTeamAway())).collect(Collectors.toList());
        /*try{
            return em.createQuery("select m from Match m WHERE m.teamHome IN (:teams) AND m.teamAway IN (:teams)", Match.class).setParameter("teams", Arrays.asList(t1, t2)).getResultList(); 
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        } */       
    }
    
    @Override
    public List<Match> filterByLocation(Location l, List<Match> matches) throws DataAccessExceptions {
        
        if(matches == null)
            return new ArrayList<>();
        
        return matches.stream().filter(p -> Objects.equals(l, p.getLocation())).collect(Collectors.toList());
    }
    
    @Override
    public List<Match> filterByDateAndTime(Date d, List<Match> matches) throws DataAccessExceptions {
        
        if(matches == null)
            return new ArrayList<>();
        
        return matches.stream().filter(p -> Objects.equals(d, p.getDateAndTime())).collect(Collectors.toList());       
    }
    
}
