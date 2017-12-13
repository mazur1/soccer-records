package soccer.records.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import soccer.records.PersistenceAppContext;

@Configuration
@Import(PersistenceAppContext.class)
@ComponentScan(basePackages = "soccer.records")
public class ServiceConfiguration {

    /*@Bean
    public TeamFacade teamFacade() {
        return new TeamFacadeImpl();
    }*/
    
    @Bean
    public Mapper dozer(){
            DozerBeanMapper dozer = new DozerBeanMapper();		
            //dozer.addMapping(new DozerCustomConfig());
            return dozer;
    }

    /**
     * Custom config for Dozer if needed
     * @author nguyen
     *
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {

        }
    }
	
}

