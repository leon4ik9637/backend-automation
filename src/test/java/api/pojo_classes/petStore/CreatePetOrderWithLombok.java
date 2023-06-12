package api.pojo_classes.petStore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CreatePetOrderWithLombok {

    /**
     * {
     *   "id": 0,
     *   "petId": 0,
     *   "quantity": 0,
     *   "shipDate": "2023-06-12T15:35:11.702Z",
     *   "status": "placed",
     *   "complete": true
     * }
     */

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean ifCompleted;


}
