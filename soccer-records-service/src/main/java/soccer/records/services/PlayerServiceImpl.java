package soccer.records.services;

import soccer.records.dao.PlayerDao;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.Inject;
import soccer.records.exceptions.service.SoccerServiceException;

/**
 * Created by ... on 24.10.2017
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    
    @Inject
    private PlayerDao playerDao;

    @Override
    public Long create(Player p) {
        playerDao.create(p);
        return p.getId();
    }

    @Override
    public List<Player> findAll() {
        return playerDao.findAll();
    }

    @Override
    public Player findById(Long id) {
        return playerDao.findById(id);
    }

    @Override
    public void update(Player p) {
        playerDao.update(p);
    }
    
    @Override
    public void remove(Player p) throws IllegalArgumentException {
        playerDao.delete(p);
    }
    
    @Override
    public void addPlayerResult(Player p, PlayerResult r) {
	if (p.getPlayerResults().contains(r)) {
            throw new SoccerServiceException("Player already contais this player result. \n" +
                                        "Match: " + p.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	p.addPlayerResult(r);
    }

    @Override
    public void removePlayerResult(Player p, PlayerResult r) {
	if (!p.getPlayerResults().contains(r)) {
            throw new SoccerServiceException("Player hasn't contains this player result. \n" +
                                        "Match: " + p.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	p.removePlayerResult(r);
    }    

    @Override
    public List<Player> findByName(String name, String surname) {
        return playerDao.findByName(name, surname);
    }
}
