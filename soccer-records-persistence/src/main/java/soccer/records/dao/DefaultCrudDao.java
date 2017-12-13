/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;
import soccer.records.entity.DefaultEntity;
import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 *
 * @author Michaela Bocanova
 * @param <TEntity>
 * @param <TKey>
 */
public interface DefaultCrudDao<TEntity extends DefaultEntity<TKey>, TKey> {
    
    /**
     * Method persists entity
     * @param e
     * @throws DataAccessExceptions
     */
    void create(TEntity e) throws DataAccessExceptions;

    /**
     * Method removes entity
     * @param e
     * @throws DataAccessExceptions 
     */
    void delete(TEntity e) throws DataAccessExceptions;

    /**
     * Method retrieves entity
     * @param id primary key
     * @return
     * @throws DataAccessExceptions 
     */
    TEntity findById(TKey id) throws DataAccessExceptions;

    /**
     * Method merges entity
     * @param e
     * @throws DataAccessExceptions 
     */
    void update(TEntity e) throws DataAccessExceptions;
    
    /**
     * Method retrieves all entities
     * @return
     * @throws DataAccessExceptions 
     */
    List<TEntity> findAll() throws DataAccessExceptions;
    
}
