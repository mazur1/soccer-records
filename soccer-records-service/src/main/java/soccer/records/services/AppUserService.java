/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.services;

import java.util.List;
import soccer.records.entity.AppUser;

/**
 *
 * @author Michaela Bocanova
 */
public interface AppUserService {

    List<AppUser> findAll();

    AppUser findById(Long id);

    boolean authenticate(AppUser u, String password);

    AppUser findByUsername(String name);

    boolean isAdmin(AppUser u);

    void registerUser(AppUser u, String unencryptedPassword);
    
}
