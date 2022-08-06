package silampari.headline.koran.domain.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idCustomer;

    @OneToOne()
    @JoinColumn(name = "packet_id", referencedColumnName = "id")

    private PacketNews packetId;
    private String packetName;
    private String price;
    private String disc;
    private Integer duration;
    private String unit;
    private String grandTotal;
    private String dateTransaction;
    private Enum status;
    private Boolean isVerification;
    private String documentTransaction;
    private String numberOfRekeningPayment;
    private String totalPayment;
    private String sender;
    private String numberOfRekeningSender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd' 'HH:mm:ss", timezone = "Asia/Jakarta")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd' 'HH:mm:ss", timezone = "Asia/Jakarta")
    private Date updatedAt;





}
