package soccer.records.services;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dao.MatchDao;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.exceptions.ServiceException;


/**
 * Implementation of business logic using repository MatchDao.
 * 
 * @author Michaela Bocanova
 */
@Service
public class MatchServiceImpl implements MatchService {
    
    @Inject
    private MatchDao matchDao;


    @Override
    public Long create(Match m) {
        matchDao.create(m);
        return m.getId();
    }

    @Override
    public void update(Match m){
        matchDao.update(m);
    }

    @Override
    public void delete(Match m){
        matchDao.delete(m);
    }
    
    @Override
    public List<Match> findAll() {
        return matchDao.findAll();
    }

    @Override
    public Match findById(Long id) {
        return matchDao.findById(id);
    }
    
    @Override
    public List<Match> findByTeam(Team t) {
       return matchDao.findByTeam(t);
    }
    
    @Override
    public void addPlayerResult(Match m, PlayerResult r) {
	if (m.getPlayerResults().contains(r)) {
            throw new ServiceException("Match already contais this player result. \n" +
                                        "Match: " + m.getId() + "\n" +
                                        "Player result: " + r.getId());
	}
	m.addPlayerResult(r);
    }
}
