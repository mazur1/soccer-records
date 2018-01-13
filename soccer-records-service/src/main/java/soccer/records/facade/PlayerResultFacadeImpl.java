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
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.PlayerResultEditDto;
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
@Transactional
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
    public Long createPlayerResult(PlayerResultCreateDto t) {
        PlayerResult mapped = beanMappingService.mapTo(t, PlayerResult.class);
        
        mapped.setMatch(match.findById(t.getMatchId()));
        mapped.setPlayer(player.findById(t.getPlayerId()));        
        
        return playerResult.create(mapped);
    }

    @Override
    public void deletePlayerResult(Long id) {
        playerResult.delete(playerResult.findById(id));
    }

    @Override
    public List<PlayerResultDto> findAllPlayerResults() {
        return beanMappingService.mapTo(playerResult.findAll(), PlayerResultDto.class);
    }

    @Override
    public PlayerResultDto findPlayerResultById(Long id) {
        return beanMappingService.mapTo(playerResult.findById(id), PlayerResultDto.class);
    }

    @Override
    public void updatePlayerResult(PlayerResultEditDto t) {
        PlayerResult mapped = beanMappingService.mapTo(t, PlayerResult.class);
        
        mapped.setMatch(match.findById(t.getMatchId()));
        mapped.setPlayer(player.findById(t.getPlayerId()));  
        
        playerResult.update(mapped);
    }

    @Override
    public void checkGoalsScoredInMatch(Long id) {
        playerResult.checkGoalsScoredInMatch(match.findById(id));
    }

    @Override
    public int getPlayerResult(Long id) {
        return playerResult.getPlayerResult(player.findById(id));
    }
    
    @Override
    public List<PlayerResultDto> filterActivePlayerResults(List<PlayerResultDto> par0) {
        List<PlayerResult> mapped = beanMappingService.mapTo(par0, PlayerResult.class);
        return beanMappingService.mapTo(playerResult.filterActive(mapped), PlayerResultDto.class);
    }
}
