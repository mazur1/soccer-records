/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;
import soccer.records.dto.MatchResultDto;
import soccer.records.dto.PlayerResultCreateDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamResultDto;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;

/**
 *
 * @author Michaela Bocanova
 */

@Service
@Transactional
public class MatchFacadeImpl implements MatchFacade {

    final static Logger log = LoggerFactory.getLogger(MatchFacadeImpl.class);

    @Inject
    private MatchService matchService;
    @Inject
    private TeamService teamService;
    @Inject
    private PlayerService playerService;
    @Inject
    private PlayerResultService resultService;
    @Inject
    private BeanMappingService beanMappingService;
            
    @Override
    public Long createMatch(MatchCreateDto m) {
        Match mapped = beanMappingService.mapTo(m, Match.class);
        mapped.setTeamAway(teamService.findById(m.getTeamAwayId()));
        mapped.setTeamHome(teamService.findById(m.getTeamHomeId()));
        
        return matchService.create(mapped);
    }
    
    @Override
    public void updateMatch(MatchEditDto m) {
        Match mapped = beanMappingService.mapTo(m, Match.class);
        mapped.setTeamHome(teamService.findById(m.getTeamHomeId()));
        mapped.setTeamAway(teamService.findById(m.getTeamAwayId()));
        
        matchService.update(mapped);
    }

    @Override
    public void deleteMatch(Long id) {
        matchService.delete(matchService.findById(id));
    }

    @Override
    public List<MatchDto> findAllMatches() {
        List<Match> matches = matchService.findAll();
        List<MatchDto> mapped = beanMappingService.mapTo(matches, MatchDto.class);
        return mapped;
    }

    @Override
    public MatchDto findMatchById(Long id) {
        return beanMappingService.mapTo(matchService.findById(id), MatchDto.class);
    }
    
    @Override
    public void addPlayerResult(Long mId, PlayerResultCreateDto rDto) {
        Match m = matchService.findById(mId);
        PlayerResult r = beanMappingService.mapTo(rDto, PlayerResult.class);
        r.setPlayer(playerService.findById(rDto.getPlayerId()));
        matchService.addPlayerResult(m, r);
    }
    
    @Override
    public void removePlayerResult(Long mId, Long rId) {
        Match m = matchService.findById(mId);
        PlayerResult r = resultService.findById(rId);
        matchService.removePlayerResult(m, r);
    }
        
    @Override
    public MatchResultDto getMatchResult(Long id) {
        return beanMappingService.mapTo(matchService.getMatchResult(
                matchService.findById(id)), MatchResultDto.class);
    }
    
    @Override
    public TeamResultDto getTeamResult(Long id) {
        return beanMappingService.mapTo(matchService.getTeamResult(
                teamService.findById(id)), TeamResultDto.class);
    }
    
    @Override
    public List<PlayerResultDto> getPlayerResults(Long id) {
        Match m = matchService.findById(id);
        List<PlayerResult> results = resultService.findByMatch(m);
        return beanMappingService.mapTo(results, PlayerResultDto.class);
    }
}
