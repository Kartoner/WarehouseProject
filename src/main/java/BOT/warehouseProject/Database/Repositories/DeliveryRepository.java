package BOT.warehouseProject.Database.Repositories;

import BOT.warehouseProject.Domain.Entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
