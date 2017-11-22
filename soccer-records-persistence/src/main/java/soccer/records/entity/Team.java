package soccer.records.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Tomas Mazurek
 */

@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false,unique=true)
    private String name;
    
    @OneToMany(mappedBy="team")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<Player> players = new ArrayList<Player>();
    
    @OneToMany(mappedBy="teamHome")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<Match> matchesHome = new ArrayList<Match>();
        
    @OneToMany(mappedBy="teamAway")
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<Match> matchesAway = new ArrayList<Match>();
        
    public Team(Long teamId) {
        this.id = teamId; 
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }    
    
    public void setId(Long teamId) {
	this.id = teamId; 
    }
    
    public List<Player> getTeamPlayers() {
        return this.players;
    }
    
    public List<Match> getMatchesHome() {
        return this.matchesHome;
    }
    
    public List<Match> getMatchesAway() {
        return this.matchesAway;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
             
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((players == null) ? 0 : players.hashCode());
        result = prime * result + ((matchesHome == null) ? 0 : matchesHome.hashCode());
        result = prime * result + ((matchesAway == null) ? 0 : matchesAway.hashCode());
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
        
        Team other = (Team) obj;        
        
        if (name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        
        if (!matchesHome.equals(other.getMatchesHome())) {
            return false;
        }
        
        if (!matchesHome.equals(other.getMatchesAway())) {
            return false;
        }

        if (!matchesHome.equals(other.getTeamPlayers())) {
            return false;
        }                       
        
        return true;
    }
}
