/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.PlayerResultEditDto;

/**
 *
 * @author radim
 */
public interface PlayerResultFacade {
    
    void addGoal(Long matchID, Long playerID, int goals);
    void removeGaol(Long matchID, Long playerID);
    void removePlayerGoals(Long playerID);
    void removeMatchGoals(Long matchID);
    
    Long createPlayerResult(PlayerResultCreateDto t);
    void updatePlayerResult(PlayerResultEditDto t);
    void deletePlayerResult(Long id);
    List<PlayerResultDto> findAllPlayerResults();
    PlayerResultDto findPlayerResultById(Long id);

    void checkGoalsScoredInMatch(Long id);

    int getPlayerResult(Long id);

    List<PlayerResultDto> findAllActivePlayerResults();
    List<PlayerResultDto> filterActiveTeams(List<PlayerResultDto> par0);
}
