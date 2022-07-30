package silampari.headline.koran.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PdfNewsDto implements Serializable {

    public static final long serialVersionUID = -2684191005657768982L;

    private Integer id;
    private String title;
    private String description;
    private String dateEdition;
    private String thumbnail;
    private String pdf;

}
