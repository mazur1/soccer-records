package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import soccer.records.dto.PlayerDto;
import org.springframework.hateoas.core.Relation;
import soccer.records.dto.PlayerResultDto;
import soccer.records.dto.TeamDto;
import soccer.records.enums.PlayerPost;

/**
 * Category rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Tomas Mazurek
 */

@Relation(value = "player", collectionRelation = "players")
@JsonPropertyOrder({"id", "name"})
public class PlayerResource extends AuditableResource<String> {

    @JsonProperty("id") //ResourceSupport alrerady has getId() method
    private long dtoId;
    private String name;
    private long teamId;
    private TeamDto team;
    private String surname;
    private int age;
    private PlayerPost post;
    private boolean captain;
    private String country;
    private String city;

    private List<PlayerResultDto> playerResults;

    public PlayerResource(PlayerDto dto) {
        super(dto);
        this.dtoId = dto.getId();
        this.name = dto.getName();
        this.team = dto.getTeam();
        this.surname = dto.getSurname();
        this.age = dto.getAge();
        this.post = dto.getPost();
        this.captain = dto.isCaptain();
        this.country = dto.getCountry();
        this.city = dto.getCity();
        //this.playerResults = dto.getPlayerResults();
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public TeamDto getTeam(){
        return team;
    }
    
        public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PlayerPost getPost() {
        return post;
    }

    public void setPost(PlayerPost post) {
        this.post = post;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(List<PlayerResultDto> playerResults) {
        this.playerResults = playerResults;
    }
}
