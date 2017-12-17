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
    @JsonIgnore
    private PlayerDto player;
    @JsonIgnore
    private MatchDto match;

    public PlayerResultEditDto() {
    }

    public PlayerResultEditDto(PlayerDto p, MatchDto m) {
        this.player = p;
        this.match = m;
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

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    public MatchDto getMatch() {
        return match;
    }

    public void setMatch(MatchDto match) {
        this.match = match;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + goalsScored;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((match == null) ? 0 : match.hashCode());
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
        
        if (player == null) {
            if (other.getPlayer() != null)
                return false;
        } else if (!player.equals(other.getPlayer())){
            return false;
        }

        if (match == null) {
            if (other.getMatch()!= null)
                return false;
        } else if (!match.equals(other.getMatch())){
            return false;
        }
        
        if(goalsScored != other.getGoalsScored()){
            return false;
        }

        return true;
    }
    
}
