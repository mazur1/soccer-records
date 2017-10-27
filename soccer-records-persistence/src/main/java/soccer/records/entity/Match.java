package soccer.records.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tomas
 */
@Entity
public class Match {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;
    @NotNull
    @Embedded 
    private Location location;
    private Integer teamOneGoalsScored;
    private Integer teamTwoGoalsScored;
    private Integer teamOneGoalsScoredHalf;
    private Integer teamTwoGoalsScoredHalf;
    @NotNull
    @ManyToOne
    private Team teamOne;
    @NotNull
    @ManyToOne
    private Team teamTwo;
    @NotNull
    @OneToMany(mappedBy = "match") 
    @OrderBy("name")
    private List<PlayerResult> playerResults = new ArrayList<PlayerResult>();
    
    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }
    public List<PlayerResult> getPlayerResults() {
        return Collections.unmodifiableList(playerResults);
    }
    
    public void addPlayerResult(PlayerResult r) {
        playerResults.add(r);
    }

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getTeamOneGoalsScored() {
        return teamOneGoalsScored;
    }

    public void setTeamOneGoalsScored(int teamOneGoalsScored) {
        this.teamOneGoalsScored = teamOneGoalsScored;
    }

    public Integer getTeamTwoGoalsScored() {
        return teamTwoGoalsScored;
    }

    public void setTeamTwoGoalsScored(int teamTwoGoalsScored) {
        this.teamTwoGoalsScored = teamTwoGoalsScored;
    }

    public Integer getTeamOneGoalsScoredHalf() {
        return teamOneGoalsScoredHalf;
    }

    public void setTeamOneGoalsScoredHalf(int teamOneGoalsScoredHalf) {
        this.teamOneGoalsScoredHalf = teamOneGoalsScoredHalf;
    }

    public Integer getTeamTwoGoalsScoredHalf() {
        return teamTwoGoalsScoredHalf;
    }

    public void setTeamTwoGoalsScoredHalf(int teamTwoGoalsScoredHalf) {
        this.teamTwoGoalsScoredHalf = teamTwoGoalsScoredHalf;
    }
        
    public Integer getTeamOneGoalsReceived() {
        return teamTwoGoalsScored;
    }
    
    public Integer getTeamTwoGoalsReceived() {
        return teamOneGoalsScored;
    }
    
    public Match(Long id) {
         this.id = id;
    }

    public Match() {
    }       
      
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dateAndTime == null) ? 0 : dateAndTime.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((teamOne == null) ? 0 : teamOne.hashCode());
        result = prime * result + ((teamTwo == null) ? 0 : teamTwo.hashCode());
        //+ goals
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
        
        Match other = (Match) obj;        
        
        if (id == null) {
            if (other.getId()!= null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        
        if (teamOne == null) {
            if (other.teamOne != null) { //@notnull?
                return false;
            }
        } else if (!teamOne.equals(other.teamOne)) {
            return false;
        }
        
        if (teamTwo == null) {
            if (other.teamTwo != null) {
                return false;
            }
        } else if (!teamTwo.equals(other.teamTwo)) {
            return false;
        }
        
        if (dateAndTime == null) {
            if (other.dateAndTime != null) {
                return false;
            }
        } else if (!dateAndTime.equals(other.dateAndTime)) {
            return false;
        }
        
        if (location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!location.equals(other.location)) {
            return false;
        }
        
        if (teamOneGoalsScored == null) {
            if (other.teamOneGoalsScored != null) {
                return false;
            }
        } else if (!teamOneGoalsScored.equals(other.teamOneGoalsScored)) {
            return false;
        }
        
        if (teamOneGoalsScoredHalf == null) {
            if (other.teamOneGoalsScoredHalf != null) {
                return false;
            }
        } else if (!teamOneGoalsScoredHalf.equals(other.teamOneGoalsScoredHalf)) {
            return false;
        }
        
        if (teamTwoGoalsScored == null) {
            if (other.teamTwoGoalsScored != null) {
                return false;
            }
        } else if (!teamTwoGoalsScored.equals(other.teamTwoGoalsScored)) {
            return false;
        }
        
        if (teamTwoGoalsScoredHalf == null) {
            if (other.teamTwoGoalsScoredHalf != null) {
                return false;
            }
        } else if (!teamTwoGoalsScoredHalf.equals(other.teamTwoGoalsScoredHalf)) {
            return false;
        }
                
        return true;
    }
    
    @Override
    public String toString() {
	return "Match{" +
		"id=" + id +
		"dateAndTime=" + dateAndTime +
                "address=" + location +
                "teamOneScoredHalf=" + teamOneGoalsScoredHalf +
                "teamTwoScoredHalf=" + teamTwoGoalsScoredHalf +
                "teamOneScoredTotal=" + teamOneGoalsScored +
                "teamTwoScoredTotal=" + teamTwoGoalsScored +
		"}";
	}
}

