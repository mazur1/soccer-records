package soccer.records.services;

import soccer.records.dao.PlayerDao;
import soccer.records.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import soccer.records.dao.PlayerResultDao;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.exceptions.ServiceException;

/**
 * Created by ... on 24.10.2017
 */

@Service
public class PlayerResultServiceImpl implements PlayerResultService {
    
    @Autowired
    private PlayerResultDao playerResultDao;

    @Override
    public void create(PlayerResult pr){
        playerResultDao.create(pr);
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
    public PlayerResult findByID(Long id){
        return playerResultDao.findByID(id);
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
    public void changeGoals(PlayerResult pr, int goals){
        
        if(goals <= 0){
            throw new ServiceException("Poèet gólù nemùže být Záporný ani nulový");
        }
        
        pr.setGoalsScored(goals);
    }
    
}
