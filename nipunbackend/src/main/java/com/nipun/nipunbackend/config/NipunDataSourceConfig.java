package com.nipun.nipunbackend.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.nipun.nipunbackend.repository.nipun", 
    entityManagerFactoryRef = "nipuEntityManagerFactory",
    transactionManagerRef = "nipunTransactionManager"
)
public class NipunDataSourceConfig {
	
	 @Primary
	    @Bean(name = "nipunDataSource")
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }

	    @Primary
	    @Bean(name = "nipuEntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	        EntityManagerFactoryBuilder builder,
	        @Qualifier("nipunDataSource") DataSource dataSource) {
	        return builder
	                .dataSource(dataSource)
	                .packages("com.nipun.nipunbackend.model.nipun") 
	                .persistenceUnit("rfiPU")
	                .build();
	    }
	    
	    

	    @Primary
	    @Bean(name = "nipunTransactionManager")
	    public PlatformTransactionManager transactionManager(
	        @Qualifier("nipuEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
	        return new JpaTransactionManager(entityManagerFactory);
	    }
	

}
