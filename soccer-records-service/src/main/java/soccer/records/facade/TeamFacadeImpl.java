/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.records.dto.MatchDto;
import soccer.records.dto.TeamCreateDto;
import soccer.records.dto.TeamDto;
import soccer.records.entity.Match;
import soccer.records.entity.Team;
import soccer.records.services.BeanMappingService;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;

/**
 *
 * @author Tomas Mazurek
 */
@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {
    
    @Inject
    private TeamService teamService;
    
    @Inject
    private PlayerService playerService;
    
    @Inject
    private MatchService matchService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public List<MatchDto> getMatchesHome(Long id) {
        Team t = teamService.findById(id);
        List<Match> matchesHome = t.getMatchesHome();
        return beanMappingService.mapTo(matchesHome, MatchDto.class);
        
    }
    
    @Override
    public List<MatchDto> getMatchesAway(Long id) {
        Team t = teamService.findById(id);
        List<Match> matchesAway = t.getMatchesAway();
        return beanMappingService.mapTo(matchesAway, MatchDto.class);
    }
    
    @Override
    public Long createTeam(TeamCreateDto t) {
        Team team = beanMappingService.mapTo(t, Team.class);
        teamService.create(team);
        return team.getId();
    }
    
    @Override
    public void updateTeam(TeamDto t) {
        Team mapped = beanMappingService.mapTo(t, Team.class);
        teamService.update(mapped);
    }
    
    @Override
    public void deleteTeam(Long id) {
        //in case is possible to have player with id null
        teamService.setNullAllPlayersByTeam(id);
//        for (Match m : teamService.findById(id).getMatchesAway()) {
//            m.setTeamAway(null);
//            matchService.update(m);
//        }
//        for (Match m : teamService.findById(id).getMatchesHome()) {
//            m.setTeamHome(null);
//            matchService.update(m);
//        }
        
        teamService.remove(teamService.findById(id));
    }

    @Override
    public List<TeamDto> findAllTeams() {
        return beanMappingService.mapTo(teamService.findAll(), TeamDto.class);
    }
    
    @Override
    public TeamDto findTeamById(Long id) {
        return beanMappingService.mapTo(teamService.findById(id), TeamDto.class);
    }
    
    @Override
    public List<TeamDto> findAllActiveTeams() {
        return beanMappingService.mapTo(teamService.findAllActive(), TeamDto.class);
    }
}
