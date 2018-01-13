package soccer.records.services;

import soccer.records.entity.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import soccer.records.dao.PlayerResultDao;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.exceptions.service.SoccerServiceException;

/**
 * Created by ?, Michaela Bocanova on 24.10.2017
 */

@Service
public class PlayerResultServiceImpl implements PlayerResultService {
    
    @Inject
    private PlayerResultDao playerResultDao;

    /**
     * Helper method to validate match before create/update
     * @return 
     */
    private void validate(PlayerResult pr) {
        if(pr.getMatch() == null)
            throw new SoccerServiceException("Can't create a player result without a match");
        if(pr.getPlayer() == null)
            throw new SoccerServiceException("Can't create a player result without a player");   
    }
    
    @Override
    public Long create(PlayerResult pr){
        validate(pr);
        playerResultDao.create(pr);
        return pr.getId();
    }

    @Override
    public void update(PlayerResult pr){
        playerResultDao.update(pr);
    }

    @Override
    public void delete(PlayerResult pr){
        playerResultDao.delete(pr);
    }
    
    @Override
    public PlayerResult findById(Long id){
        return playerResultDao.findById(id);
    }

    @Override
    public List<PlayerResult> findByPlayer(Player p){
        return playerResultDao.findByPlayerID(p.getId());
    }

    @Override
    public List<PlayerResult> findByMatch(Match m){
         return playerResultDao.findByMatchID(m.getId());
    }
 
    @Override
    public PlayerResult findByBoth(Player p, Match m){
         return playerResultDao.findByBoth(p.getId(),m.getId());
    }
    
    @Override
    public List<PlayerResult> findAll() {
        return playerResultDao.findAll();
    }
    
    @Override
    public List<PlayerResult> findAllActive() {
        return playerResultDao.findAll();
    }
    
    @Override
    public List<PlayerResult> filterActive(List<PlayerResult> par0) {
        return playerResultDao.filterActive(par0);
    }
    
    @Override
    public void changeGoals(PlayerResult pr, int goals){
        
        if(goals <= 0){
            throw new SoccerServiceException("Poèet gólù nemùže být Záporný ani nulový");
        }
        
        pr.setGoalsScored(goals);
    }
    
    @Override
    public int getPlayerResult(Player p) {//statistics
       
        List<PlayerResult> results = playerResultDao.findByPlayerID(p.getId());
        int total = 0;
        for(PlayerResult r : results) {
            total += r.getGoalsScored();
        }
                
        return total;
    }
    
    @Override
    public void checkGoalsScoredInMatch(Match m) {//optional check
        List<PlayerResult> results = playerResultDao.findByMatchID(m.getId());
        int totalHome = 0, totalAway = 0;
        for(PlayerResult r : results) {
            if(Objects.equals(r.getPlayer().getTeam(), m.getTeamHome()))
                totalHome += r.getGoalsScored();
            else if(Objects.equals(r.getPlayer().getTeam(), m.getTeamAway()))
                totalAway += r.getGoalsScored();
        }
        
        if(m.getTeamHomeGoalsScored() != totalHome)
            throw new SoccerServiceException("total number of goals scored by players in team home is different from the score of match");
        if(m.getTeamAwayGoalsScored() != totalAway)
            throw new SoccerServiceException("total number of goals scored by players in team away is different from the score of match");
    }
    
}
