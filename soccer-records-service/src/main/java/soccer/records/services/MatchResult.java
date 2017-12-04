/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import soccer.records.entity.Match;
import soccer.records.entity.Team;

/**
 * Helper class for match results
 * 
 * @author Michaela Bocanova
 */
public class MatchResult {
    private Match match;
    private Team winner;
    private Team looser;
    private boolean tie=false;

    public MatchResult() {
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Team getLooser() {
        return looser;
    }

    public void setLooser(Team looser) {
        this.looser = looser;
    }

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((match == null) ? 0 :match.hashCode());
        
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
        
        MatchResult other = (MatchResult) obj;        
       
        if (winner == null) {
            if (other.getWinner()!= null) {
                return false;
            }
        } else if (!winner.equals(other.getWinner())) {
            return false;
        }
        
        if (looser == null) {
            if (other.getLooser()!= null) {
                return false;
            }
        } else if (!looser.equals(other.getLooser())) {
            return false;
        }
        
        if (tie != other.isTie()) {
                return false;
        }
                        
        return true;
    }
}
