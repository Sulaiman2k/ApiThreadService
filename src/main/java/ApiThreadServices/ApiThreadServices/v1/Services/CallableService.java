package ApiThreadServices.ApiThreadServices.v1.Services;


import ApiThreadServices.ApiThreadServices.v1.DTO.MockResponse;
import ApiThreadServices.ApiThreadServices.v1.Util.JedisUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.Callable;

@Component
public class CallableService implements Callable<Object> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    JedisUtil jedisUtil ;

//    @Override
//    public Object call() throws Exception{
//        var object = new  ArrayList<MockResponse>() ;
//        var  redisValue = restTemplate.getForObject("https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8" , String.class );
//        ObjectMapper objectMapper = new ObjectMapper() ;
//        var type = new TypeReference<ArrayList<MockResponse>>(){
//        };
//        objectMapper.readValue(redisValue , type) ;
//
//        return redisValue ;
//    }

        @Override
    public Object call() throws Exception{
        var  redisValue = restTemplate.getForObject("https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8" , String.class );
        jedisUtil.setValue( "check", redisValue ) ;
        jedisUtil.valueGet("check");

        return redisValue ;
    }

}
