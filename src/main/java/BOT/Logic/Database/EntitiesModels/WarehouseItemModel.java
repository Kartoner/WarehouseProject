package BOT.Logic.Database.EntitiesModels;

import BOT.Logic.Enums.ItemType;

public class WarehouseItemModel
{
    private long id;
    private String itemName;
    private ItemType itemType;
    private String itemDescription;
    private long quantity;
    private double price;

    public WarehouseItemModel(long id, String itemName, ItemType itemType, String itemDescription, long quantity, double price) {
        this.id = id;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public long getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
