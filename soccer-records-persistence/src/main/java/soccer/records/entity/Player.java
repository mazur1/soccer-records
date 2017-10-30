package soccer.records.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import soccer.records.enums.PlayerPost;

/**
 *
 * @author Tomas Mazurek
 */
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String surname;

    @NotNull
    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private PlayerPost post;

    @NotNull
    @Column(nullable = false)
    private boolean captian;

    private String country;
    private String city;

    @ManyToOne()
    private Team team;

    @OneToMany(mappedBy = "player")
    private Set<PlayerResult> playerResults = new HashSet<PlayerResult>();

    public Player(Long playerId) {
        this.id = playerId;
    }

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long playerId) {
        this.id = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PlayerPost getPost() {
        return post;
    }

    public void setPost(PlayerPost post) {
        this.post = post;
    }

    public boolean isCaptian() {
        return captian;
    }

    public void setCaptian(boolean captian) {
        this.captian = captian;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(Set<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }
    
    

    public Long getTeamId() {
        return team.getId();
    }

    public void setTeamId(Long teamId) {
        this.team.setId(teamId);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team team() {
        return this.team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        if (name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        return true;
    }

}
