/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.validation.constraints.Min;

/**
 *
 * @author Radim VIdlák
 */
public class PlayerResultEditDto {
   
    private Long id;    
    
    @Min(0)
    private int goalsScored;

    private Long playerId;
    private Long matchId;

    public PlayerResultEditDto() {
    }

    public PlayerResultEditDto(Long p, Long m) {
        this.playerId = p;
        this.matchId = m;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Long getPlayer() {
        return playerId;
    }

    public void setPlayer(Long playerId) {
        this.playerId = playerId;
    }

    public Long getMatch() {
        return matchId;
    }

    public void setMatch(Long matchId) {
        this.matchId = matchId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + goalsScored;
        result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
        result = prime * result + ((matchId == null) ? 0 : matchId.hashCode());
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
        PlayerResultEditDto other = (PlayerResultEditDto) obj;
        
        if (playerId == null) {
            if (other.getPlayer() != null)
                return false;
        } else if (!playerId.equals(other.getPlayer())){
            return false;
        }

        if (matchId == null) {
            if (other.getMatch()!= null)
                return false;
        } else if (!matchId.equals(other.getMatch())){
            return false;
        }
        
        if(goalsScored != other.getGoalsScored()){
            return false;
        }

        return true;
    }
    
}
