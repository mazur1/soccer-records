/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.List;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;

/**
 *
 * @author 
 */
public interface PlayerResultService {

    void create(PlayerResult pr);

    void delete(PlayerResult pr);

    List<PlayerResult> findAll();

    PlayerResult findByBoth(Player p, Match m);

    List<PlayerResult> findByMatch(Match m);

    List<PlayerResult> findByPlayer(Player p);

    void update(PlayerResult pr);
    
}
