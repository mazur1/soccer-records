package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import soccer.records.dto.TeamDto;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.MatchDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.TeamResultDto;

/**
 * Category rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Radim Vidlák
 */

@Relation(value = "team", collectionRelation = "teams")
@JsonPropertyOrder({"id", "name"})
public class TeamResource extends AuditableResource<String> {

    @JsonProperty("id") //ResourceSupport alrerady has getId() method
    private long dtoId;
    private String name;
    private List<PlayerDto> players;
    private List<MatchDto> matchesHome;
    private List<MatchDto> matchesAway;
    private TeamResultDto teamResult;
    private int wins;
    private int looses;
    private int ties;

    public TeamResource(TeamDto dto) {
        super(dto);
        this.dtoId = dto.getId();
        this.name = dto.getName();
        this.players = dto.getPlayers();
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLooses() {
        return looses;
    }

    public void setLooses(int looses) {
        this.looses = looses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<PlayerDto> getPlayers() {
        return players;
    }

    public List<MatchDto> getMatchesHome() {
        return matchesHome;
    }

    public List<MatchDto> getMatchesAway() {
        return matchesAway;
    }

    public void setMatchesHome(List<MatchDto> matchesHome) {
        this.matchesHome = matchesHome;
    }

    public void setMatchesAway(List<MatchDto> matchesAway) {
        this.matchesAway = matchesAway;
    }

    public TeamResultDto getTeamResult() {
        return teamResult;
    }

    public void setTeamResult(TeamResultDto teamResult) {
        this.teamResult = teamResult;
    }
    
    
}
