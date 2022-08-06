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
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubscriptionDto implements Serializable {

    private static final long serialVersionUID = -109305084755175472L;

    private Integer packetId;
    private Integer idCustomer;
    private String numberOfRekeningSender;
    private String sender;
    private Integer grandTotal;
    private Integer totalPayment;
    private String documentTransaction;
    private String numberOfRekeningPayment;



}
