/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerResultDto;

/**
 *
 * @author Radim Vidlák
 */

@Relation(value = "playerResult", collectionRelation = "playerResults")
@JsonPropertyOrder({"id", "player", "match", "goalsScored"})
public class PlayerResultResource extends AuditableResource<String>{

    @JsonProperty("id") 
    private long dtoId;
    
    private long playerId;
    private long matchId;
    private PlayerDto player;
    private MatchDto match;
    
    private int goalsScored;
    public PlayerResultResource(PlayerResultDto dto) {
        super(dto);
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

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
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
