package BOT.Logic.Database.EntitiesModels;

import BOT.Logic.Enums.DeliveryStatus;

public class DeliveryModel
{
    private long id;
    private long employeeAcceptingID;
    private long customerOrderingID;
    private String deliveryAddress;
    private DeliveryStatus deliveryStatus;
    private Double overallPrice;

    public DeliveryModel(long id, long employeeAcceptingID, long customerOrderingID, String deliveryAddress, DeliveryStatus deliveryStatus, Double overallPrice) {
        this.id = id;
        this.employeeAcceptingID = employeeAcceptingID;
        this.customerOrderingID = customerOrderingID;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.overallPrice = overallPrice;
    }

    public long getId() {
        return id;
    }

    public long getEmployeeAcceptingID() {
        return employeeAcceptingID;
    }

    public long getCustomerOrderingID() {
        return customerOrderingID;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public Double getOverallPrice() {
        return overallPrice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmployeeAcceptingID(long employeeAcceptingID) {
        this.employeeAcceptingID = employeeAcceptingID;
    }

    public void setCustomerOrderingID(long customerOrderingID) {
        this.customerOrderingID = customerOrderingID;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setOverallPrice(Double overallPrice) {
        this.overallPrice = overallPrice;
    }
}
