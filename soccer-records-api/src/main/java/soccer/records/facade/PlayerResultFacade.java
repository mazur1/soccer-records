/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

/**
 *
 * @author radim
 */
public interface PlayerResultFacade {
    
    void addGoal(Long matchID, Long playerID, int goals);
    void removeGaol(Long matchID, Long playerID);
    void removePlayerGoals(Long playerID);
    void removeMatchGoals(Long matchID);
    
}
