//package com.vn.education.config.batch;
//
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    @Autowired
//    @Qualifier("postgresSourceDataSource")
//    private DataSource batchDataSource;
//
//    @Bean
//    public JobRepository jobRepository(
//            JobExplorer jobExplorer,
//            PlatformTransactionManager transactionManager,
//            @Qualifier("postgresSourceDataSource") DataSource dataSource) throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.setDatabaseType("POSTGRES");
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(@Qualifier("postgresSourceDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource); // Sử dụng dataSource được tiêm với tên 'postgresSourceDataSource'
//    }
//}
