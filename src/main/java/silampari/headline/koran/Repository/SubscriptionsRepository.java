package silampari.headline.koran.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import silampari.headline.koran.domain.dao.Subscriptions;

@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Integer> {

}
