package com.ots.trainingapi.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private DataSource dataSource;
    
    @PostConstruct
    public void migrate() {
        Boolean flywayEnabled = Boolean.valueOf(environment.getProperty("spring.flyway.enabled"));
        
        if(flywayEnabled != null && flywayEnabled) {
            String[] locations = environment.getProperty("spring.flyway.locations", String[].class);
            String table = environment.getProperty("spring.flyway.table", String.class);
            
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations(locations)
                    .table(table)
                    .validateOnMigrate(false)
                    .load();
            
            flyway.migrate();
        }
    }
}
