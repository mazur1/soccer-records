/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import soccer.records.dto.TeamDto;

/**
 *
 * @author Tomas Mazurek
 */
public interface TeamFacade {
    public Long createTeam(TeamDto t);
    public void deleteTeam(Long id);
    List<TeamDto> findAllTeams();
    TeamDto findTeamById(Long id);
}
