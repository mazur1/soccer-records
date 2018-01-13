/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;
import soccer.records.dto.MatchResultDto;
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamResultDto;

/**
 *
 * @author Michaela Bocanova
 */
public interface MatchFacade {
    Long createMatch(MatchCreateDto m);
    void updateMatch(MatchEditDto m);
    void deleteMatch(Long id);
    List<MatchDto> findAllMatches();
    MatchDto findMatchById(Long id);

    void addPlayerResult(Long mId, PlayerResultCreateDto rDto);

    void removePlayerResult(Long mId, Long rId);

    MatchResultDto getMatchResult(Long id);

    TeamResultDto getTeamResult(Long id);

    List<PlayerResultDto> getPlayerResults(Long id);

    List<MatchDto> filterActiveMatches(List<MatchDto> par0);
    
    void updateMatchScore(Long id);
    
}
