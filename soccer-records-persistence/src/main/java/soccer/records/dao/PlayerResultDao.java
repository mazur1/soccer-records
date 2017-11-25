/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;

import soccer.records.entity.Player;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;

/**
 *
 * @author Radim Vidlák
 */
public interface PlayerResultDao {
    /**
    * Method to create PlayerResult in db.
    * @param pr PlayerResult to create
    */
    public void create(PlayerResult pr);
    /**
    * Method to update PlayerResult in db.
    * @param pr PlayerResult to update
    */
    public void update(PlayerResult pr);
    /**
    * Method to delete PlayerResult from db.
    * @param pr PlayerResult to delete
    */
    public void delete(PlayerResult pr);
    
    /**
    * Method to find List of PlayerResults by Player.
    * @param id
    * @return PlayerResult by id
    */
    public PlayerResult findByID(Long id);
    
    /**
     * 
     * @param id
     * @return List of PlayerResults with defined player id
     */
    public List<PlayerResult> findByPlayerID(Long id);
    /**
    * Method to find List of PlayerResults by Match.
    * @param id
    * @return List of PlayerResults with defined match id 
    */
    public List<PlayerResult> findByMatchID(Long id);
    /**
    * Method to find PlayerResults by Player and Match.
    * @param playerID
    * @param machId
    * @return PlayerResult with certain player and match
    */
    public PlayerResult findByBoth(Long playerID, Long machId);
    /**
    * Method to find all PlayerResults.
    * @return return List of all teams
    */
    public List<PlayerResult> findAll();
    
}
