package silampari.headline.koran.util;

import lombok.Builder;
import java.io.Serializable;

@Builder
public class ApiResponse<T extends Serializable> implements Serializable{
    private String responseKey;
    private String message;
    private T data;

}
