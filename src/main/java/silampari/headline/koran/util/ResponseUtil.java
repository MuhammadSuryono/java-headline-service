package silampari.headline.koran.util;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import silampari.headline.koran.domain.dto.PaginationResponse;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

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

    public static <T extends Serializable> PaginationResponse buildPaginationResponse(T data, int totalCurrentData, int totalData, Pageable pageable) {
        int totalPage = totalData / pageable.getPageSize();

        return PaginationResponse.builder()
                .totalPage(totalPage == 0 ? 1 : totalPage)
                .totalData(totalData)
                .totalCurrentData(totalCurrentData)
                .pageNumber(pageable.getPageNumber() + 1)
                .limit(pageable.getPageSize())
                .pdfNews(data)
                .build();
    }

}
