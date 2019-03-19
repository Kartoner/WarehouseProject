package BOT.warehouseProject.Domain.Services.Implementations;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Database.Repositories.DeliveryRepository;
import BOT.warehouseProject.Database.Repositories.WarehouseItemRepository;
import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Enums.ItemType;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class WarehouseServiceImplementation implements IWarehouseService {

    private static final Logger log = LoggerFactory.getLogger(WarehouseServiceImplementation.class);

    @Autowired
    private WarehouseItemRepository warehouseItemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Boolean createWarehouseItem(String itemName, ItemType itemType, String itemDescription, Double price) {
        return null;
    }

    @Override
    public Boolean updateStock(Long id, Integer quantity) {
        return null;
    }

    @Override
    public Boolean updateItemInfo(ItemType itemType, String itemDescription) {
        return null;
    }

    @Override
    public WarehouseItem getWarehouseItem(Long id) {
        return null;
    }

    @Override
    public List<WarehouseItem> getAllItems() {
        return null;
    }

    @Override
    public List<WarehouseItem> getItemsByType(ItemType itemType) {
        return null;
    }

    @Override
    public Boolean createDelivery(User employeeAccepting, User customerOrdering, String deliveryAddress, DeliveryStatus deliveryStatus, Map<WarehouseItem, Integer> itemsOrdered, Double overallPrice) {
        return null;
    }

    @Override
    public Boolean updateDelivery(User employeeAccepting, User customerOrdering, String deliveryAddress, DeliveryStatus deliveryStatus, Map<WarehouseItem, Integer> itemsOrdered, Double overallPrice, Boolean isPaid) {
        return null;
    }

    @Override
    public Boolean checkDelivery(Delivery delivery) {
        return null;
    }

    @Override
    public Delivery getDelivery(Long id) {
        return null;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return null;
    }

    @Override
    public List<Delivery> getDeliveriesByStatus(DeliveryStatus deliveryStatus) {
        return null;
    }
}
