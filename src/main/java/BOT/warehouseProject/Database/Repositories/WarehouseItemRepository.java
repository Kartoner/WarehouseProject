package BOT.warehouseProject.Database.Repositories;

import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {

    @Query("SELECT wi FROM WarehouseItem wi WHERE wi.itemType = ?1")
    List<WarehouseItem> findByItemType(ItemType itemType);
}
