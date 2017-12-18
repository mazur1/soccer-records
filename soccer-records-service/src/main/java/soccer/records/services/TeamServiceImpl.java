package soccer.records.services;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dao.PlayerDao;
import soccer.records.dao.TeamDao;
import soccer.records.entity.Match;
import soccer.records.entity.Team;
import soccer.records.entity.Player;
import soccer.records.exceptions.service.SoccerServiceException;


/**
 *
 * @author Tomas Mazurek
 */
@Service
public class TeamServiceImpl implements TeamService {
    
    @Inject
    private TeamDao teamDao;
    
    @Inject
    private PlayerDao playerDao;

    @Override
    public Long create(Team t) {
        teamDao.create(t);
        return t.getId();
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
        
    @Override
    public void addPlayer(Team t, Player p) {
	if (t.getPlayers().contains(p)) {
            throw new SoccerServiceException("Team already contais this player. \n" +
                                        "Team: " + t.getId() + "\n" +
                                        "Player: " + p.getId());
	}
	t.addPlayer(p);
    }
    
    @Override
    public void setNullAllPlayersByTeam(Long id) {
        List<Player> players = teamDao.findById(id).getPlayers();
        for (Player p : players) {
            p.addTeam(null);
            playerDao.update(p);
        }
        
    }

    @Override
    public void removePlayer(Team t, Player p) {
	if (!t.getPlayers().contains(p)) {
            throw new SoccerServiceException("Team hasn't contains this player. \n" +
                                        "Team: " + t.getId() + "\n" +
                                        "Player: " + p.getId());
	}
        
	t.removePlayer(p);
    } 

    @Override
    public void addMatchHome(Team t, Match m) {
	if (t.getMatchesHome().contains(m)) {
            throw new SoccerServiceException("Team already contais this match home. \n" +
                                        "Team: " + t.getId() + "\n" +
                                        "Match: " + m.getId());
	}
	t.addMatchHome(m);
    }

    @Override
    public void removeMatchHome(Team t, Match m) {
	if (!t.getMatchesHome().contains(m)) {
            throw new SoccerServiceException("Team hasn't contains this match home. \n" +
                                        "Team: " + t.getId() + "\n" +
                                        "Match: " + m.getId());
	}
        
	t.removeMatchHome(m);
    } 

    @Override
    public void addMatchAway(Team t, Match m) {
	if (t.getMatchesAway().contains(m)) {
            throw new SoccerServiceException("Team already contais this match home. \n" +
                                        "Team: " + t.getId() + "\n" +
                                        "Match: " + m.getId());
	}
	t.addMatchAway(m);
    }

    @Override
    public void removeMatchAway(Team t, Match m) {
	if (!t.getMatchesAway().contains(m)) {
            throw new SoccerServiceException("Team hasn't contains this player. \n" +
                                        "Team: " + t.getId() + "\n" +
                                        "Match: " + m.getId());
	}
        
	t.addMatchAway(m);
    }     
}
