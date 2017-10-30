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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity representing a match between two soccer teams 
 * containing info about location, date and time, score
 * 
 * @author Michaela Bocanova
 */
@Entity
@Table(name="SoccerMatch")
public class Match {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;
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
    @OneToMany(mappedBy = "match")
    private List<PlayerResult> playerResults = new ArrayList<PlayerResult>();
    
    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
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

    public Integer getTeamHomeGoalsScored() {
        return teamHomeGoalsScored;
    }

    public void setTeamHomeGoalsScored(int teamHomeGoalsScored) {
        this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public Integer getTeamAwayGoalsScored() {
        return teamAwayGoalsScored;
    }

    public void setTeamAwayGoalsScored(int teamAwayGoalsScored) {
        this.teamAwayGoalsScored = teamAwayGoalsScored;
    }

    public Integer getTeamHomeGoalsScoredHalf() {
        return teamHomeGoalsScoredHalf;
    }

    public void setTeamHomeGoalsScoredHalf(int teamHomeGoalsScoredHalf) {
        this.teamHomeGoalsScoredHalf = teamHomeGoalsScoredHalf;
    }

    public Integer getTeamAwayGoalsScoredHalf() {
        return teamAwayGoalsScoredHalf;
    }

    public void setTeamAwayGoalsScoredHalf(int teamAwayGoalsScoredHalf) {
        this.teamAwayGoalsScoredHalf = teamAwayGoalsScoredHalf;
    }
        
    public Integer getTeamHomeGoalsReceived() {
        return teamAwayGoalsScored;
    }
    
    public Integer getTeamAwayGoalsReceived() {
        return teamHomeGoalsScored;
    }
    
    /**
     * Constructor assigns a specific id
     * @param id 
     */
    public Match(Long id) {
         this.id = id;
    }

    /**
     * Constructor sets both notNull properties
     * @param teamHome
     * @param teamAway 
     */
    public Match(Team teamHome, Team teamAway) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
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
        
        Match other = (Match) obj;        
        
        if (id == null) {
            if (other.getId()!= null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        
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
            if (other.getTeamHomeGoalsScored() != null) {
                return false;
            }
        } else if (!teamHomeGoalsScored.equals(other.getTeamHomeGoalsScored())) {
            return false;
        }
        
        if (teamHomeGoalsScoredHalf == null) {
            if (other.getTeamHomeGoalsScoredHalf() != null) {
                return false;
            }
        } else if (!teamHomeGoalsScoredHalf.equals(other.getTeamHomeGoalsScoredHalf())) {
            return false;
        }
        
        if (teamAwayGoalsScored == null) {
            if (other.getTeamAwayGoalsScored() != null) {
                return false;
            }
        } else if (!teamAwayGoalsScored.equals(other.getTeamAwayGoalsScored())) {
            return false;
        }
        
        if (teamAwayGoalsScoredHalf == null) {
            if (other.getTeamAwayGoalsScoredHalf() != null) {
                return false;
            }
        } else if (!teamAwayGoalsScoredHalf.equals(other.getTeamAwayGoalsScoredHalf())) {
            return false;
        }
                
        return true;
    }
    
    @Override
    public String toString() {
	return "Match{" +
		"id=" + id +
		"dateAndTime=" + dateAndTime +
                "location=" + location +
                "teamHomeScoredHalf=" + teamHomeGoalsScoredHalf +
                "teamAwayScoredHalf=" + teamAwayGoalsScoredHalf +
                "teamHomeScoredTotal=" + teamHomeGoalsScored +
                "teamAwayScoredTotal=" + teamAwayGoalsScored +
		"}";
    }
}

