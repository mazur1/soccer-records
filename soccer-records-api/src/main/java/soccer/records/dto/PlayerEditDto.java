/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Min;
import soccer.records.enums.PlayerPost;


public class PlayerEditDto {
    
    private Long id;
    
    private String name;

    private String surname;

    @Min(0)
    private int age;

    private PlayerPost post;

    private boolean captain;

    private String country;
    private String city;

    private Long teamId; 

    @JsonIgnore
    private List<Long> playerResults = new ArrayList<Long>();

    public PlayerEditDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }    
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }    
    
    public void setAge(int age) {
        this.age = age;
    }

   public int getAge() {
        return age;
    }    
    
    public void setPost(PlayerPost post) {
        this.post = post;
    }

    public PlayerPost getPost() {
        return post;
    }    

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }    
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }    
    
    public List<Long> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(ArrayList<Long> playerResults) {
        this.playerResults = playerResults;
    }
    
    public void addPlayerResult(Long id)
    {
        playerResults.add(id);
    }
    public void removePlayerResult(Long id)
    {
        playerResults.remove(id);
    }
    
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
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
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        //result = prime * result + ((playerResults == null) ? 0 : playerResults.hashCode());
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
        final PlayerEditDto other = (PlayerEditDto) obj;
        if (this.age != other.age) {
            return false;
        }
        if (this.captain != other.captain) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (this.post != other.post) {
            return false;
        }
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
        return true;
    }
    
}
