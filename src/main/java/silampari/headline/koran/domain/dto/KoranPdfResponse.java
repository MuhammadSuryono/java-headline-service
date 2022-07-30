package silampari.headline.koran.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KoranPdfResponse implements Serializable {

    public static final long serialVersionUID = -2929667142722084445L;

    private int totalPage;
    private int totalElement;
    private int numberOfElement;
    private int pageNumber;
    private List<PdfNewsDto> pdfNews;


}
