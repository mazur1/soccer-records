package soccer.records.sampledata;

//import cz.fi.muni.pa165.service.config.ServiceConfiguration;
import soccer.records.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import soccer.records.services.*;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 *
 * @author Tomas Mazurek
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SRWithSampleDataConfiguration {

    //final static Logger log = LoggerFactory.getLogger(SRSampleDataConfiguration.class);

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        //log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
