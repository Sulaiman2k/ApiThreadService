package ApiThreadServices.ApiThreadServices.v1.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MockResponse {

    @JsonProperty("name")
    private String name ;

    @JsonProperty("city")
    private String city ;

}
