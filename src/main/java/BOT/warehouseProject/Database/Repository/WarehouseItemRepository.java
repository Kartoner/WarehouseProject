package BOT.warehouseProject.Database.Repository;

import BOT.warehouseProject.Domain.Entity.WarehouseItem;
import BOT.warehouseProject.Domain.Enum.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {

    @Query("SELECT wi FROM WarehouseItem wi WHERE wi.itemType = ?1")
    List<WarehouseItem> findByItemType(ItemType itemType);

    @Query("SELECT wi FROM WarehouseItem wi WHERE wi.itemName = ?1")
    Optional<WarehouseItem> findByItemName(String itemName);
}
