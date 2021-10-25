package ApiThreadServices.ApiThreadServices.v1.Services;

import ApiThreadServices.ApiThreadServices.v1.Util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class CallableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallableService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    JedisUtil jedisUtil;

    @Async("ThreadPoolExecutor")
    public CompletableFuture<Boolean> makeApiCallAndSaveInRedis(String redisKey, String url){
        var  redisValue = restTemplate.getForObject(url, String.class);
        jedisUtil.setValue(redisKey, redisValue) ;
        var savedValues =  jedisUtil.valueGet(redisKey);
        LOGGER.info("value saved in redis {}", savedValues);
        return CompletableFuture.completedFuture(true);
    }

}
