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
    private Set<Player> players = new HashSet<Player>();

    @NotNull
    @ManyToOne
    private Set<Match> matches = new HashSet<Match>();

    public PlayerResult() {
    }

    public PlayerResult(Player p, Match m) {
        this.addMatch(m);
        this.addPlayer(p);
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public void addMatch(Match m) {
        this.matches.add(m);
    }

    public Set<Match> getMatches() {
        return Collections.unmodifiableSet(matches);
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (!(obj instanceof PlayerResult)) {
            return false;
        }
        PlayerResult other = (PlayerResult) obj;
        
        if (id == null) {
            if (other.getId()!= null) {
                return false;
            }
        } else if (!id.equals(other.getGoalsScored())) {
            return false;
        }
        
        return true;
    }

}
