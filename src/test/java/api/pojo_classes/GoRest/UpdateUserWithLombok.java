package api.pojo_classes.GoRest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data // with the help @Data, we can eliminate getters and setters
@Builder // we are able to assign the value to attributes
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UpdateUserWithLombok {

    @Builder.Default
    private String gender = "female";
    private String email;
    // name and status variable are added when we are doing cucumber test
    private String name;
    private String status;
}
