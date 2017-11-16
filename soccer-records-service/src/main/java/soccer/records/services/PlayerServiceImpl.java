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
public class PlayerServiceImpl implements PlayerService {
    
    @Autowired
    private PlayerDao playerDao;

    @Override
    public void create(Player p) {
        playerDao.create(p);
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
    public List<Player> findByName(String name, String surname) {
        return playerDao.findByName(name, surname);
    }
}
