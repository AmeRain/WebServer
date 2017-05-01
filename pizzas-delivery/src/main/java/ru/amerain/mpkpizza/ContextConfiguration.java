package ru.amerain.mpkpizza;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.amerain.mpkpizza.data.jdbc.DbDataSourceFabric;

@Configuration
public class ContextConfiguration {
    @Bean
    public DbDataSourceFabric dbDataSourceFabric()
    {
        return new DbDataSourceFabric();
    }

}
