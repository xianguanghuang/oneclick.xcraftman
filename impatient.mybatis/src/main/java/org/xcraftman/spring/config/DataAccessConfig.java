package org.xcraftman.spring.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/6/29.
 */
public class DataAccessConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.source_1")
    public DataSource dataSource1(){
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.source_2")
    public DataSource dataSource2(){
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }
}
