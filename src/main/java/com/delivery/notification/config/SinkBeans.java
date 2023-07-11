package com.delivery.notification.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Sinks;
@Configuration
public class SinkBeans {
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> ds = DataSourceBuilder.create();
		ds.username("root");
		ds.password("password");
		ds.url("jdbc:mysql://localhost:3306/delivery");
		ds.driverClassName("com.mysql.cj.jdbc.Driver");
		return ds.build();
	}
	@Bean
	public JpaProperties jpaProperties() {
		JpaProperties jpa = new JpaProperties();
		jpa.setGenerateDdl(true);
		jpa.setDatabase(Database.MYSQL);
		jpa.setShowSql(false);
		return jpa;
	}
	@Bean
	public HibernateProperties hibernateProperties() {
		HibernateProperties h = new HibernateProperties();
		h.setDdlAuto("update");
		return h;
	}
	@Bean("sink")
	@Scope("prototype")
	
	public Sinks.Many<String> sink1(){
//		return Sinks.one()
		return Sinks.many().unicast().onBackpressureBuffer();
	}
	@Bean("sinktest")
	
	public Sinks.Many<String> sinktest(){
//		return Sinks.one()
		return Sinks.many().unicast().onBackpressureBuffer();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
