package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.List;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.LocationDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;

/**
 *
 * @author Michaela Bocanova
 */
@Relation(value = "match", collectionRelation = "matches")
@JsonPropertyOrder({"id", "teamHome", "teamAway", "dateAndTime", "location", 
    "teamHomeGoalsScored", "teamAwayGoalsScored", "teamHomeGoalsScoredHalf", "teamAwayGoalsScoredHalf"})
public class MatchResource extends AuditableResource<String> {
    
    @JsonProperty("id") 
    private long dtoId;
    private long teamHomeId;
    private long teamAwayId;
    private TeamDto teamHome;
    private TeamDto teamAway;
    private Date dateAndTime;
    private LocationDto location;
    private int teamHomeGoalsScored;
    private int teamAwayGoalsScored;
    private int teamHomeGoalsScoredHalf;
    private int teamAwayGoalsScoredHalf;

    private List<PlayerResultDto> playerResults;
    
    public MatchResource(MatchDto dto) {
        super(dto);
        this.dtoId = dto.getId();
        teamHome = dto.getTeamHome();
        teamAway = dto.getTeamAway();
        dateAndTime = dto.getDateAndTime();
        location = dto.getLocation();
        teamHomeGoalsScored = dto.getTeamHomeGoalsScored();
        teamAwayGoalsScored = dto.getTeamAwayGoalsScored();
        teamHomeGoalsScoredHalf = dto.getTeamHomeGoalsScoredHalf();
        teamAwayGoalsScoredHalf = dto.getTeamAwayGoalsScoredHalf();
        //playerResults;
    }

    public Long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public long getTeamHomeId() {
        return teamHomeId;
    }

    public void setTeamHomeId(long teamHomeId) {
        this.teamHomeId = teamHomeId;
    }

    public long getTeamAwayId() {
        return teamAwayId;
    }

    public void setTeamAwayId(long teamAwayId) {
        this.teamAwayId = teamAwayId;
    }
    
    public TeamDto getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(TeamDto teamHome) {
        this.teamHome = teamHome;
    }

    public TeamDto getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(TeamDto teamAway) {
        this.teamAway = teamAway;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        /*Date d;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            d = sdf.parse(date);
            m.setDateAndTime(d);
        } catch(ParseException e) {}*/
        this.dateAndTime = dateAndTime;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public int getTeamHomeGoalsScored() {
        return teamHomeGoalsScored;
    }

    public void setTeamHomeGoalsScored(int teamHomeGoalsScored) {
        this.teamHomeGoalsScored = teamHomeGoalsScored;
    }

    public int getTeamAwayGoalsScored() {
        return teamAwayGoalsScored;
    }

    public void setTeamAwayGoalsScored(int teamAwayGoalsScored) {
        this.teamAwayGoalsScored = teamAwayGoalsScored;
    }

    public int getTeamHomeGoalsScoredHalf() {
        return teamHomeGoalsScoredHalf;
    }

    public void setTeamHomeGoalsScoredHalf(int teamHomeGoalsScoredHalf) {
        this.teamHomeGoalsScoredHalf = teamHomeGoalsScoredHalf;
    }

    public int getTeamAwayGoalsScoredHalf() {
        return teamAwayGoalsScoredHalf;
    }

    public void setTeamAwayGoalsScoredHalf(int teamAwayGoalsScoredHalf) {
        this.teamAwayGoalsScoredHalf = teamAwayGoalsScoredHalf;
    }

    

    public List<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(List<PlayerResultDto> playerResults) {
        this.playerResults = playerResults;
    }
    
}
