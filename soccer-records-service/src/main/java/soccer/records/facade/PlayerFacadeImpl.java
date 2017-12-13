/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dto.PlayerDto;
import soccer.records.entity.Player;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerService;

/**
 *
 * @author 
 */
@Service
public class PlayerFacadeImpl implements PlayerFacade {
        
    @Inject
    private PlayerService playerService;
    
    @Inject    
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createPlayer(PlayerDto p) {
        Player player = beanMappingService.mapTo(p, Player.class);
        return playerService.create(player); 
    }
    
    @Override
    public void updatePlayer(PlayerDto p) {
        Player mapped = beanMappingService.mapTo(p, Player.class);
        playerService.update(mapped);
    }
    
    @Override
    public void deletePlayer(Long id) {
        playerService.remove(playerService.findById(id));
    }

    @Override
    public List<PlayerDto> findAllPlayers() {
        return beanMappingService.mapTo(playerService.findAll(), PlayerDto.class);
    }
    
    @Override
    public PlayerDto findPlayerById(Long id) {
        return beanMappingService.mapTo(playerService.findById(id), PlayerDto.class);
    }
    
}
