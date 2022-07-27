package silampari.headline.koran.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import silampari.headline.koran.domain.dao.PacketNews;

@Repository
public interface PackageNewsRepository extends JpaRepository<PacketNews, Integer> {


}
