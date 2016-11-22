package com.customdatsource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

@Configuration
public class CustomDataSource {

    @Autowired
    private DataSourceUtils properties;

    @Bean
    @Configuration(prefix = DataSourceLookup.PREFIX) // this will inject other properties to the datasource
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(DruidDataSource.class)
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }
}