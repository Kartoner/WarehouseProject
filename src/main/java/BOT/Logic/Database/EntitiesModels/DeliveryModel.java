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


}
