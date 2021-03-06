package soccer.records.services;

import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    final static Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);
    
    @Inject
    private MatchDao matchDao;
    
    @Inject
    private PlayerResultService resultService;

    /**
     * Helper method to validate Match match before create/update
     * @return 
     */
    private void validateMatch(Match m) throws SoccerServiceException {
        
        if(m.getTeamHome() != null) {
            if(m.getTeamHome().equals(m.getTeamAway()))
                throw new SoccerServiceException("Can't create a match between the same teams");
        }
        if(m.getTeamHomeGoalsScoredHalf() > m.getTeamHomeGoalsScored() 
                || m.getTeamAwayGoalsScoredHalf() > m.getTeamAwayGoalsScored())
            throw new SoccerServiceException("Number of goals scored during halftime cannot be bigger than total");
        
        for(PlayerResult r : m.getPlayerResults()) {
            validatePlayerResult(m, r);
        }       
    }
    
    private void validatePlayerResult(Match m, PlayerResult r) throws SoccerServiceException {
        if(r.getPlayer() != null) { // edit class playerresult?
                if(!Objects.equals(r.getPlayer().getTeam(), m.getTeamHome())
                        && !Objects.equals(r.getPlayer().getTeam(), m.getTeamAway()))
                    throw new SoccerServiceException("Player result " + r + " is from unparticipating team.");
            }
    }
    
    private void validateMatches(Match m, boolean update) {
        if(m == null) throw new SoccerServiceException("match is null");
        
        List<Match> matches = matchDao.findAll();
        if(update)
            matches.remove(matchDao.findById(m.getId()));
        
        List<Match> active = matchDao.filterActive(matches);
        
        
        List<Match> byDate = matchDao.filterByDateAndTime(m.getDateAndTime(), active);
        if(!byDate.isEmpty()) {
            List<Match> byTeam1 = matchDao.filterByTeam(m.getTeamAway(), active);
            List<Match> byTeam2 = matchDao.filterByTeam(m.getTeamHome(), active);
            
            if(!byTeam1.isEmpty())
                throw new SoccerServiceException("Team " + m.getTeamAway() + " is participating in a different match at that time.");
            if(!byTeam2.isEmpty())
                throw new SoccerServiceException("Team " + m.getTeamHome() + " is participating in a different match at that time.");
        }
        List<Match> byLocation = matchDao.filterByLocation(m.getLocation(), byDate);
        if(!byLocation.isEmpty())
            throw new SoccerServiceException("Location " + m.getLocation() + " is hosting a different match at that time.");
        
        validateMatch(m);        
    }
    
    @Override
    public Long create(Match m) {
        validateMatches(m, false);
        matchDao.create(m);
        return m.getId();
    }

    @Override
    public void update(Match m){
        validateMatches(m, true);
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
    public List<Match> filterActive(List<Match> par0) {
        return matchDao.filterActive(par0);
    }

    @Override
    public Match findById(Long id) {
        return matchDao.findById(id);
    }
    
    @Override
    public List<Match> findByTeam(Team t) {
       return matchDao.filterByTeam(t, null);
    }
    
    @Override
    public List<Match> findByTeams(Team t1, Team t2) {
       return matchDao.filterByTeams(t1, t2, null);
    }
    
    @Override
    public void addPlayerResult(Match m, PlayerResult r) {
        PlayerResult found = resultService.findByBoth(r.getPlayer(), m);
        
        if (found != null && found.getIsActive()) {
            throw new SoccerServiceException("Match already contains this player result. \n" +
                                        "Match: " + m.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
        validatePlayerResult(m, r);
	m.addPlayerResult(r);
    }
    
    @Override
    public void removePlayerResult(Match m, PlayerResult r) {
        PlayerResult found = resultService.findByBoth(r.getPlayer(), m);
        
        if (found == null || !found.getIsActive()) {
            throw new SoccerServiceException("Match doesn't contain this player result. \n" +
                                        "Match: " + m.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	m.removePlayerResult(r);
    }
    
    @Override
    public void updateTeamHomeScore(Match m) {

        List<PlayerResult> results = resultService.findByMatch(m);
        List<PlayerResult> active = resultService.filterActive(results);
        int goalsSum = 0;
        for (PlayerResult result : active) {
            if (m.getTeamHome() == result.getPlayer().getTeam()) { 
                goalsSum += result.getGoalsScored();
            }
        }
        m.setTeamHomeGoalsScored(goalsSum);
    }
 
    @Override
    public void updateTeamAwayScore(Match m) {
        List<PlayerResult> results = resultService.findByMatch(m);
        List<PlayerResult> active = resultService.filterActive(results);
        int goalsSum = 0;
        for (PlayerResult result : results) {
            if (m.getTeamAway() == result.getPlayer().getTeam()) { 
                goalsSum += result.getGoalsScored();
            }
        }
        m.setTeamAwayGoalsScored(goalsSum);
    }
        
    @Override
    public MatchResult getMatchResult(Match m) {
        MatchResult result = new MatchResult();
        result.setMatch(m);
        
        if(m.getTeamHomeGoalsScored() > m.getTeamAwayGoalsScored()) {
            result.setWinner(m.getTeamHome());
            result.setLooser(m.getTeamAway());
        }
        else if(m.getTeamAwayGoalsScored() > m.getTeamHomeGoalsScored()) {
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
        
        List<Match> matches = matchDao.filterByTeam(t, matchDao.findAll());
        
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