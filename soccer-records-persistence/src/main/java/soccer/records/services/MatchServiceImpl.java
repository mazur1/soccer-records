package soccer.records.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dao.MatchDao;
import soccer.records.entity.Match;


/**
 *
 * @author Tomas
 */
@Service
@Transactional
public class MatchServiceImpl {
    
    @Autowired
    private MatchDao matchDao;


    public void create(Match m) {
        matchDao.create(m);
    }

    public List<Match> findAll() {
        return matchDao.findAll();
    }

    public Match findById(Long id) {
        return matchDao.findById(id);
    }

    public void remove(Match m) throws IllegalArgumentException {
        matchDao.delete(m);
    }

}
