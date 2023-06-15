package api.pojo_classes.GoRest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data // with the help @Data, we can eliminate getters and setters
@Builder // we are able to assign the value to attributes
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PaginationComment {

    private int total;
    private int pages;
    private int page;
    private int limit;
    private Links links;



}
