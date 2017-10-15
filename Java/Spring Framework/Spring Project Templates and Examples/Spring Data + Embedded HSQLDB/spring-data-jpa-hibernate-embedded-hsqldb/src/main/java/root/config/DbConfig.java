package root.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DbConfig 
{
    @Bean
    public DataSource dataSource() 
    {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = builder
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("sql/create-db.sql")
            .addScript("sql/data-to-insert.sql")
            .build();
        return database;
    }
}
