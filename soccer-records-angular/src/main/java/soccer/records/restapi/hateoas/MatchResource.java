/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.restapi.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 *
 * @author Michaela Bocanova
 */
@Relation(value = "match", collectionRelation = "matches")
@JsonPropertyOrder({"id"})
public class MatchResource extends ResourceSupport {
    
    @JsonProperty("id") 
    private long dtoId;
    
    
}
