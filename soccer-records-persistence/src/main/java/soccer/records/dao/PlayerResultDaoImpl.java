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
        em.persist(pr);
    }

    @Override
    public void delete(PlayerResult pr) {
        em.remove(pr);
    }

    @Override
    public List<PlayerResult> findByPlayer(Player p) {
        return em.createQuery("select pr from PlayerResult pr WHERE pr.player = :player", PlayerResult.class).setParameter("player", p).getResultList();
    }

    @Override
    public List<PlayerResult> findByMatch(Match m) {
        return em.createQuery("select pr from PlayerResult pr WHERE pr.match = :match", PlayerResult.class).setParameter("match", m).getResultList();
    }
    
    @Override
    public PlayerResult findByBoth(Player p, Match m){
        return em.createQuery("select pr from PlayerResult pr WHERE pr.player =:player AND pr.match =:match", PlayerResult.class).setParameter("player", p).setParameter("match", m).getSingleResult();
    }

    @Override
    public List<PlayerResult> findAll() {
        return em.createQuery("select pr from PlayerResult pr", PlayerResult.class).getResultList();
    }

}
