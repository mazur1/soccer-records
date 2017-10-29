package soccer.records;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import soccer.records.entity.Player;
import soccer.records.entity.Team;
import soccer.records.services.PlayerServiceImpl;
import soccer.records.services.TeamServiceImpl;

public class MainJavaSe {
    
	private static EntityManagerFactory emf;

	public static void main(String[] args) throws SQLException {
            
		// start up a in-memory database
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");

		// soccer records begin
		
                System.out.println("TEST");

		// soccer records end
                
		emf.close();
                appContext.close();
	}


	private static void test1() {
            //Player tt = new Player();
            //tt.setName("one");
            //PlayerServiceImpl tsi = new PlayerServiceImpl();
            //tsi.create(tt);
            //List<Player> result = tsi.findByName("one");
            //System.out.println("name is:");
            //System.out.println(result.getName());
            
            
	}

}
