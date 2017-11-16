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
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;

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
	
    @Autowired
    private BeanMappingService beanMappingService;
        
    @Override
    public Long createMatch(MatchDto m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMatch(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MatchDto> getAllMatches() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MatchDto getMatchById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
