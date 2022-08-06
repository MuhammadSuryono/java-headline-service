package silampari.headline.koran.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import silampari.headline.koran.domain.dao.PdfNews;

import java.util.List;

@Repository
public interface PdfNewsRepository extends JpaRepository<PdfNews,Integer> {
    List<PdfNews> findFirstByOrderByCreatedAtDesc();
}
