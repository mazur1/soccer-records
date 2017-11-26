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
import soccer.records.dto.MatchDto;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultService;

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
    private PlayerResultService resultService;
    @Autowired
    private BeanMappingService beanMappingService;
        
    @Override
    public Long createMatch(MatchDto m) {
        Match mapped = beanMappingService.mapTo(m, Match.class);
        //mapped.addPlayerResult();
        return matchService.create(mapped);
    }
    
    //public void update(Match m){}

    @Override
    public void deleteMatch(Long id) {
        matchService.delete(new Match(id));
    }

    @Override
    public List<MatchDto> findAllMatches() {
        return beanMappingService.mapTo(matchService.findAll(), MatchDto.class);
    }

    @Override
    public MatchDto findMatchById(Long id) {
        return beanMappingService.mapTo(matchService.findById(id), MatchDto.class);
    }
    
    @Override
    public void addPlayerResult(Long m, Long r) {
        //matchService.addPlayerResults(matchService.findById(m), resultService.findById(r));
    }
}
