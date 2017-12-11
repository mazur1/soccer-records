/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;
import soccer.records.entity.AppUser;
import soccer.records.enums.AppRole;

/**
 *
 * @author Michaela Bocanova
 */
public interface AppUserDao {

    void create(AppUser u);

    void delete(AppUser u);

    List<AppUser> findAll();

    AppUser findById(Long id);

    void update(AppUser u);

    AppUser findByUsername(String name);

    List<AppUser> findByRole(AppRole role);
    
}
