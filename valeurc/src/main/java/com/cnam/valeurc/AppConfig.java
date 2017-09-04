package com.cnam.valeurc;

import org.codehaus.plexus.component.annotations.Configuration;





@Configuration 
@ComponentScan(basePackages = { "com.cnam.valeurc" }) 
@PropertySource(value="classpath:config.properties", ignoreResourceNotFound=true) 
public class AppConfig { 
/**
 *
 * @author George Harik
 */
    @Autowired 
    private Environment env; 

    @Bean 
    public MongoTemplate mongoTemplate() throws Exception { 

        String db_host = env.getProperty("mongodb.host");//To be changed       
        String db_port = env.getProperty("mongodb.port");//To be changed
        String db_name = env.getProperty("mongodb.db"); //To be changed
         
        MongoClient mongo = new MongoClient(db_host, Integer.valueOf(db_port)); 
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, db_name); 
        return new MongoTemplate(mongoDbFactory); 

    } 
}