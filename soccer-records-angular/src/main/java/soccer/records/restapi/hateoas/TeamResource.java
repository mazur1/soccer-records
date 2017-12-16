package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import soccer.records.dto.TeamDto;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.PlayerDto;

/**
 * Category rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Radim Vidlák
 */

@Relation(value = "team", collectionRelation = "teams")
@JsonPropertyOrder({"id", "name"})
public class TeamResource extends ResourceSupport {

    @JsonProperty("id") //ResourceSupport alrerady has getId() method
    private long dtoId;
    private String name;
    private List<PlayerDto> players;

    public TeamResource(TeamDto dto) {
        this.dtoId = dto.getId();
        this.name = dto.getName();
        this.players = dto.getPlayers();
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
}
