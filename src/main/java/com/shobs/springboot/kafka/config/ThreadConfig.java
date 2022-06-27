package com.shobs.springboot.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
public class ThreadConfig {

    @Value("${app.thread-config.core-pool-size:2}")
    private int corePoolSize;

    @Value("${app.thread-config.max-pool-size:10}")
    private int maxPoolSize;

    @Primary
    @Bean(name = "skExecutor")
    public TaskExecutor threadPoolTaskExecutor() {
        log.info("Thread pool config; corePoolSize {}, maxPoolSize {}", corePoolSize, maxPoolSize);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setThreadNamePrefix("app-thread");
        executor.initialize();
        return executor;
    }
}