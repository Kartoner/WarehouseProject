package BOT.warehouseProject.Domain.Entity;

import BOT.warehouseProject.Authentication.Value.UserData;
import BOT.warehouseProject.Domain.Enum.DeliveryStatus;
import BOT.warehouseProject.Domain.Value.WarehouseItemData;
import BOT.warehouseProject.Infrastructure.Converter.UserDataConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "delivery")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = UserDataConverter.class)
    private UserData employeeAccepting;

    @Convert(converter = UserDataConverter.class)
    private UserData customerOrdering;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "completion_date")
    private String completionDate;

    @ElementCollection(targetClass = WarehouseItemData.class)
    @CollectionTable(name="delivery_item")
    @JoinColumn(name="delivery_id", referencedColumnName = "id")
    private Set<WarehouseItemData> itemsOrdered;

    @Column(name = "overall_price", nullable = false)
    @Digits(integer = 10, fraction = 2)
    private Double overallPrice;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    public Delivery() {
    }

    public Delivery(UserData employeeAccepting,
                    UserData customerOrdering,
                    String deliveryAddress,
                    DeliveryStatus deliveryStatus,
                    String orderDate,
                    String completionDate,
                    Set<WarehouseItemData> itemsOrdered,
                    Double overallPrice,
                    Boolean isPaid) {
        this.employeeAccepting = employeeAccepting;
        this.customerOrdering = customerOrdering;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = orderDate;
        this.completionDate = completionDate;
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

    public UserData getEmployeeAccepting() {
        return employeeAccepting;
    }

    public void setEmployeeAccepting(UserData employeeAccepting) {
        this.employeeAccepting = employeeAccepting;
    }

    public UserData getCustomerOrdering() {
        return customerOrdering;
    }

    public void setCustomerOrdering(UserData customerOrdering) {
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

    public String getOrderDate() { return orderDate; }

    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getCompletionDate() { return completionDate; }

    public void setCompletionDate(String completionDate) { this.completionDate = completionDate; }

    public Set<WarehouseItemData> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(Set<WarehouseItemData> itemsOrdered) {
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
