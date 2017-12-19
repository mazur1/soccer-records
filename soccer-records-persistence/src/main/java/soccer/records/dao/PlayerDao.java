package soccer.records.dao;

import java.util.List;
import soccer.records.entity.Player;
import soccer.records.exceptions.dao.DataAccessExceptions;


public interface PlayerDao extends DefaultCrudDao<Player,Long> {
    //public Player findById(Long id);
    public Player findByPlayer(Player p) throws DataAccessExceptions;
    //public void create(Player c);
    //public void update(Player c);
    //public void delete(Player c);
    //public List<Player> findAll();
    public List<Player> findByName(String name,  String surname) throws DataAccessExceptions;

    List<Player> findAllActive() throws DataAccessExceptions;

    List<Player> findAll() throws DataAccessExceptions;

}
