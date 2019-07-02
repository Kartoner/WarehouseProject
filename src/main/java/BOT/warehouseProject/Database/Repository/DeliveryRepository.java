package BOT.warehouseProject.Database.Repository;

import BOT.warehouseProject.Domain.Entity.Delivery;
import BOT.warehouseProject.Domain.Enum.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("SELECT d FROM Delivery d WHERE d.deliveryStatus = ?1")
    List<Delivery> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
