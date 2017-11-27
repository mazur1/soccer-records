/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class PlayerResultFacadeImpl implements PlayerResultFacade{
 
    @Inject
    private PlayerService player;

    @Inject
    private MatchService match;

    @Inject
    private PlayerResultService playerResult;
    
    @Autowired
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PlayerResult mapped = beanMappingService.mapTo(t, PlayerResult.class);
        return playerResult.create(mapped);
    }

    @Override
    public void deletePlayerResult(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PlayerResultDto> findAllPlayerResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlayerResultDto findPlayerResultById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
