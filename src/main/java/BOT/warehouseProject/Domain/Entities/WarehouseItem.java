package BOT.warehouseProject.Domain.Entities;

import BOT.warehouseProject.Domain.Enums.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "warehouse_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WarehouseItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", unique = true, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType itemType;

    @Column(name = "item_description")
    private String itemDescription;

    @ColumnDefault("0")
    private Integer quantity;

    @ColumnDefault("0.0")
    private Double price;

    public WarehouseItem() {
    }

    public WarehouseItem(String itemName,
                         ItemType itemType,
                         String itemDescription,
                         Integer quantity,
                         Double price) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getItemId() {
        return id;
    }

    public void setItemId(Long id) {
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
