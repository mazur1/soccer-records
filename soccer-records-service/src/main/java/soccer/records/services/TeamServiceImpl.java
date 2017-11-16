package soccer.records.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dao.TeamDao;
import soccer.records.entity.Match;
import soccer.records.entity.Team;


/**
 *
 * @author Tomas
 */
@Service
public class TeamServiceImpl implements TeamService {
    
    @Autowired
    private TeamDao teamDao;


    @Override
    public void create(Team t) {
        teamDao.create(t);
    }
    
    @Override
    public void update(Team t){
        teamDao.update(t);
    }

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public Team findById(Long id) {
        return teamDao.findById(id);
    }

    @Override
    public void remove(Team t) throws IllegalArgumentException {
        teamDao.delete(t);
    }

    @Override
    public Team findByName(String name) {
        return teamDao.findByName(name);
    }    
}
