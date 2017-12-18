/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michaela Bocanova
 */
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class MatchDto extends AuditableDto<String> {
    
    private Long id;
    private Date dateAndTime;
    private LocationDto location;
    //@Min(0)
    private int teamHomeGoalsScored;
    //@Min(0)
    private int teamAwayGoalsScored;
    @Min(0)
    private int teamHomeGoalsScoredHalf;
    @Min(0)
    private int teamAwayGoalsScoredHalf;
    
    @NotNull
    //@JsonIgnore
    private TeamDto teamHome;
    
    @NotNull
    //@JsonIgnore
    private TeamDto teamAway;

    //@JsonIgnore
    //private List<PlayerResultDto> playerResults = new ArrayList<>();
        
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
    /*public List<PlayerResultDto> getPlayerResults() {
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
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getTeamHomeGoalsScored(boolean halftime) {
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

    public int getTeamAwayGoalsScored(boolean halftime) {
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
        
    public int getTeamHomeGoalsReceived(boolean halftime) {
        if (halftime)
            return teamAwayGoalsScoredHalf;
        return teamAwayGoalsScored;
    }
    
    public int getTeamAwayGoalsReceived(boolean halftime) {
        if (halftime)
            return teamHomeGoalsScoredHalf;
        return teamHomeGoalsScored;
    }
    
    /**
     * Constructor assigns a specific id
     * @param id 
     */
    public MatchDto(Long id) {
         this.id = id;
    }

    /**
     * Constructor sets both notNull properties
     * @param teamHome
     * @param teamAway 
     */
    public MatchDto(TeamDto teamHome, TeamDto teamAway) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
    } 
    
    public MatchDto() {
    }       
      
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if(id != null)
            return prime * result + id.hashCode();
        
        result = prime * result + ((dateAndTime == null) ? 0 : dateAndTime.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((teamHome == null) ? 0 : teamHome.hashCode());
        result = prime * result + ((teamAway == null) ? 0 : teamAway.hashCode());
        
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
        
        MatchDto other = (MatchDto) obj;        
        
        if (id == null) {
            if (other.getId()!= null) {
                return false;
            }
        } else return id.equals(other.getId());
        
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
        
        /*if (teamHomeGoalsScored == null) {
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
        }*/
                
        return true;
    }
    
    @Override
    public String toString() {
	return "MatchDto{" +
		"id=" + id + '\'' +
		"dateAndTime=" + dateAndTime + '\'' +
                "location=" + location + '\'' +
                "teamHomeScoredHalf=" + teamHomeGoalsScoredHalf + '\'' +
                "teamAwayScoredHalf=" + teamAwayGoalsScoredHalf + '\'' +
                "teamHomeScoredTotal=" + teamHomeGoalsScored + '\'' +
                "teamAwayScoredTotal=" + teamAwayGoalsScored + '\'' +
		"}";
    }
}
