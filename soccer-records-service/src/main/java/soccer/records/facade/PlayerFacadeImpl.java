/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dto.PlayerCreateDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerEditDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.services.BeanMappingService;

import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;

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
    private TeamService teamService;
    
    @Inject    
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createPlayer(PlayerCreateDto p) {
        
        Player mapped = beanMappingService.mapTo(p, Player.class);       
        mapped.setTeam(teamService.findById(p.getTeamId()));      
        playerService.create(mapped);    
        return mapped.getId();
    }
    
    @Override
    public void updatePlayer(PlayerEditDto p) {
        Player mapped = beanMappingService.mapTo(p, Player.class);
        
        mapped.setTeam(teamService.findById(p.getTeamId()));
        
        for (Long item : p.getPlayerResults()) {
            mapped.addPlayerResult(resultService.findById(item));
        }
        
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
    
    @Override
    public List<PlayerResultDto> getPlayerResults(Long id) {
        Player p = playerService.findById(id);
        List<PlayerResult> results = resultService.findByPlayer(p);
        return beanMappingService.mapTo(results, PlayerResultDto.class);
    }
    
    @Override
    public List<PlayerDto> findAllActivePlayers() {
        return beanMappingService.mapTo(playerService.findAllActive(), PlayerDto.class);
    }
    
    @Override
    public List<PlayerDto> filterActivePlayers(List<PlayerDto> par0) {
        List<Player> mapped = beanMappingService.mapTo(par0, Player.class);
        return beanMappingService.mapTo(playerService.filterActive(mapped), PlayerDto.class);
    }
}
