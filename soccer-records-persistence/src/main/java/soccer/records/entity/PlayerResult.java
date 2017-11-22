/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import soccer.records.entity.Player;
import soccer.records.entity.Match;

/**
 *
 * @author Radim Vidlák
 */
@Entity
public class PlayerResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private int goalsScored;

    @NotNull
    @ManyToOne
    private Player player;

    @NotNull
    @ManyToOne
    private Match match;

    public PlayerResult() {
    }

    public PlayerResult(Player p, Match m) {
        this.setPlayer(p);
        this.setMatch(m);
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((match == null) ? 0 : match.hashCode());
        result = prime * result + goalsScored;
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
        
        PlayerResult other = (PlayerResult) obj;      

        if (player == null) {
            if (other.getPlayer()!= null)
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

        return true;        

    }

}
