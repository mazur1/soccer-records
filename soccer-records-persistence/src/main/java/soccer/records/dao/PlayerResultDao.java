/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;

import soccer.records.entity.Player;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;

/**
 *
 * @author Radim Vidlák
 */
public interface PlayerResultDao {

    public void create(PlayerResult pr);

    public void update(PlayerResult pr);

    public void delete(PlayerResult pr);

    public List<PlayerResult> findByPlayer(Player p);

    public List<PlayerResult> findByMatch(Match m);

    public PlayerResult findByBoth(Player p, Match m);
    
    public List<PlayerResult> findAll();
    
}
