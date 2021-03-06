/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import soccer.records.dto.PlayerCreateDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerEditDto;
import soccer.records.dto.PlayerResultDto;


public interface PlayerFacade {
 
    Long createPlayer(PlayerCreateDto p);
    void updatePlayer(PlayerEditDto p);
    void deletePlayer(Long id);
    List<PlayerDto> findAllPlayers();
    PlayerDto findPlayerById(Long id);

    List<PlayerResultDto> getPlayerResults(Long id);

    List<PlayerDto> findAllActivePlayers();
    List<PlayerDto> filterActivePlayers(List<PlayerDto> par0);
    
}
