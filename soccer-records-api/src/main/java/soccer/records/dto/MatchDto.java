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
/*@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")*/
public class MatchDto extends AuditableDto<String> {
    
    private boolean isActive=true;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    private Long id;
    private Date dateAndTime;
    private LocationDto location;
    @Min(0)
    private int teamHomeGoalsScored;
    @Min(0)
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

    //@JsonIgnore
    //private List<PlayerResultDto> playerResults = new ArrayList<>();*/
    
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

    public int getTeamHomeGoalsScored() {
        return teamHomeGoalsScored;
    }

    public void setTeamHomeGoalsScored(int teamHomeGoalsScored) {
        this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public int getTeamAwayGoalsScored() {
        return teamAwayGoalsScored;
    }

    public void setTeamAwayGoalsScored(int teamAwayGoalsScored) {
        this.teamAwayGoalsScored = teamAwayGoalsScored;
    }

    public int getTeamHomeGoalsScoredHalf() {
        return teamHomeGoalsScoredHalf;
    }

    public void setTeamHomeGoalsScoredHalf(int teamHomeGoalsScoredHalf) {
        this.teamHomeGoalsScoredHalf = teamHomeGoalsScoredHalf;
    }

    public int getTeamAwayGoalsScoredHalf() {
        return teamAwayGoalsScoredHalf;
    }

    public void setTeamAwayGoalsScoredHalf(int teamAwayGoalsScoredHalf) {
        this.teamAwayGoalsScoredHalf = teamAwayGoalsScoredHalf;
    }
    
    
    /**
     * Constructor assigns a specific id
     * @param id 
     */
    public MatchDto(Long id) {
         this.id = id;
    }
    
    public MatchDto() {
    }       

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.teamHome);
        hash = 19 * hash + Objects.hashCode(this.teamAway);
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
        final MatchDto other = (MatchDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateAndTime, other.dateAndTime)) {
            return false;
        }
        if (!Objects.equals(this.teamHome, other.teamHome)) {
            return false;
        }
        if (!Objects.equals(this.teamAway, other.teamAway)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatchDto{" + "id=" + id + ", dateAndTime=" + dateAndTime + ", location=" + location + ", teamHomeGoalsScored=" + teamHomeGoalsScored + ", teamAwayGoalsScored=" + teamAwayGoalsScored + ", teamHomeGoalsScoredHalf=" + teamHomeGoalsScoredHalf + ", teamAwayGoalsScoredHalf=" + teamAwayGoalsScoredHalf + ", teamHome=" + teamHome + ", teamAway=" + teamAway + '}';
    }

}
