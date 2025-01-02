//package com.vn.education.config.db;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.vn.education.repository.postgres.target",
//        entityManagerFactoryRef = "postgresEntityManagerFactoryTarget",
//        transactionManagerRef = "postgresTransactionManagerTarget"
//)
//public class PostgresTargetConfig {
//
//    @Primary
//    @Bean(name = "postgresSourceDataTarget")
//    @Qualifier("postgresSourceDataTarget")
//    @ConfigurationProperties(prefix = "spring.datasource.postgres-target")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "postgresEntityManagerFactoryTarget")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("postgresSourceDataTarget") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.vn.education.entity.postgres.target")
//                .persistenceUnit("postgresSource")
//                .properties(hibernateProperties())
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "postgresTransactionManagerTarget")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("postgresEntityManagerFactoryTarget") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    private Map<String, Object> hibernateProperties() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.show_sql", "true");
//        return properties;
//    }
//
//}
