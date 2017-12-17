/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import soccer.records.dto.PlayerCreateDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;

/**
 *
 * @author 
 */
@Service
@Transactional
public class PlayerFacadeImpl implements PlayerFacade {
        
    @Inject
    private PlayerResultService resultService;
    
    @Inject
    private PlayerService playerService;
    
    @Inject    
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createPlayer(PlayerCreateDto p) {
        Player player = new Player();
        player.setName(p.getName());
        player.setSurname(p.getSurname());
        player.setAge(p.getAge());
        player.setPost(p.getPost());
        player.setCaptain(p.isCaptain());
        player.setCountry(p.getCountry());
        player.setCity(p.getCity());
        playerService.create(player);
        return player.getId();
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
    
    public List<PlayerResultDto> getPlayerResults(Long id) {
        Player p = playerService.findById(id);
        List<PlayerResult> results = resultService.findByPlayer(p);
        return beanMappingService.mapTo(results, PlayerResultDto.class);
    }
    
}
