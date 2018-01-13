package soccer.records.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity representing a match between two soccer teams 
 * containing info about location, date and time, score
 * 
 * @author Michaela Bocanova
 */
@Entity
@Table(name="SoccerMatch")
public class Match extends Auditable<String,Long> {

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateAndTime;
    @Embedded 
    private Location location;
    @Min(0)
    private int teamHomeGoalsScored=0;
    @Min(0)
    private int teamAwayGoalsScored=0;
    @Min(0)
    private int teamHomeGoalsScoredHalf=0;
    @Min(0)
    private int teamAwayGoalsScoredHalf=0;
    
    @NotNull
    @ManyToOne
    //@JoinColumn(name="TeamHome_FK")
    private Team teamHome;
    @NotNull
    @ManyToOne
    //@JoinColumn(name="TeamAway_FK")
    private Team teamAway;
    @OneToMany()//(mappedBy = "match")
    @JoinColumn(name="Match_FK")
    private List<PlayerResult> playerResults = new ArrayList<>();
    
    
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
    
    public void setPlayerResults(List<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }
        
    public void addPlayerResult(PlayerResult r) {
        playerResults.add(r);
    }
    
    public void removePlayerResult(PlayerResult r) {
        playerResults.remove(r);
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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
    

    /*public int getTeamHomeGoalsScored(boolean halftime) {
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
    }*/
    
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
        
        Match other = (Match) obj;        
        
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
	return "Match{" +
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

