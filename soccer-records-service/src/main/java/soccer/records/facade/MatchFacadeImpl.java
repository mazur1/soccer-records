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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;
import soccer.records.entity.Match;
import soccer.records.exceptions.ServiceException;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;
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
    private PlayerResultService resultService;
    @Autowired
    private BeanMappingService beanMappingService;
            
    @Override
    public Long createMatch(MatchDto m) {
        Match mapped = beanMappingService.mapTo(m, Match.class);
        return matchService.create(mapped);
    }
    
    @Override
    public void updateMatch(MatchDto m) {
        Match mapped = beanMappingService.mapTo(m, Match.class);
        matchService.update(mapped);
    }

    @Override
    public void deleteMatch(Long id) {
        matchService.delete(matchService.findById(id));
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return beanMappingService.mapTo(matchService.findAll(), MatchDto.class);
    }

    @Override
    public MatchDto findMatchById(Long id) {
        return beanMappingService.mapTo(matchService.findById(id), MatchDto.class);
    }
    
}
