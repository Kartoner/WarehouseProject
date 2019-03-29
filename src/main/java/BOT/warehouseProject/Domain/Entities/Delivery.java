package BOT.warehouseProject.Domain.Entities;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Serialization.UserDeserializer;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Serialization.WarehouseItemDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "delivery")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonDeserialize(keyUsing = UserDeserializer.class)
    private User employeeAccepting;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonDeserialize(keyUsing = UserDeserializer.class)
    private User customerOrdering;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;

    @ElementCollection
    @CollectionTable(name="delivery_item",
            joinColumns={@JoinColumn(name="delivery_id")})
    @MapKeyJoinColumn(name="warehouse_item_id")
    @JsonDeserialize(keyUsing = WarehouseItemDeserializer.class)
    private Map<WarehouseItem, Integer> itemsOrdered;

    @Column(name = "overall_price", nullable = false)
    private Double overallPrice;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    public Delivery() {
    }

    public Delivery(User employeeAccepting,
                    User customerOrdering,
                    String deliveryAddress,
                    DeliveryStatus deliveryStatus,
                    Map<WarehouseItem, Integer> itemsOrdered,
                    Double overallPrice,
                    Boolean isPaid) {
        this.employeeAccepting = employeeAccepting;
        this.customerOrdering = customerOrdering;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.itemsOrdered = itemsOrdered;
        this.overallPrice = overallPrice;
        this.isPaid = isPaid;
    }

    public Long getDeliveryId() {
        return id;
    }

    public void setDeliveryId(Long id) {
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
