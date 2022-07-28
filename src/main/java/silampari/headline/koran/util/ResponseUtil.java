package silampari.headline.koran.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class ResponseUtil {

    public static <T extends Serializable> ResponseEntity<Object> buildResponse(String responseKey, String messageKey, T data, HttpStatus httpStatus) {
        return new ResponseEntity(buildResponse(responseKey, messageKey, data), httpStatus);
    }

    public static <T extends Serializable> ApiResponse buildResponse(String responseKey, String messageKey, T data) {
        return buildResponse1(responseKey, messageKey, (Serializable) data);
    }

    public static <T extends Serializable> ApiResponse buildResponse1(String responseKey, String messageKey, T data) {
        return ApiResponse.builder().responseKey(responseKey).message(messageKey).data(data).build();
    }

}
