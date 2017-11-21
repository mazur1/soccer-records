/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.List;
import soccer.records.entity.Team;

/**
 *
 * @author 
 */
public interface TeamService {

    void create(Team t);

    List<Team> findAll();

    Team findById(Long id);

    Team findByName(String name);

    void remove(Team t) throws IllegalArgumentException;

    void update(Team t);
    
}
