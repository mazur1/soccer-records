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
public class TeamResultDto {
    //private Team team;
    private int wins=0;
    private int losses=0;
    private int ties=0;

    public TeamResultDto() {
    }

//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        //result = prime * result + ((team == null) ? 0 :team.hashCode());
        
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
        
        TeamResultDto other = (TeamResultDto) obj;        
       
//        if (team == null) {
//            if (other.getTeam()!= null) {
//                return false;
//            }
//        } else if (!team.equals(other.getTeam())) {
//            return false;
//        }
        
        if (wins != other.getWins()) {
                return false;
        }
        
        if (losses != other.getLosses()) {
                return false;
        }
        
        if (ties != other.getTies()) {
                return false;
        }
                        
        return true;
    }
    
}
