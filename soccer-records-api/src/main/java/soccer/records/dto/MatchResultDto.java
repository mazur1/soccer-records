/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dto;

/**
 *
 * @author Michaela Bocanova
 */
public class MatchResultDto {
    private MatchDto match;
    private TeamDto winner;
    private TeamDto looser;
    private boolean tie=false;

    public MatchResultDto() {
    }

    public MatchDto getMatch() {
        return match;
    }

    public void setMatch(MatchDto match) {
        this.match = match;
    }

    public TeamDto getWinner() {
        return winner;
    }

    public void setWinner(TeamDto winner) {
        this.winner = winner;
    }

    public TeamDto getLooser() {
        return looser;
    }

    public void setLooser(TeamDto looser) {
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
        
        MatchResultDto other = (MatchResultDto) obj;        
       
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

