/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Min;
import soccer.records.enums.PlayerPost;

/**
 *
 * @author 
 */
public class PlayerCreateDto {
    
    private String name;

    private String surname;

    @Min(0)
    private int age;

    private PlayerPost post;

    private boolean captian;

    private String country;
    private String city;

    private TeamDto team;

    private Set<PlayerResultDto> playerResults = new HashSet<PlayerResultDto>();

    public PlayerCreateDto() {
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
    
    public void setIsCaptian(boolean captian) {
        this.captian = captian;
    }

    public boolean isCaptian() {
        return captian;
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
    
    public Set<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(Set<PlayerResultDto> playerResults) {
        this.playerResults = playerResults;
    }
    
    public void addPlayerResult(PlayerResultDto r)
    {
        playerResults.add(r);
    }
    public void removePlayerResult(PlayerResultDto r)
    {
        playerResults.remove(r);
    }
    
    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public TeamDto getTeam() {
        return team;
    }    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + age;
        result = prime * result + ((post == null) ? 0 : post.hashCode());
        result = prime * result + (captian ? 1 : 0);        
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
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
        final PlayerCreateDto other = (PlayerCreateDto) obj;
        if (this.age != other.age) {
            return false;
        }
        if (this.captian != other.captian) {
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
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        return true;
    }
    
}
