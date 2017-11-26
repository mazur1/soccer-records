/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import soccer.records.dto.PlayerDto;
import soccer.records.entity.Player;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerService;

/**
 *
 * @author 
 */
public class PlayerFacadeImpl {
    
        
    @Inject
    private PlayerService playerService;
    
    @Autowired
    
    private BeanMappingService beanMappingService;
    
    public void createPlayer(PlayerDto p) {
        Player player = beanMappingService.mapTo(p, Player.class);
        playerService.create(player); // create musi vratit id
    }
    
    public void updatePlayer(PlayerDto p) {
        Player mapped = beanMappingService.mapTo(p, Player.class);
        playerService.update(mapped);
    }
    
    public void deletePlayer(Long id) {
        playerService.remove(playerService.findById(id));
    }

    List<PlayerDto> findAllPlayers() {
        return beanMappingService.mapTo(playerService.findAll(), PlayerDto.class);
    }
    
    PlayerDto findPlayerById(Long id) {
        return beanMappingService.mapTo(playerService.findById(id), PlayerDto.class);
    }
    
}
