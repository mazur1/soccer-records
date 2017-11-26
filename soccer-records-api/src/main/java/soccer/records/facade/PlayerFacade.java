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
 
    public Long createPlayer(PlayerDto t);
    public void deletePlayer(Long id);
    List<PlayerDto> findAllPlayer();
    PlayerDto findPlayerById(Long id);
    
}
