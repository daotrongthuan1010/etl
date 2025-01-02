package com.vn.education.config.system;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPool {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        // Lấy số lượng CPU khả dụng
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // Tạo ThreadPoolTaskExecutor
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(availableProcessors);
        executor.setMaxPoolSize(availableProcessors * 2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("JvmExecutor-");
        executor.initialize();
        return executor;
    }


}
