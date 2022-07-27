package silampari.headline.koran.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "try_db", schema = "packet_news")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class packetNews {


}
