package soccer.records.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.dao.PlayerDao;

import soccer.records.entity.Player;
import soccer.records.enums.PlayerPost;
import soccer.records.services.PlayerService;
import soccer.records.services.PlayerServiceImpl;

/**
 *
 * @author Radim Vidlák
 */
@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;
   
    private Player pl;
    
    @Mock
    private PlayerService playerService;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeTest
    public void init(){      
        this.pl = new Player();       
    } 
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void notNullTest(){
        
        this.pl.setName("Karel");
        this.playerService.create(pl);

        this.pl.setSurname("Dvoøák");
        this.playerService.create(pl);        

        this.pl.setAge(30);
        this.playerService.create(pl); 

        this.pl.setPost(PlayerPost.ATTACKER);
        this.playerService.create(pl); 
        
        /* correct
        this.pl.setCaptian(false);
        this.playerService.create(pl); 
        */
    }
    
    @Test
    public void playerCreateTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
    
        this.pl.setName("Karel");
        this.pl.setSurname("Novák");
        this.pl.setAge(30);
        this.pl.setPost(PlayerPost.GOLMAN);
        this.pl.setCaptian(false);
        
        playerService.create(this.pl);
        Assert.assertEquals(this.pl.getName(), playerService.findByName("Karel","Novák").get(0).getName());
        
        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void playerUpdateTest() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        this.pl.setName("Karel");
        this.pl.setSurname("Bureš");
        playerService.update(this.pl);
        Assert.assertEquals(this.pl.getName(), playerService.findByName("Karel","Bureš").get(0).getName());
        
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
