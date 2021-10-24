package ApiThreadServices.ApiThreadServices.v1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class Config {

  public static final int DEFAULT_WORKER_POOL_SIZE = 10;

  @Bean("ThreadPoolExecutor")
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    var executor = new ThreadPoolTaskExecutor();
    executor.setThreadNamePrefix("BG-JOB");
    executor.setCorePoolSize(DEFAULT_WORKER_POOL_SIZE);
    executor.setMaxPoolSize(DEFAULT_WORKER_POOL_SIZE);
    executor.setQueueCapacity(2 * DEFAULT_WORKER_POOL_SIZE);
    return executor;
  }
}
