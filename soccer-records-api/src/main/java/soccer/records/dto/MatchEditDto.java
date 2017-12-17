/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michaela Bocanova
 */
public class MatchEditDto {
    
    private Long id;
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
    @JsonIgnore
    private TeamDto teamHome;
    
    @NotNull
    @JsonIgnore
    private TeamDto teamAway;

    @JsonIgnore
    private List<PlayerResultDto> playerResults = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public List<PlayerResultDto> getPlayerResults() {
        return Collections.unmodifiableList(playerResults);
    }

    public void setPlayerResults(List<PlayerResultDto> playerResults) {
        this.playerResults = playerResults;
    }
    
    public void addPlayerResult(PlayerResultDto r) {
        playerResults.add(r);
    }
    
    public void removePlayerResult(PlayerResultDto r) {
        playerResults.remove(r);
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

    public Integer getTeamHomeGoalsScored(boolean halftime) {
        if (halftime)
            return teamHomeGoalsScoredHalf;
        return teamHomeGoalsScored;
    }

    public void setTeamHomeGoalsScored(int teamHomeGoalsScored, boolean halftime) {
        if (halftime)
            this.teamHomeGoalsScoredHalf = teamHomeGoalsScored;
        else
            this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public Integer getTeamAwayGoalsScored(boolean halftime) {
        if (halftime)
            return teamAwayGoalsScoredHalf;
        return teamAwayGoalsScored;
    }

    public void setTeamAwayGoalsScored(int teamAwayGoalsScored, boolean halftime) {
        if (halftime)
            this.teamAwayGoalsScoredHalf = teamAwayGoalsScored;
        else
            this.teamAwayGoalsScored = teamAwayGoalsScored;
    }
    
    /**
     * Constructor sets both notNull properties
     * @param teamHome
     * @param teamAway 
     */
    public MatchEditDto(TeamDto teamHome, TeamDto teamAway) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
    } 
    
    public MatchEditDto() {
    }       
      
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((dateAndTime == null) ? 0 : dateAndTime.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((teamHome == null) ? 0 : teamHome.hashCode());
        result = prime * result + ((teamAway == null) ? 0 : teamAway.hashCode());
        result = prime * result + ((teamHomeGoalsScored == null) ? 0 : teamHomeGoalsScored.hashCode());
        result = prime * result + ((teamHomeGoalsScoredHalf == null) ? 0 : teamHomeGoalsScoredHalf.hashCode());
        result = prime * result + ((teamAwayGoalsScored == null) ? 0 : teamAwayGoalsScored.hashCode());
        result = prime * result + ((teamAwayGoalsScoredHalf == null) ? 0 : teamAwayGoalsScoredHalf.hashCode());
        
        return result;
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
        
        MatchEditDto other = (MatchEditDto) obj;        
                
        if (teamHome == null) {
            if (other.getTeamHome() != null) { 
                return false;
            }
        } else if (!teamHome.equals(other.getTeamHome())) {
            return false;
        }
        
        if (teamAway == null) {
            if (other.getTeamAway() != null) {
                return false;
            }
        } else if (!teamAway.equals(other.getTeamAway())) {
            return false;
        }
        
        if (dateAndTime == null) {
            if (other.getDateAndTime() != null) {
                return false;
            }
        } else if (!dateAndTime.equals(other.getDateAndTime())) {
            return false;
        }
        
        if (location == null) {
            if (other.getLocation() != null) {
                return false;
            }
        } else if (!location.equals(other.getLocation())) {
            return false;
        }
        
        if (teamHomeGoalsScored == null) {
            if (other.getTeamHomeGoalsScored(false) != null) {
                return false;
            }
        } else if (!teamHomeGoalsScored.equals(other.getTeamHomeGoalsScored(false))) {
            return false;
        }
        
        if (teamHomeGoalsScoredHalf == null) {
            if (other.getTeamHomeGoalsScored(true) != null) {
                return false;
            }
        } else if (!teamHomeGoalsScoredHalf.equals(other.getTeamHomeGoalsScored(true))) {
            return false;
        }
        
        if (teamAwayGoalsScored == null) {
            if (other.getTeamAwayGoalsScored(false) != null) {
                return false;
            }
        } else if (!teamAwayGoalsScored.equals(other.getTeamAwayGoalsScored(false))) {
            return false;
        }
        
        if (teamAwayGoalsScoredHalf == null) {
            if (other.getTeamAwayGoalsScored(true) != null) {
                return false;
            }
        } else if (!teamAwayGoalsScoredHalf.equals(other.getTeamAwayGoalsScored(true))) {
            return false;
        }
                
        return true;
    }
    
    @Override
    public String toString() {
	return "MatchDto{" +
		"dateAndTime=" + dateAndTime + '\'' +
                "location=" + location + '\'' +
                "teamHomeScoredHalf=" + teamHomeGoalsScoredHalf + '\'' +
                "teamAwayScoredHalf=" + teamAwayGoalsScoredHalf + '\'' +
                "teamHomeScoredTotal=" + teamHomeGoalsScored + '\'' +
                "teamAwayScoredTotal=" + teamAwayGoalsScored + '\'' +
		"}";
    }
}
