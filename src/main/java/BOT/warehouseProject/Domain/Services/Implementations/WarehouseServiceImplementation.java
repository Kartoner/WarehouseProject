package BOT.warehouseProject.Domain.Services.Implementations;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WarehouseServiceImplementation implements IWarehouseService {

    private static final Logger log = LoggerFactory.getLogger(WarehouseServiceImplementation.class);

    @Autowired
    private WarehouseItemRepository warehouseItemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Boolean createWarehouseItem(WarehouseItem warehouseItem) {
        try {
            warehouseItemRepository.save(warehouseItem);
        } catch (Exception ex) {
            log.info("Failed creating new item");

            return Boolean.FALSE;
        }
        log.info("Created new item");

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateStock(Long id, Integer quantity) {
        try {
            WarehouseItem warehouseItem = warehouseItemRepository.getOne(id);

            warehouseItem.setQuantity(quantity);

            warehouseItemRepository.save(warehouseItem);
        } catch (Exception ex) {
            log.info("Failed updating item");

            return Boolean.FALSE;
        }
        log.info("Updated item");

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateItemInfo(WarehouseItem warehouseItem) {
        try {
            //WarehouseItem warehouseItem = warehouseItemRepository.getOne(id);

            //warehouseItem.setItemType(itemType);
            //warehouseItem.setItemDescription(itemDescription);

            warehouseItemRepository.save(warehouseItem);
        } catch (Exception ex) {
            log.info("Failed updating item info");

            return Boolean.FALSE;
        }
        log.info("Updated item info");

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteWarehouseItem(Long id){
        try{
            warehouseItemRepository.deleteById(id);
        } catch (Exception ex){
            log.info("Failed deleting item");
        }
        log.info("Deleted item");

        return Boolean.TRUE;
    }

    @Override
    public WarehouseItem getWarehouseItem(Long id) {
        return warehouseItemRepository.getOne(id);
    }

    @Override
    public List<WarehouseItem> getAllItems() {
        return warehouseItemRepository.findAll();
    }

    @Override
    public List<WarehouseItem> getItemsByType(ItemType itemType) {
        return warehouseItemRepository.findByItemType(itemType);
    }

    @Override
    public Boolean createDelivery(Delivery delivery) {
        try {
            if (checkDelivery(delivery)){
                deliveryRepository.save(delivery);
            } else {
                log.info("Invalid delivery");

                return Boolean.FALSE;
            }
        } catch (Exception ex) {
            log.info("Failed creating delivery");

            return Boolean.FALSE;
        }
        log.info("Created new delivery");

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateDelivery(Delivery delivery) {
        try {
            deliveryRepository.save(delivery);
        } catch (Exception ex) {
            log.info("Failed updating delivery");

            return Boolean.FALSE;
        }
        log.info("Updated delivery");

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteDelivery(Long id) {
        try{
            deliveryRepository.deleteById(id);
        } catch (Exception ex){
            log.info("Failed deleting delivery");
        }
        log.info("Deleted delivery");

        return Boolean.TRUE;
    }

    @Override
    public Delivery getDelivery(Long id) {
        return deliveryRepository.getOne(id);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public List<Delivery> getDeliveriesByStatus(DeliveryStatus deliveryStatus) {
        return deliveryRepository.findByDeliveryStatus(deliveryStatus);
    }

    private Boolean checkDelivery(Delivery delivery) {
        Map<WarehouseItem, Integer> itemsOrdered = delivery.getItemsOrdered();
        WarehouseItem tempItem;

        for (Map.Entry<WarehouseItem, Integer> entry : itemsOrdered.entrySet()){
            tempItem = warehouseItemRepository.getOne(entry.getKey().getId());
            if (tempItem.getQuantity() - entry.getValue() < 0){
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }
}
