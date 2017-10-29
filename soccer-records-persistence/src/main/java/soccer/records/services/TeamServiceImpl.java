package soccer.records.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dao.TeamDao;
import soccer.records.entity.Team;


/**
 *
 * @author Tomas
 */
@Service
public class TeamServiceImpl {
    
    @Autowired
    private TeamDao teamDao;


    public void create(Team t) {
        teamDao.create(t);
    }

    public List<Team> findAll() {
        return teamDao.findAll();
    }

    public Team findById(Long id) {
        return teamDao.findById(id);
    }

    public void remove(Team t) throws IllegalArgumentException {
        teamDao.delete(t);
    }

    public Team findByName(String name) {
        return teamDao.findByName(name);
    }    
}
