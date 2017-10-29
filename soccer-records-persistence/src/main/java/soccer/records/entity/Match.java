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
    private Integer teamHomeGoalsScored;
    private Integer teamAwayGoalsScored;
    private Integer teamHomeGoalsScoredHalf;
    private Integer teamAwayGoalsScoredHalf;
    @NotNull
    @ManyToOne
    private Team teamHome;
    @NotNull
    @ManyToOne
    private Team teamAway;
    @NotNull
    @OneToMany(mappedBy = "match") 
    @OrderBy("name")
    private List<PlayerResult> playerResults = new ArrayList<PlayerResult>();
    
    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamTwo() {
        return teamAway;
    }

    public void setTeamTwo(Team teamAway) {
        this.teamAway = teamAway;
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
        return teamHomeGoalsScored;
    }

    public void setTeamOneGoalsScored(int teamHomeGoalsScored) {
        this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public Integer getTeamTwoGoalsScored() {
        return teamAwayGoalsScored;
    }

    public void setTeamTwoGoalsScored(int teamAwayGoalsScored) {
        this.teamAwayGoalsScored = teamAwayGoalsScored;
    }

    public Integer getTeamOneGoalsScoredHalf() {
        return teamHomeGoalsScoredHalf;
    }

    public void setTeamOneGoalsScoredHalf(int teamHomeGoalsScoredHalf) {
        this.teamHomeGoalsScoredHalf = teamHomeGoalsScoredHalf;
    }

    public Integer getTeamTwoGoalsScoredHalf() {
        return teamAwayGoalsScoredHalf;
    }

    public void setTeamTwoGoalsScoredHalf(int teamAwayGoalsScoredHalf) {
        this.teamAwayGoalsScoredHalf = teamAwayGoalsScoredHalf;
    }
        
    public Integer getTeamOneGoalsReceived() {
        return teamAwayGoalsScored;
    }
    
    public Integer getTeamTwoGoalsReceived() {
        return teamHomeGoalsScored;
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
        result = prime * result + ((teamHome == null) ? 0 : teamHome.hashCode());
        result = prime * result + ((teamAway == null) ? 0 : teamAway.hashCode());
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
        
        if (teamHome == null) {
            if (other.teamHome != null) { //@notnull?
                return false;
            }
        } else if (!teamHome.equals(other.teamHome)) {
            return false;
        }
        
        if (teamAway == null) {
            if (other.teamAway != null) {
                return false;
            }
        } else if (!teamAway.equals(other.teamAway)) {
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
        
        if (teamHomeGoalsScored == null) {
            if (other.teamHomeGoalsScored != null) {
                return false;
            }
        } else if (!teamHomeGoalsScored.equals(other.teamHomeGoalsScored)) {
            return false;
        }
        
        if (teamHomeGoalsScoredHalf == null) {
            if (other.teamHomeGoalsScoredHalf != null) {
                return false;
            }
        } else if (!teamHomeGoalsScoredHalf.equals(other.teamHomeGoalsScoredHalf)) {
            return false;
        }
        
        if (teamAwayGoalsScored == null) {
            if (other.teamAwayGoalsScored != null) {
                return false;
            }
        } else if (!teamAwayGoalsScored.equals(other.teamAwayGoalsScored)) {
            return false;
        }
        
        if (teamAwayGoalsScoredHalf == null) {
            if (other.teamAwayGoalsScoredHalf != null) {
                return false;
            }
        } else if (!teamAwayGoalsScoredHalf.equals(other.teamAwayGoalsScoredHalf)) {
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
                "teamHomeScoredHalf=" + teamHomeGoalsScoredHalf +
                "teamAwayScoredHalf=" + teamAwayGoalsScoredHalf +
                "teamHomeScoredTotal=" + teamHomeGoalsScored +
                "teamAwayScoredTotal=" + teamAwayGoalsScored +
		"}";
	}
}

