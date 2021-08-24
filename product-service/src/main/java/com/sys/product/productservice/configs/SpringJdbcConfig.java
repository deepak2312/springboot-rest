package com.sys.product.productservice.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringJdbcConfig {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/product_service");
        dataSource.setUsername("product_user");
        dataSource.setPassword("product_user");

        return dataSource;
    }
    
    @Bean
    JdbcTemplate jdbcTemplate() {
    	return new JdbcTemplate(mysqlDataSource());
    }
}