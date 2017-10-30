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
    * @param p Player of certain PlayerResult to find
    * @return List of PlayerResults with certain player 
    */
    public List<PlayerResult> findByPlayer(Player p);
    /**
    * Method to find List of PlayerResults by Match.
    * @param m Player of certain PlayerResult to find
    * @return List of PlayerResults with certain match 
    */
    public List<PlayerResult> findByMatch(Match m);
    /**
    * Method to find PlayerResults by Player and Match.
    * @param p Player of certain PlayerResult to find
    * @param m Match of certain PlayerResult to find
    * @return PlayerResult with certain player and match
    */
    public PlayerResult findByBoth(Player p, Match m);
    /**
    * Method to find all PlayerResults.
    * @return return List of all teams
    */
    public List<PlayerResult> findAll();
    
}
