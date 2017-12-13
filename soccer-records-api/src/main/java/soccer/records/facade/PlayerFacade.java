/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import soccer.records.dto.PlayerDto;

/**
 *
 * @author 
 */
public interface PlayerFacade {
 
    Long createPlayer(PlayerDto t);
    void updatePlayer(PlayerDto t);
    void deletePlayer(Long id);
    List<PlayerDto> findAllPlayers();
    PlayerDto findPlayerById(Long id);
    
}
