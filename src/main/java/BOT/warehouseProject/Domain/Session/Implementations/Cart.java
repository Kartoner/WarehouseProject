package BOT.warehouseProject.Domain.Session.Implementations;

import BOT.warehouseProject.Domain.Session.ICart;
import BOT.warehouseProject.Domain.Values.WarehouseItemData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component(value = "cart")
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements ICart {

    private static final Logger log = LoggerFactory.getLogger(Cart.class);

    private Set<WarehouseItemData> itemsInCart = new LinkedHashSet<WarehouseItemData>();

    public Cart() {
    }

    @Override
    public Set<WarehouseItemData> getItemsInCart() {
        return itemsInCart;
    }

    @Override
    public void setItemsInCart(Set<WarehouseItemData> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    @Override
    public Boolean addToCart(WarehouseItemData item){
        try{
            this.itemsInCart.add(item);
        } catch (Exception ex){
            log.info("Item not added");

            return Boolean.FALSE;
        }

        log.info("Item added");

        return Boolean.TRUE;
    }

    @Override
    public Boolean resetCart(){
        try{
            this.itemsInCart.clear();
        } catch (Exception ex){
            log.info("Failed to reset the cart");

            return Boolean.FALSE;
        }

        log.info("Cart cleared");

        return Boolean.TRUE;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "itemsInCart=" + itemsInCart +
                '}';
    }
}
