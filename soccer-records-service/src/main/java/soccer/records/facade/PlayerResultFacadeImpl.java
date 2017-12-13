/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dto.PlayerResultDto;
import soccer.records.services.BeanMappingService;

import soccer.records.services.PlayerService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;

import soccer.records.entity.PlayerResult;

/**
 *
 * @author radim
 */

@Service
public class PlayerResultFacadeImpl implements PlayerResultFacade{
 
    @Inject
    private PlayerService player;

    @Inject
    private MatchService match;

    @Inject
    private PlayerResultService playerResult;
    
    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void addGoal(Long matchID, Long playerID, int goals){
        playerResult.changeGoals(playerResult.findByBoth(player.findById(playerID), match.findById(matchID)), goals);
    }
    
    @Override
    public void removeGaol(Long matchID, Long playerID){
        playerResult.delete(playerResult.findByBoth(player.findById(playerID), match.findById(matchID)));
    }
    
    @Override
    public void removePlayerGoals(Long playerID){
        
        List<PlayerResult> pr = playerResult.findByPlayer(player.findById(playerID));
        
        for (PlayerResult e : pr) {
            playerResult.delete(e);
        }
        
    }
    
    @Override
    public void removeMatchGoals(Long matchID){
        
        List<PlayerResult> pr = playerResult.findByMatch(match.findById(matchID));
        
        for (PlayerResult e : pr) {
            playerResult.delete(e);
        }
        
    }

    @Override
    public Long createPlayerResult(PlayerResultDto t) {
        PlayerResult mapped = beanMappingService.mapTo(t, PlayerResult.class);
        return playerResult.create(mapped);
    }

    @Override
    public void deletePlayerResult(Long id) {
        playerResult.delete(playerResult.findByID(id));
    }

    @Override
    public List<PlayerResultDto> findAllPlayerResults() {
        return beanMappingService.mapTo(playerResult.findAll(), PlayerResultDto.class);
    }

    @Override
    public PlayerResultDto findPlayerResultById(Long id) {
        return beanMappingService.mapTo(playerResult.findByID(id), PlayerResultDto.class);
    }

    @Override
    public void updatePlayerResult(PlayerResultDto t) {
        PlayerResult mapped = beanMappingService.mapTo(t, PlayerResult.class);
        playerResult.update(mapped);
    }

}
