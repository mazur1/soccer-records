/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import soccer.records.dto.TeamDto;
import soccer.records.entity.Team;
import soccer.records.services.BeanMappingService;
import soccer.records.services.TeamService;

/**
 *
 * @author Tomas Mazurek
 */
@Service
public class TeamFacadeImpl implements TeamFacade {
    
    @Inject
    private TeamService teamService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public Long createTeam(TeamDto t) {
        Team team = beanMappingService.mapTo(t, Team.class);
        return teamService.create(team); 
    }
    
    @Override
    public void updateTeam(TeamDto t) {
        Team mapped = beanMappingService.mapTo(t, Team.class);
        teamService.update(mapped);
    }
    
    @Override
    public void deleteTeam(Long id) {
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
}
