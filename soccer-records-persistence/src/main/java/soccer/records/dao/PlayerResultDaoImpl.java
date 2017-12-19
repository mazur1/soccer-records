/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;


import soccer.records.exceptions.dao.DataAccessExceptions;

import org.springframework.stereotype.Repository;

import soccer.records.entity.PlayerResult;

/**
 *
 * @author Radim Vidlák
 */
@Repository
public class PlayerResultDaoImpl extends DefaultCrudDaoImpl<PlayerResult,Long> implements PlayerResultDao {

    /*@PersistenceContext
    private EntityManager em;*/

    public PlayerResultDaoImpl() {        
        super(PlayerResult.class, Long.class);
    }

    /*@Override
    public void create(PlayerResult pr) {
        try{
            em.persist(pr);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }

    @Override
    public void update(PlayerResult pr) {
        try{
            em.merge(pr);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }
    }

    @Override
    public void delete(PlayerResult pr) {
        try{      
            em.remove(em.merge(pr));
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }        
    }

    @Override
    public PlayerResult findById(Long id) {
        try {
            return em.createQuery("select pr from PlayerResult pr WHERE pr.id = :id", PlayerResult.class).setParameter("id", id).getSingleResult();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }  
    }*/
    
    @Override
    public void delete(PlayerResult t) {
        try {
            t.setIsActive(false);
            em.merge(t);
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
    @Override
    public List<PlayerResult> findByPlayerID(Long id) throws DataAccessExceptions {
        try{
            return em.createQuery("select pr from PlayerResult pr WHERE pr.player.id = :player", PlayerResult.class).setParameter("player", id).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }  
    }

    @Override
    public List<PlayerResult> findByMatchID(Long id) throws DataAccessExceptions {
        try {
            return em.createQuery("select pr from PlayerResult pr WHERE pr.match.id = :match", PlayerResult.class).setParameter("match", id).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }  
    }
    
    @Override
    public PlayerResult findByBoth(Long playerID, Long machId) throws DataAccessExceptions {        
        try{
            return em.createQuery("select pr from PlayerResult pr WHERE pr.player.id =:player AND pr.match.id =:match", PlayerResult.class).setParameter("player", playerID).setParameter("match", machId).getSingleResult();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }      
    }

    @Override
    public List<PlayerResult> findAll() throws DataAccessExceptions {
        try {
            return em.createQuery("select pr from PlayerResult pr", PlayerResult.class).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());  
        }          
    }

}
