package BOT.warehouseProject.Domain.Services;

import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Enums.ItemType;

import java.util.List;

public interface IWarehouseService {
    public Boolean createWarehouseItem(WarehouseItem warehouseItem);

    public Boolean updateStock(Long id, Integer quantity);

    public Boolean updateItemInfo(WarehouseItem warehouseItem);

    public Boolean deleteWarehouseItem(Long id);

    public WarehouseItem getWarehouseItem(Long id);

    public List<WarehouseItem> getAllItems();

    public List<WarehouseItem> getItemsByType(ItemType itemType);

    public Boolean createDelivery(Delivery delivery);

    public Boolean updateDelivery(Delivery delivery);

    public Boolean deleteDelivery(Long id);

    public Delivery getDelivery(Long id);

    public List<Delivery> getAllDeliveries();

    public List<Delivery> getDeliveriesByStatus(DeliveryStatus deliveryStatus);
}
