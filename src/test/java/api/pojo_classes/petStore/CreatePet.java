package api.pojo_classes.petStore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CreatePet {
    /**
     * {
     *     "id": 1,
     *     "category": {
     *         "id": 1,
     *         "name": "Dog"
     *     },
     *     "name": "Lucy",
     *     "photoUrls": [
     *         "string"
     *     ],
     *     "tags": [
     *         {
     *             "id": 1,
     *             "name": "Tucker"
     *         }
     *     ],
     *     "status": "available"
     * }
     */

    private int id;
    private Category category;
    private String name;
    // Inside the List there is no Object
    // There is only a value
    private List<String> photoUrls;
    // Variable name of the class has to be the same
    // with the attribute name in the JSON body
    private List<Tags> tags;
    private String status;


}
