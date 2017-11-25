/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import soccer.records.entity.Player;
import soccer.records.entity.Match;
import soccer.records.entity.PlayerResult;

/**
 *
 * @author Radim Vidlák
 */
@Repository
public class PlayerResultDaoImpl implements PlayerResultDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(PlayerResult pr) {
        em.persist(pr);
    }

    @Override
    public void update(PlayerResult pr) {
        em.merge(pr);
    }

    @Override
    public void delete(PlayerResult pr) {
        em.remove(em.merge(pr));
    }

    @Override
    public PlayerResult findByID(Long id) {
        return em.createQuery("select pr from PlayerResult pr WHERE pr.id = :id", PlayerResult.class).setParameter("id", id).getSingleResult();
    }
    
    @Override
    public List<PlayerResult> findByPlayerID(Long id) {
        return em.createQuery("select pr from PlayerResult pr WHERE pr.player = :player", PlayerResult.class).setParameter("player", id).getResultList();
    }

    @Override
    public List<PlayerResult> findByMatchID(Long id) {
        return em.createQuery("select pr from PlayerResult pr WHERE pr.match = :match", PlayerResult.class).setParameter("match", id).getResultList();
    }
    
    @Override
    public PlayerResult findByBoth(Long playerID, Long machId){
        return em.createQuery("select pr from PlayerResult pr WHERE pr.player =:player AND pr.match =:match", PlayerResult.class).setParameter("player", playerID).setParameter("match", machId).getSingleResult();
    }

    @Override
    public List<PlayerResult> findAll() {
        return em.createQuery("select pr from PlayerResult pr", PlayerResult.class).getResultList();
    }

}
