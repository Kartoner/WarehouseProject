package BOT.warehouseProject.Domain.Session;

import BOT.warehouseProject.Domain.Values.WarehouseItemData;

import java.util.Set;

public interface ICart {

    public Set<WarehouseItemData> getItemsInCart();

    public void setItemsInCart(Set<WarehouseItemData> itemsInCart);

    public Boolean addToCart(WarehouseItemData item);

    public Boolean resetCart();
}
