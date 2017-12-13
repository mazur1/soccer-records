/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;
import soccer.records.entity.AppUser;
import soccer.records.enums.AppRole;
import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 *
 * @author Michaela Bocanova
 */
public interface AppUserDao extends DefaultCrudDao<AppUser,Long> {

    /**
     * finds user by its username
     * @param name
     * @return
     * @throws DataAccessExceptions 
     */
    AppUser findByUsername(String name) throws DataAccessExceptions;

    /**
     * finds users with given role
     * @param role
     * @return
     * @throws DataAccessExceptions 
     */
    List<AppUser> findByRole(AppRole role) throws DataAccessExceptions;
    
}
