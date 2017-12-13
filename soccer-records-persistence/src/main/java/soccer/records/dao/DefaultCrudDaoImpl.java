/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import soccer.records.exceptions.dao.DataAccessExceptions;
import soccer.records.entity.DefaultEntity;

/**
 *
 * @author Michaela Bocanova
 * @param <TEntity>
 * @param <TKey>
 */
//@Repository
public abstract class DefaultCrudDaoImpl<TEntity extends DefaultEntity<TKey>,TKey> implements DefaultCrudDao<TEntity,TKey> {
    
    @PersistenceContext
    protected EntityManager em;
    
    protected Class<TKey> keyClass;
    protected Class<TEntity> entityClass;
       
    public DefaultCrudDaoImpl(Class<TEntity> entityClass, Class<TKey> keyClass) {
		super();
		this.keyClass = keyClass;
		this.entityClass = entityClass;
	}
            
    @Override
    public void create(TEntity e) throws DataAccessExceptions {
        try {
            em.persist(e);
        } catch(Exception ex){
            throw new DataAccessExceptions(ex.getMessage());          
        }
    }
        
    @Override
    public void update(TEntity e) throws DataAccessExceptions {
        try {
            em.merge(e);
        } catch(Exception ex){
            throw new DataAccessExceptions(ex.getMessage());          
        }        
    }
    
    @Override
    public void delete(TEntity e) throws DataAccessExceptions {
        try {
            em.remove(em.merge(e));
        } catch(Exception ex){
            throw new DataAccessExceptions(ex.getMessage());          
        }
    }
        
    @Override
    public TEntity findById(TKey id) throws DataAccessExceptions {
        try {
            return em.find(entityClass, id);
        } catch(Exception e){
            throw new DataAccessExceptions(e.getMessage());          
        }
    }
    
}
