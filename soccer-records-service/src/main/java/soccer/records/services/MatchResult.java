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
}
