package ApiThreadServices.ApiThreadServices.v1.Controller;


import ApiThreadServices.ApiThreadServices.v1.Services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/threadService")
public class ApiCallController {

  @Autowired
  ApiService apiService;

  @PostMapping("/job/submit")
  public Map<String, String> callApiUsingThread() {
    String uuid = UUID.randomUUID().toString();
    apiService.callThreadService(uuid);
    return Map.of("job_id", uuid);
  }


  @GetMapping("/job/{id}")
  public Map<String, String> getInfo(@PathVariable("id") String id) {
    return Map.of("status", apiService.getJobStatus(id));
  }

}
