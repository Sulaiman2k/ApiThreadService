package ApiThreadServices.ApiThreadServices.v1.Services;


import ApiThreadServices.ApiThreadServices.v1.Util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@Component
public class CallableService implements Callable<Object> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    JedisUtil jedisUtil ;

    @Override
    public Object call() throws Exception{
        Object redisValue = restTemplate.getForObject("https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8" , Object.class );
        return redisValue ;
    }

}
