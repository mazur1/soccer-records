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
    
    public List<Player> teamPlayers() {
        return this.players;
    }
    
    public List<Match> matchesHome() {
        return this.matchesHome;
    }
    
    public List<Match> matchesAway() {
        return this.matchesAway;
    }

    
}
