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
import org.testng.annotations.BeforeTest;
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
   
    private Player pl;
    
    @Autowired
    private PlayerServiceImpl playerService;
    
    @BeforeTest
    public void init(){      
        this.pl = new Player();       
    } 
    
    @Test
    public void playerCreateTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
    
        this.pl.setName("Karel Novák");
        playerService.create(this.pl);
        Assert.assertEquals(this.pl.getName(), playerService.findByName("Karel Novák").get(0).getName());
        
        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void playerUpdateTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        this.pl.setName("Karel Bureš");       
        playerService.update(this.pl);
        Assert.assertEquals(this.pl.getName(), playerService.findByName("Karel Bureš").get(0).getName());
        
        em.getTransaction().commit();
        em.close();

    }
    
    @Test
    public void playerDeleteTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        playerService.remove(this.pl);
        Assert.assertEquals(true, playerService.findAll().isEmpty());
        
        em.getTransaction().commit();
        em.close();

    }    
    
    
}
