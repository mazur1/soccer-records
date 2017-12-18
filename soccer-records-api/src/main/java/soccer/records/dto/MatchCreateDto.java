/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michaela Bocanova
 */
public class MatchCreateDto {
    
    private Date dateAndTime;
    private LocationDto location;
    @Min(0)
    private Integer teamHomeGoalsScored;
    @Min(0)
    private Integer teamAwayGoalsScored;
    @Min(0)
    private Integer teamHomeGoalsScoredHalf;
    @Min(0)
    private Integer teamAwayGoalsScoredHalf;
    @NotNull
    private Long teamHomeId;
    @NotNull
    private Long teamAwayId;
    /*@NotNull
    @JsonIgnore
    private TeamDto teamHome;
    
    @NotNull
    @JsonIgnore
    private TeamDto teamAway;

    @JsonIgnore
    private List<PlayerResultDto> playerResults = new ArrayList<>();*/
    
    public MatchCreateDto() {
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

    public Long getTeamHomeId() {
        return teamHomeId;
    }

    public void setTeamHomeId(Long teamHomeId) {
        this.teamHomeId = teamHomeId;
    }

    public Long getTeamAwayId() {
        return teamAwayId;
    }

    public void setTeamAwayId(Long teamAwayId) {
        this.teamAwayId = teamAwayId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.teamHomeId);
        hash = 29 * hash + Objects.hashCode(this.teamAwayId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MatchCreateDto other = (MatchCreateDto) obj;
        if (!Objects.equals(this.dateAndTime, other.dateAndTime)) {
            return false;
        }
        if (!Objects.equals(this.teamHomeId, other.teamHomeId)) {
            return false;
        }
        if (!Objects.equals(this.teamAwayId, other.teamAwayId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatchCreateDto{" + "dateAndTime=" + dateAndTime + ", location=" + location + ", teamHomeGoalsScored=" + teamHomeGoalsScored + ", teamAwayGoalsScored=" + teamAwayGoalsScored + ", teamHomeGoalsScoredHalf=" + teamHomeGoalsScoredHalf + ", teamAwayGoalsScoredHalf=" + teamAwayGoalsScoredHalf + ", teamHomeId=" + teamHomeId + ", teamAwayId=" + teamAwayId + '}';
    }
      
    
}
