package soccer.records.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import soccer.records.enums.PlayerPost;

/**
 *
 * @author Tomas Mazurek
 */
@Entity
public class Player extends Auditable<String,Long> {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

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
    private boolean captain;

    private String country;
    private String city;

    @ManyToOne
    //@JoinColumn(name="Team_FK")
    private Team team;
    
    /*@NotNull
    @Column(nullable = false)
    private boolean isActive;*/

    @OneToMany()//(mappedBy = "player")
    @JoinColumn(name="Player_FK")
    private Set<PlayerResult> playerResults = new HashSet<>();

    public Player(Long playerId) {
        this.id = playerId;
    }

    public Player() {
       //isActive = true;
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long playerId) {
        this.id = playerId;
    }*/

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

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    /*public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }*/
    
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
    



    public void addPlayerResult(PlayerResult r)
    {
        playerResults.add(r);
    }
    public void removePlayerResult(PlayerResult r)
    {
        playerResults.remove(r);
    }


    /*public Long getTeamId() {
        return team.getId();
    }

    public void setTeamId(Long teamId) {
        this.team.setId(teamId);
    }*/

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + age;
        result = prime * result + ((post == null) ? 0 : post.hashCode());
        result = prime * result + (captain ? 1 : 0);        
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        //result = prime * result + ((playerResults == null) ? 0 : playerResults.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        Player other = (Player) obj;
        
        if (!name.equals(other.getName())){
            return false;
        }

        if (!surname.equals(other.getSurname())){
            return false;
        }

        if (age != other.getAge()){
            return false;
        }

        if (!post.equals(other.getPost())){
            return false;
        }
        
        if (captain != other.isCaptain()){
            return false;
        }       
  
        if (!country.equals(other.getCountry())){
            return false;
        }

        if (!city.equals(other.getCity())){
            return false;
        }

        if (team == null) {
            if (other.getTeam()!= null)
                return false;
        } else if (!team.equals(other.getTeam())){
            return false;
        }        
        
        /*if (!playerResults.equals(other.getPlayerResults())){
            return false;
        }*/

        return true;
    }
    
}
