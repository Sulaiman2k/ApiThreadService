package ApiThreadServices.ApiThreadServices.v1.Controller;


import ApiThreadServices.ApiThreadServices.v1.Util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ApiCall")
public class ApiCallController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public Object callApiUsingThread() {
        Object[] redisValue = restTemplate.getForObject("https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8" , Object[].class );
        return redisValue[0] ;
    }
}
