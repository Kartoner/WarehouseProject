package BOT.warehouseProject.Domain.Service;

import BOT.warehouseProject.Domain.Entity.Delivery;
import BOT.warehouseProject.Domain.Entity.WarehouseItem;
import BOT.warehouseProject.Domain.Enum.DeliveryStatus;
import BOT.warehouseProject.Domain.Enum.ItemType;

import java.util.List;
import java.util.Optional;

public interface IWarehouseService {
    public Boolean createWarehouseItem(WarehouseItem warehouseItem);

    public Boolean updateItemInfo(WarehouseItem warehouseItem);

    public Boolean deleteWarehouseItem(Long id);

    public Optional<WarehouseItem> getWarehouseItem(Long id);

    public List<WarehouseItem> getAllItems();

    public List<WarehouseItem> getItemsByType(ItemType itemType);

    public Optional<WarehouseItem> getItemByName(String itemName);

    public Boolean createDelivery(Delivery delivery);

    public Boolean updateDelivery(Delivery delivery);

    public Boolean deleteDelivery(Long id);

    public Optional<Delivery> getDelivery(Long id);

    public List<Delivery> getAllDeliveries();

    public List<Delivery> getDeliveriesByStatus(DeliveryStatus deliveryStatus);
}
