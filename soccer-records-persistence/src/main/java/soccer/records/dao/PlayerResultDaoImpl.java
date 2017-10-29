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
        return em.createQuery("select pr from PlayerResult pr WHERE pr.player = :playerId", PlayerResult.class).setParameter(":playerId", p.getId()).getResultList();
    }

    @Override
    public List<PlayerResult> findByMatch(Match m) {
        return em.createQuery("select pr from PlayerResult pr WHERE pr.match = :matchId", PlayerResult.class).setParameter(":matchId", m.getId()).getResultList();
    }
    
    @Override
    public PlayerResult findByBoth(Player p, Match m){
        return em.createQuery("select pr from PlayerResult pr WHERE pr.player =:playerId AND pr.match =:matchId", PlayerResult.class).setParameter(":playerId", p.getId()).setParameter(":matchId", m.getId()).getSingleResult();
    }

}
