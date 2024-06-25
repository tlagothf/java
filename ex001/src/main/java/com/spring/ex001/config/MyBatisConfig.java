package com.spring.ex001.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("com.spring.ex001.mapper")
public class MyBatisConfig {
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource());
    return factoryBean.getObject();
  }
  @Bean
  public DataSource dataSource() {
	  HikariConfig config = new HikariConfig();
	  config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	  config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	  config.setUsername("user01");
	  config.setPassword("1234");

	  HikariDataSource ds = new HikariDataSource(config);
	  	return ds;
  }
  
}

