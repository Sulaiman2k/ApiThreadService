package ApiThreadServices.ApiThreadServices.v1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ApiService {

    @Value("${spring.thread.executor}")
    private int threadsExecutor ;

    private int  noOfRequest ;

    @Autowired
    CallableService callableService ;

    @Autowired
    RestTemplate restTemplate ;

   public List<Future<Object>> callThreadService(){
       ExecutorService executor = Executors.newFixedThreadPool(threadsExecutor) ;
       List<Future<Object>> list = new ArrayList<Future<Object>>() ;
       noOfRequest = 10 ;
       for(int loop = 0 ; loop < noOfRequest ; loop++ ){
           Future<Object> future = executor.submit(callableService) ;
           list.add(future) ;
       }
       executor.shutdown();
       return list ;
   }


}
