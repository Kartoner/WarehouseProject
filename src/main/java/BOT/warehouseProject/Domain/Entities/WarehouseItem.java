package BOT.warehouseProject.Domain.Entities;

import BOT.warehouseProject.Domain.Enums.ItemType;

import javax.persistence.*;

@Entity
@Table(name = "warehouse_item")
public class WarehouseItem {

    @Id
    @GeneratedValue
    private Long id;

    private String itemName;
    private ItemType itemType;
    private String itemDescription;
    private Integer quantity;
    private Double price;

    protected WarehouseItem() {
    }

    public WarehouseItem(String itemName,
                         ItemType itemType,
                         String itemDescription,
                         Double price) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.quantity = 0;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "WarehouseItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemType=" + itemType +
                ", itemDescription='" + itemDescription + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
