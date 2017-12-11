/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import soccer.records.entity.AppUser;
import soccer.records.enums.AppRole;
import soccer.records.exceptions.dao.DataAccessExceptions;

/**
 *
 * @author Michaela Bocanova
 */
@Repository
public class AppUserDaoImpl implements AppUserDao {
    
    @PersistenceContext
    private EntityManager em;
                
    @Override        
    public void create(AppUser u){
        try {
            em.persist(u);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
        
    @Override
    public void update(AppUser u) {
        try {
            em.merge(u);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
    @Override
    public void delete(AppUser u) {
        try {
            em.remove(findById(u.getId()));
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
        
    @Override
    public AppUser findById(Long id){
        try {
            return em.find(AppUser.class, id);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
        
    @Override        
    public List<AppUser> findAll() {
        try{
            return em.createQuery("select u from AppUser u", AppUser.class).getResultList();
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }        
    }
    
    @Override
    public AppUser findByUsername(String name) {
        try {
            return em.createQuery("select u from AppUser u where u.username = :name", AppUser.class).setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }
    
    //works?
    @Override
    public List<AppUser> findByRole(AppRole role) {
        try {
            return em.createQuery("select u from AppUser u where :role member of u.roles", AppUser.class).setParameter("role", role).getResultList();
        } catch (Exception e) {
            throw new DataAccessExceptions(e.getMessage());
        }
    }

}
