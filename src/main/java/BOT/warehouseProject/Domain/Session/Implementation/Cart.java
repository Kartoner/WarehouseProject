package BOT.warehouseProject.Domain.Session.Implementation;

import BOT.warehouseProject.Domain.Session.ICart;
import BOT.warehouseProject.Domain.Value.WarehouseItemData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import java.util.LinkedHashSet;
import java.util.Set;

@Component(value = "cart")
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements ICart {

    private static final Logger log = LoggerFactory.getLogger(Cart.class);

    private Set<WarehouseItemData> itemsInCart = new LinkedHashSet<WarehouseItemData>();

    @Digits(integer = 10, fraction = 2)
    private Double overallPrice = 0.0d;

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
    public Double getOverallPrice(){ return overallPrice; }

    @Override
    public void setOverallPrice(Double overallPrice){ this.overallPrice = overallPrice; }

    @Override
    public Boolean addToCart(WarehouseItemData item){
        try{
            this.itemsInCart.add(item);
            this.overallPrice += item.getPrice() * item.getQuantity();
        } catch (Exception ex){
            log.info("Item not added");

            return Boolean.FALSE;
        }

        log.info("Item added");

        return Boolean.TRUE;
    }

    @Override
    public Boolean removeFromCart(WarehouseItemData item){
        try{
            this.itemsInCart.remove(item);
            this.overallPrice -= item.getPrice() * item.getQuantity();
        } catch (Exception ex) {
            log.info("Item not removed");

            return Boolean.FALSE;
        }

        log.info("Item removed");

        return Boolean.TRUE;
    }

    @Override
    public Boolean resetCart(){
        try{
            this.itemsInCart.clear();
            this.overallPrice = 0.0d;
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
                ", overallPrice=" + overallPrice +
                '}';
    }
}
