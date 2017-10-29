package soccer.records.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
    private Set<Player> players = new HashSet<Player>();
    
    @OneToMany(mappedBy="teamHome")
    private Set<Match> matchesHome = new HashSet<Match>();
        
    @OneToMany(mappedBy="teamAway")
    private Set<Match> matchesAway = new HashSet<Match>();
        
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
    
    public Set<Player> teamPlayers() {
        return this.players;
    }
    
    public Set<Match> matchesHome() {
        return this.matchesHome;
    }
    
    public Set<Match> matchesAway() {
        return this.matchesAway;
    }

}
