package soccer.records.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import soccer.records.PersistenceContext;
import soccer.records.entity.Player;
import soccer.records.services.PlayerServiceImpl;

public class PlayerTest {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void playerTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        
        Player pl = new Player();
        pl.setName("Karel Novák");
        
        playerService.create(pl);
        
        Assert.assertEquals(pl.getName(), playerService.findByName("Karel Novák").get(0).getName());

        em.getTransaction().commit();
        em.close();

    }

}
