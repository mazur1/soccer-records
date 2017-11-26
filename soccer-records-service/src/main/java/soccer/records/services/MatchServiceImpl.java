package soccer.records.services;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dao.MatchDao;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.exceptions.ServiceException;


/**
 * Implementation of business logic using repository MatchDao.
 * 
 * @author Michaela Bocanova
 */
@Service
public class MatchServiceImpl implements MatchService {
    
    @Inject
    private MatchDao matchDao;

    /**
     * Helper method to validate match before create/update
     * @return 
     */
    private void validate(Match m) {
        if(m.getTeamAway().equals(m.getTeamHome()))
            throw new ServiceException("Can't create a match between the same teams");
        if(m.getTeamHomeGoalsScored(true) > m.getTeamHomeGoalsScored(false) 
                || m.getTeamAwayGoalsScored(true) > m.getTeamAwayGoalsScored(false))
            throw new ServiceException("Number of goals scored during halftime cannot be bigger than total");
        
    }
    
    @Override
    public Long create(Match m) {
        validate(m);
        matchDao.create(m);
        return m.getId();
    }

    @Override
    public void update(Match m){
        validate(m);
        matchDao.update(m);
    }

    @Override
    public void delete(Match m){
        matchDao.delete(m);
    }
    
    @Override
    public List<Match> findAll() {
        return matchDao.findAll();
    }

    @Override
    public Match findById(Long id) {
        return matchDao.findById(id);
    }
    
    @Override
    public List<Match> findByTeam(Team t) {
       return matchDao.findByTeam(t);
    }
    
    @Override
    public List<Match> findByTeams(Team t1, Team t2) {
       return matchDao.findByTeams(t1, t2);
    }
    
    @Override
    public void addPlayerResult(Match m, PlayerResult r) {
	if (m.getPlayerResults().contains(r)) {
            throw new ServiceException("Match already contais this player result. \n" +
                                        "Match: " + m.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	m.addPlayerResult(r);
    }
    
    public String matchResult(Match m) {
        if(m.getTeamHomeGoalsScored(false) > m.getTeamHomeGoalsReceived(false))
            return "Team " + m.getTeamHome() + " won.";
        if(m.getTeamAwayGoalsScored(false) > m.getTeamAwayGoalsReceived(false))
            return "Team " + m.getTeamAway() + "won.";
        return "Tie.";
    }
}
