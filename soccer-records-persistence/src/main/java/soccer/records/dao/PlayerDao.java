package soccer.records.dao;

import java.util.List;
import soccer.records.entity.Player;


public interface PlayerDao {
    public Player findById(Long id);
    public void create(Player c);
    public void update(Player c);
    public void delete(Player c);
    public List<Player> findAll();
    public List<Player> findByName(String name);
}
