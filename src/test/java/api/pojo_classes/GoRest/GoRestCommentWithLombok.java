package api.pojo_classes.GoRest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data // with the help @Data, we can eliminate getters and setters
@Builder // we are able to assign the value to attributes
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GoRestCommentWithLombok {

    /**
     * {
     *     "code": 408,
     *     "meta": {
     *         "pagination": {
     *             "total": 2000,
     *             "pages": 211,
     *             "page": 1,
     *             "limit": 5,
     *             "links": {
     *                 "previous": null,
     *                 "current": "https://gorest.co.in/public/v1/comments?page=1",
     *                 "next": "https://gorest.co.in/public/v1/comments?page=2"
     *             }
     *         }
     *     },
     *     "data": [
     *              {
     *               "id": 2220,
     *               "post_id": 5585,
     *               "name": "Tech Global",
     *               "email": "random email",
     *               "body": "random text",
     *           },
     *              {
     *               "id": 2221,
     *               "post_id": 5586,
     *               "name": "Tech Global",
     *               "email": "random email",
     *               "body": "random text",
     *           },
     *              {
     *               "id": 2222,
     *               "post_id": 5587,
     *               "name": "Tech Global",
     *               "email": "random email",
     *               "body": "random text",
     *           }
     *     ]
     * }
     */

    private int code;
    private MetaComment meta;
    private List<DataComment> data;
}
