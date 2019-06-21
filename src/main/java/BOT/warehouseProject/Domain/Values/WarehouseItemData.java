package BOT.warehouseProject.Domain.Values;

import BOT.warehouseProject.Domain.Enums.ItemType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class WarehouseItemData {

    private Long itemDataId;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private Double price;

    private Integer quantity;

    public WarehouseItemData() {
    }

    public WarehouseItemData(Long itemId, String itemName, ItemType itemType, Double price, Integer quantity) {
        this.itemDataId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getItemDataId() {
        return itemDataId;
    }

    public void setItemDataId(Long itemId) {
        this.itemDataId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "WarehouseItemData{" +
                "id=" + itemDataId +
                ", itemName='" + itemName + '\'' +
                ", itemType=" + itemType +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
