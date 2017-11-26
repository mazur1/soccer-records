/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import soccer.records.dto.MatchDto;

/**
 *
 * @author Michaela Bocanova
 */
public interface MatchFacade {
    public Long createMatch(MatchDto m);
    public void deleteMatch(Long id);
    public List<MatchDto> findAllMatches();
    public MatchDto findMatchById(Long id);
    void addPlayerResult(Long m, Long r);
}
