/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import soccer.records.enums.PlayerPost;

/**
 *
 * @author 
 */
public class PlayerDto {
    
    private Long id;

    private String name;

    private String surname;

    private int age;

    private PlayerPost post;

    private boolean captian;

    private String country;
    private String city;

    private TeamDto team;

    private Set<PlayerResultDto> playerResults = new HashSet<PlayerResultDto>();

    public PlayerDto(Long playerId) {
        this.id = playerId;
    }

    public PlayerDto() {
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


    public Long getTeamId() {
        return team.getId();
    }

    public void setTeamId(Long teamId) {
        this.team.setId(teamId);
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public TeamDto team() {
        return this.team;
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
 
    /*@Override
    public boolean equals(Object obj) {
        
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        PlayerDto other = (PlayerDto) obj;
        
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
        
        if (captian != other.isCaptian()){
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
        
        if (!playerResults.equals(other.getPlayerResults())){
            return false;
        }

        return true;
    }*/

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
        final PlayerDto other = (PlayerDto) obj;
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
