package soccer.records.services;

import soccer.records.dao.PlayerDao;
import soccer.records.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ... on 24.10.2017
 */
@Service
public class PlayerServiceImpl {
    
    @Autowired
    private PlayerDao playerDao;

    public void create(Player p) {
        playerDao.create(p);
    }

    public List<Player> findAll() {
        return playerDao.findAll();
    }

    public Player findById(Long id) {
        return playerDao.findById(id);
    }

    public void update(Player p) {
        playerDao.update(p);
    }
    
    public void remove(Player p) throws IllegalArgumentException {
        playerDao.delete(p);
    }

    public List<Player> findByName(String name) {
        return playerDao.findByName(name);
    }
}
