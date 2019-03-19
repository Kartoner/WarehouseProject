package BOT.warehouseProject.Domain.Entities;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User employeeAccepting;

    @ManyToOne(cascade = CascadeType.ALL)
    private User customerOrdering;

    private String deliveryAddress;
    private DeliveryStatus deliveryStatus;

    @ElementCollection
    @CollectionTable(name="delivery_item",
            joinColumns={@JoinColumn(name="delivery_id")})
    @MapKeyJoinColumn(name="warehouse_item_id")
    private Map<WarehouseItem, Integer> itemsOrdered;

    private Double overallPrice;
    private Boolean isPaid;

    protected Delivery() {
    }

    public Delivery(User employeeAccepting,
                    User customerOrdering,
                    String deliveryAddress,
                    DeliveryStatus deliveryStatus,
                    Map<WarehouseItem, Integer> itemsOrdered,
                    Double overallPrice) {
        this.employeeAccepting = employeeAccepting;
        this.customerOrdering = customerOrdering;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.itemsOrdered = itemsOrdered;
        this.overallPrice = overallPrice;
        this.isPaid = Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployeeAccepting() {
        return employeeAccepting;
    }

    public void setEmployeeAccepting(User employeeAccepting) {
        this.employeeAccepting = employeeAccepting;
    }

    public User getCustomerOrdering() {
        return customerOrdering;
    }

    public void setCustomerOrdering(User customerOrdering) {
        this.customerOrdering = customerOrdering;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Map<WarehouseItem, Integer> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(Map<WarehouseItem, Integer> itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public Double getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(Double overallPrice) {
        this.overallPrice = overallPrice;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", employeeAccepting=" + employeeAccepting +
                ", customerOrdering=" + customerOrdering +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", itemsOrdered=" + itemsOrdered +
                ", overallPrice=" + overallPrice +
                ", isPaid=" + isPaid +
                '}';
    }
}
