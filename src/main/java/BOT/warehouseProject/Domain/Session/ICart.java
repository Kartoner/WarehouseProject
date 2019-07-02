package BOT.warehouseProject.Domain.Session;

import BOT.warehouseProject.Domain.Value.WarehouseItemData;

import java.util.Set;

public interface ICart {

    public Set<WarehouseItemData> getItemsInCart();

    public void setItemsInCart(Set<WarehouseItemData> itemsInCart);

    public Double getOverallPrice();

    public void setOverallPrice(Double overallPrice);

    public Boolean addToCart(WarehouseItemData item);

    public Boolean removeFromCart(WarehouseItemData item);

    public Boolean resetCart();
}
