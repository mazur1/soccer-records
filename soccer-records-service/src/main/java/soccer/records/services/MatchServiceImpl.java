package soccer.records.services;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dao.MatchDao;
import soccer.records.entity.Match;
import soccer.records.entity.Team;


/**
 * CRUD methods in service layer using MatchDao repository
 * 
 * @author Michaela Bocanova
 */
@Service
public class MatchServiceImpl implements MatchService {
    
    @Inject
    private MatchDao matchDao;


    @Override
    public void create(Match m) {
        matchDao.create(m);
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
}
