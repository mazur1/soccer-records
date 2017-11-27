package soccer.records.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dao.MatchDao;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.exceptions.service.SoccerServiceException;


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
            throw new SoccerServiceException("Can't create a match between the same teams");
        if(m.getTeamHomeGoalsScored(true) > m.getTeamHomeGoalsScored(false) 
                || m.getTeamAwayGoalsScored(true) > m.getTeamAwayGoalsScored(false))
            throw new SoccerServiceException("Number of goals scored during halftime cannot be bigger than total");
        
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
            throw new SoccerServiceException("Match already contains this player result. \n" +
                                        "Match: " + m.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	m.addPlayerResult(r);
    }
    
    @Override
    public void removePlayerResult(Match m, PlayerResult r) {
        if (!m.getPlayerResults().contains(r)) {
            throw new SoccerServiceException("Match doesn't contain this player result. \n" +
                                        "Match: " + m.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	m.removePlayerResult(r);
    }
        
    @Override
    public MatchResult getMatchResult(Match m) {
        MatchResult result = new MatchResult();
        result.setMatch(m);
        
        if(m.getTeamHomeGoalsScored(false) > m.getTeamHomeGoalsReceived(false)) {
            result.setWinner(m.getTeamHome());
            result.setLooser(m.getTeamAway());
        }
        else if(m.getTeamAwayGoalsScored(false) > m.getTeamAwayGoalsReceived(false)) {
            result.setWinner(m.getTeamAway());
            result.setLooser(m.getTeamHome());
        }
        else result.setTie(true);
        
        return result;
    }
    
    @Override
    public TeamResult getTeamResult(Team t) {
        TeamResult tResult = new TeamResult();
        tResult.setTeam(t);
        List<Match> matches = matchDao.findByTeam(t);
        
        int wins=0, losses=0,ties=0;
        for(Match m : matches) {
            MatchResult mResult = getMatchResult(m);
            if (mResult.isTie())
                ties++;
            else if (mResult.getWinner() != null && mResult.getWinner().equals(t))
                wins++;
            else if (mResult.getLooser() != null && mResult.getLooser().equals(t))
                losses++;
        }
        tResult.setLosses(losses);
        tResult.setTies(ties);
        tResult.setWins(wins);
        
        return tResult;
    }

    
}