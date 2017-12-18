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
    //@JsonIgnore
    private Long teamHomeId;
    
    @NotNull
    //@JsonIgnore
    private Long teamAwayId;

    /*@JsonIgnore
    private List<Long> playerResults = new ArrayList<>();*/

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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    /*public List<Long> getPlayerResults() {
        return Collections.unmodifiableList(playerResults);
    }

    public void setPlayerResults(List<Long> playerResults) {
        this.playerResults = playerResults;
    }
    
    public void addPlayerResult(Long r) {
        playerResults.add(r);
    }
    
    public void removePlayerResult(Long r) {
        playerResults.remove(r);
    }*/

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
    
    public MatchEditDto() {
    }       

    @Override
    public int hashCode() {
        int hash = 7;
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
        final MatchEditDto other = (MatchEditDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatchEditDto{" + "id=" + id + ", dateAndTime=" + dateAndTime + ", location=" + location + ", teamHomeGoalsScored=" + teamHomeGoalsScored + ", teamAwayGoalsScored=" + teamAwayGoalsScored + ", teamHomeGoalsScoredHalf=" + teamHomeGoalsScoredHalf + ", teamAwayGoalsScoredHalf=" + teamAwayGoalsScoredHalf + '}';
    }
      
    
}
