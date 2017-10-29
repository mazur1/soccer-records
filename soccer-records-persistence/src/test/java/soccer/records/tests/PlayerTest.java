package soccer.records.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.dao.PlayerDao;

import soccer.records.entity.Player;
import soccer.records.services.PlayerServiceImpl;

@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    
    @Autowired
    private PlayerServiceImpl playerService;
    
    /*@Autowired
    private PlayerDao playerDao;*/
    
    @Test
    public void playerTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
                
        Player pl = new Player();
        pl.setName("Karel Novák");
        
        playerService.create(pl);
        //playerDao.create(pl);
        
        Assert.assertEquals(pl.getName(), playerService.findByName("Karel Novák").get(0).getName());

        em.getTransaction().commit();
        em.close();

    }

}
