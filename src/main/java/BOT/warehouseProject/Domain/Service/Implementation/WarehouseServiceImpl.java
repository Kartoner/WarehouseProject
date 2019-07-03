package BOT.warehouseProject.Domain.Service.Implementation;

import BOT.warehouseProject.Database.Repository.DeliveryRepository;
import BOT.warehouseProject.Database.Repository.WarehouseItemRepository;
import BOT.warehouseProject.Domain.Entity.Delivery;
import BOT.warehouseProject.Domain.Entity.WarehouseItem;
import BOT.warehouseProject.Domain.Enum.DeliveryStatus;
import BOT.warehouseProject.Domain.Enum.ItemType;
import BOT.warehouseProject.Domain.Service.IWarehouseService;
import BOT.warehouseProject.Domain.Value.WarehouseItemData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service(value = "warehouseService")
public class WarehouseServiceImpl implements IWarehouseService {

    private static final Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final WarehouseItemRepository warehouseItemRepository;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseItemRepository warehouseItemRepository,
                                DeliveryRepository deliveryRepository)
    {
        this.warehouseItemRepository = warehouseItemRepository;
        this.deliveryRepository = deliveryRepository;
    }

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
    public Boolean updateItemInfo(WarehouseItem warehouseItem) {
        try {
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

            return Boolean.FALSE;
        }
        log.info("Deleted item");

        return Boolean.TRUE;
    }

    @Override
    public Optional<WarehouseItem> getWarehouseItem(Long id) {
        return warehouseItemRepository.findById(id);
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
    public Optional<WarehouseItem> getItemByName(String itemName){
        return warehouseItemRepository.findByItemName(itemName);
    }

    @Override
    public Boolean createDelivery(Delivery delivery) {
        try {
            if (checkDelivery(delivery)){
                deliveryRepository.save(delivery);
                Set <WarehouseItemData> itemsOrdered = delivery.getItemsOrdered();

                for (WarehouseItemData item : itemsOrdered){
                    Optional<WarehouseItem> warehouseItem = warehouseItemRepository.findById(item.getItemDataId());

                    this.updateStock(warehouseItem.get().getItemId(),
                            warehouseItem.get().getQuantity() - item.getQuantity());
                }
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

            return Boolean.FALSE;
        }
        log.info("Deleted delivery");

        return Boolean.TRUE;
    }

    @Override
    public Optional<Delivery> getDelivery(Long id) {
        return deliveryRepository.findById(id);
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
        Set <WarehouseItemData> itemsOrdered = delivery.getItemsOrdered();

        if (itemsOrdered.isEmpty()){
            return Boolean.FALSE;
        }

        Optional<WarehouseItem> tempItem;

        for (WarehouseItemData item : itemsOrdered){
            tempItem = warehouseItemRepository.findByItemName(item.getItemName());
            if (tempItem.get().getQuantity() - item.getQuantity() < 0){
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private Boolean updateStock(Long id, Integer quantity) {
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
}
