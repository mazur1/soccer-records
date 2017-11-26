/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.services.BeanMappingService;

import soccer.records.services.PlayerService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;

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
        
    }
    
    @Override
    public void removeGaol(Long matchID, Long playerID){
        
    }
    
    @Override
    public void removePlayerGoals(Long playerID){
        
    }
    
    @Override
    public void removeMatchGoals(Long matchID){
        
    }
    
}
