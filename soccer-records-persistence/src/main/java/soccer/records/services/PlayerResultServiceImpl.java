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

/**
 * Created by ... on 24.10.2017
 */

@Service
public class PlayerResultServiceImpl {
    
    @Autowired
    private PlayerResultDao playerResultDao;

    public void create(PlayerResult pr){
        playerResultDao.create(pr);
    }

    public void update(PlayerResult pr){
        playerResultDao.update(pr);
    }

    public void delete(PlayerResult pr){
        playerResultDao.delete(pr);
    }

    public List<PlayerResult> findByPlayer(Player p){
        return playerResultDao.findByPlayer(p);
    }

    public List<PlayerResult> findByMatch(Match m){
         return playerResultDao.findByMatch(m);
    }
 
    public PlayerResult findByBoth(Player p, Match m){
         return playerResultDao.findByBoth(p,m);
    }
    
}
