package BOT.warehouseProject.Domain.Services;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Enums.ItemType;

import java.util.List;
import java.util.Map;

public interface IWarehouseService {
    public Boolean createWarehouseItem(WarehouseItem warehouseItem);

    public Boolean updateStock(Long id, Integer quantity);

    public Boolean updateItemInfo(Long id, ItemType itemType, String itemDescription);

    public WarehouseItem getWarehouseItem(Long id);

    public List<WarehouseItem> getAllItems();

    public List<WarehouseItem> getItemsByType(ItemType itemType);

    public Boolean createDelivery(Delivery delivery);

    public Boolean updateDelivery(Delivery delivery);

    public Boolean checkDelivery(Delivery delivery);

    public Delivery getDelivery(Long id);

    public List<Delivery> getAllDeliveries();

    public List<Delivery> getDeliveriesByStatus(DeliveryStatus deliveryStatus);
}
