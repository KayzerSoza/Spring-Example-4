package com.example.conferencedemo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
  @Bean
  public DataSource dataSource(){
    DataSourceBuilder builder=DataSourceBuilder.create();
    builder.url("write url for database");
    builder.username("write username");
    builder.password("write password");
    System.out.println("CUSTOM DATASOURCE BEAN INITIALIZED AND SET ");
    return builder.build();
  }
}
