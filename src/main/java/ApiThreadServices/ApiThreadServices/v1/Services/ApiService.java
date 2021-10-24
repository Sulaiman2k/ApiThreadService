package ApiThreadServices.ApiThreadServices.v1.Services;

import ApiThreadServices.ApiThreadServices.v1.ApiConstants;
import ApiThreadServices.ApiThreadServices.v1.Util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ApiService {

  @Value("${spring.thread.executor}")
  private int threadsExecutor;

  @Autowired
  CallableService callableService;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  JedisUtil jedisUtil;

  private static final String COMPLETED = "COMPLETED";
  private static final String IN_PROGRESS = "IN_PROGRESS";
  private static final String FAILED =  "FAILED";

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

  @Async("ThreadPoolExecutor")
  public void callThreadService(String jobId) {
    try {
      markJob(IN_PROGRESS, jobId);
      ExecutorService executor = Executors.newFixedThreadPool(threadsExecutor);
      List<CompletableFuture<Boolean>> results = new ArrayList<>();
      ApiConstants.API.forEach((key, value) -> {
        results.add(callableService.makeApiCallAndSaveInRedis(key, ApiConstants.HOST + value));
      });
      CompletableFuture.allOf(results.stream().toArray(CompletableFuture[]::new)).join();
      markJob(COMPLETED, jobId);
    } catch (Exception exception) {
      LOGGER.error("Something went wrong", exception);
      markJob(FAILED, jobId);
    }
  }

  private void markJob(String status, String jobid) {
    jedisUtil.setValue(jobid, status);
  }

  public String getJobStatus(String jobid){
    return jedisUtil.get(jobid);
  }
}

