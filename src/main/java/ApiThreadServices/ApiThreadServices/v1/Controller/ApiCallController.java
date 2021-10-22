package ApiThreadServices.ApiThreadServices.v1.Controller;



import ApiThreadServices.ApiThreadServices.v1.Services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.concurrent.Future;


@RestController
@RequestMapping("/threadService")
public class ApiCallController {

    @Autowired
    ApiService apiService ;

    @RequestMapping("/apiCall")
    public void callApiUsingThread() {
       apiService.callThreadService() ;
    }

}
