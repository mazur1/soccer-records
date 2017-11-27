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

/**
 *
 * @author Michaela Bocanova
 */
public interface MatchFacade {
    Long createMatch(MatchDto m);
    void updateMatch(MatchDto m);
    void deleteMatch(Long id);
    List<MatchDto> findAllMatches();
    MatchDto findMatchById(Long id);
    
}
