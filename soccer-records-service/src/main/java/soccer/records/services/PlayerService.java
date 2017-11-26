/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.List;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;

/**
 *
 * @author 
 */
public interface PlayerService {

    void create(Player p);

    List<Player> findAll();

    Player findById(Long id);

    List<Player> findByName(String name, String surname);

    void remove(Player p) throws IllegalArgumentException;

    void update(Player p);
    
    void addPlayerResult(Player p, PlayerResult r);
    void removePlayerResult(Player p, PlayerResult r);
    
}
