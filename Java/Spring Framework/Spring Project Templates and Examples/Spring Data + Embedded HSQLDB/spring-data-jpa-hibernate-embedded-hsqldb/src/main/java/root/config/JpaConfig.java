package root.config;

import java.util.Properties;
import javax.annotation.Resource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("root")
@PropertySource("classpath:jpa/hibernate.properties")
@EnableJpaRepositories("root")
public class JpaConfig 
{
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
 
    @Resource
    private Environment environment;
    
    @Autowired
    private DbConfig dbConfig;
 
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dbConfig.dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));         
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        return entityManagerFactoryBean;
    }
    
 
    private Properties jpaProperties() 
    {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));        
        properties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        properties.put(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS));
        return properties;
    }    
 
    @Bean
    public JpaTransactionManager transactionManager() 
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }     
}
