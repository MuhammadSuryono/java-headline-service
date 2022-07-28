package silampari.headline.koran.util;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ApiResponse<T extends Serializable> implements Serializable{
    private String responseKey;
    private String message;
    private T data;

}
