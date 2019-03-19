package BOT.warehouseProject.Database.Repositories;

import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {
}
