/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.LocationDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;

/**
 *
 * @author Michaela Bocanova
 */
@Relation(value = "match", collectionRelation = "matches")
@JsonPropertyOrder({"id", "teamHome", "teamAway", "dateAndTime", "location", "teamHomeGoalsScored", "teamAwayGoalsScored"})
public class MatchResource extends ResourceSupport {
    
    @JsonProperty("id") 
    private Long dtoId;
    private TeamDto teamHome;
    private TeamDto teamAway;
    private Date dateAndTime;
    private LocationDto location;
    private Integer teamHomeGoalsScored;
    private Integer teamAwayGoalsScored;
    private Integer teamHomeGoalsScoredHalf;
    private Integer teamAwayGoalsScoredHalf;

    private List<PlayerResultDto> playerResults = new ArrayList<>();
    
    public MatchResource(MatchDto dto) {
        this.dtoId = dto.getId();
        teamHome = dto.getTeamHome();
        teamAway = dto.getTeamAway();
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public TeamDto getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(TeamDto teamHome) {
        this.teamHome = teamHome;
    }

    public TeamDto getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(TeamDto teamAway) {
        this.teamAway = teamAway;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Integer getTeamHomeGoalsScored() {
        return teamHomeGoalsScored;
    }

    public void setTeamHomeGoalsScored(Integer teamHomeGoalsScored) {
        this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public Integer getTeamAwayGoalsScored() {
        return teamAwayGoalsScored;
    }

    public void setTeamAwayGoalsScored(Integer teamAwayGoalsScored) {
        this.teamAwayGoalsScored = teamAwayGoalsScored;
    }

    public Integer getTeamHomeGoalsScoredHalf() {
        return teamHomeGoalsScoredHalf;
    }

    public void setTeamHomeGoalsScoredHalf(Integer teamHomeGoalsScoredHalf) {
        this.teamHomeGoalsScoredHalf = teamHomeGoalsScoredHalf;
    }

    public Integer getTeamAwayGoalsScoredHalf() {
        return teamAwayGoalsScoredHalf;
    }

    public void setTeamAwayGoalsScoredHalf(Integer teamAwayGoalsScoredHalf) {
        this.teamAwayGoalsScoredHalf = teamAwayGoalsScoredHalf;
    }

    public List<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(List<PlayerResultDto> playerResults) {
        this.playerResults = playerResults;
    }
    
}
