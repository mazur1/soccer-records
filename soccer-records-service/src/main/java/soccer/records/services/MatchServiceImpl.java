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
    
    @Override
    public void removePlayerResult(Match m, PlayerResult r) {
        
	m.removePlayerResult(r);
    }
    
    static class MatchResult {
        
        private Match match;
        private Team winner;
        private Team looser;
        private boolean tie=false;

        public MatchResult() {
        }
        
        public Match getMatch() {
            return match;
        }

        public void setMatch(Match match) {
            this.match = match;
        }

        public Team getWinner() {
            return winner;
        }

        public void setWinner(Team winner) {
            this.winner = winner;
        }

        public Team getLooser() {
            return looser;
        }

        public void setLooser(Team looser) {
            this.looser = looser;
        }

        public boolean isTie() {
            return tie;
        }

        public void setTie(boolean tie) {
            this.tie = tie;
        }
        
    }
    
    static class TeamResult {

        private Team team;
        private int wins=0;
        private int losses=0;
        private int ties=0;
        
        public TeamResult() {
        }
        
        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public int getWins() {
            return wins;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public int getLosses() {
            return losses;
        }

        public void setLosses(int losses) {
            this.losses = losses;
        }

        public int getTies() {
            return ties;
        }

        public void setTies(int ties) {
            this.ties = ties;
        }
                
    }
    
    @Override
    public MatchResult getMatchResult(Match m) {
        MatchResult result = new MatchResult();
        result.match = m;
        
        if(m.getTeamHomeGoalsScored(false) > m.getTeamHomeGoalsReceived(false)) {
            result.winner = m.getTeamHome();
            result.looser = m.getTeamAway();
        }
        else if(m.getTeamAwayGoalsScored(false) > m.getTeamAwayGoalsReceived(false)) {
            result.winner= m.getTeamAway();
            result.looser=m.getTeamHome();
        }
        else result.tie = true;
        
        return result;
    }
    
    @Override
    public TeamResult getTeamResult(Team t) {
        /*Map<Team, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> sub = new HashMap<>();*/
        TeamResult tResult = new TeamResult();
        tResult.team = t;
        List<Match> matches = matchDao.findByTeam(t);
        
        int wins=0, losses=0,ties=0;
        for(Match m : matches) {
            MatchResult mResult = getMatchResult(m);
            if (mResult.tie)
                tResult.ties++;
            else if (mResult.winner != null && mResult.winner.equals(t))
                tResult.wins++;
            else if (mResult.looser != null && mResult.looser.equals(t))
                tResult.losses++;
        }
        
        return tResult;
    }

    
}