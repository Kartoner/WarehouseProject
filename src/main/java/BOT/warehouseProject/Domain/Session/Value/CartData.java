package BOT.warehouseProject.Domain.Session.Value;

import BOT.warehouseProject.Domain.Session.ICart;
import BOT.warehouseProject.Domain.Value.WarehouseItemData;

import java.util.Set;

public class CartData {

    private Set<WarehouseItemData> itemsInCart;

    private Double overallPrice;

    public CartData(ICart cart) {
        this.itemsInCart = cart.getItemsInCart();
        this.overallPrice = cart.getOverallPrice();
    }

    public Set<WarehouseItemData> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(Set<WarehouseItemData> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public Double getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(Double overallPrice) {
        this.overallPrice = overallPrice;
    }

    @Override
    public String toString() {
        return "CartData{" +
                "itemsInCart=" + itemsInCart +
                ", overallPrice=" + overallPrice +
                '}';
    }
}
