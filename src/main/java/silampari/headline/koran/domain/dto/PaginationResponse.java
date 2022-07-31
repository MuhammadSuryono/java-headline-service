package silampari.headline.koran.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import silampari.headline.koran.domain.dao.PdfNews;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaginationResponse<T> implements Serializable {
    private static final long serialVersionUID = 5414975445516496207L;

    private int totalPage;
    private int totalData;
    private int totalCurrentData;
    private int limit;
    private int pageNumber;
    private T pdfNews;
}
