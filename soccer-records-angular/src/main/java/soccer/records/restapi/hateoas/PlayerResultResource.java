/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.LocationDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;

/**
 *
 * @author Radim Vidlák
 */

//@Relation(value = "match", collectionRelation = "matches")
@JsonPropertyOrder({"id", "player", "match", "goalsScored"})
public class PlayerResultResource extends ResourceSupport{

    @JsonProperty("id") 
    private long dtoId;
    
    private PlayerDto player;
    private MatchDto match;
    
    private int goalsScored;
    
    public PlayerResultResource(PlayerResultDto dto) {
        this.dtoId = dto.getId();
        player = dto.getPlayer();
        match = dto.getMatch();
        goalsScored = dto.getGoalsScored();
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
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

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }
    
}
