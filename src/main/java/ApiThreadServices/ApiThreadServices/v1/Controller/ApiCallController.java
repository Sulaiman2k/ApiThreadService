package ApiThreadServices.ApiThreadServices.v1.Controller;



import ApiThreadServices.ApiThreadServices.v1.Services.ApiService;
import ApiThreadServices.ApiThreadServices.v1.Util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;


@RestController
@RequestMapping("/ApiCall")
public class ApiCallController {

    @Autowired
    ApiService apiService ;

    @RequestMapping("/")
    public List<Future<Object>> callApiUsingThread() {
        List<Future<Object>> result = apiService.callThreadService() ;
        return result ;
    }

}
