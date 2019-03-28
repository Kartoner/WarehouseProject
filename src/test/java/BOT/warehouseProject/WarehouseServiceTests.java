package BOT.warehouseProject;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Database.Repositories.WarehouseItemRepository;
import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Enums.ItemType;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import BOT.warehouseProject.Domain.Services.Implementations.WarehouseServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseServiceTests
{
    @TestConfiguration
    static class WarehouseServiceTestsConfiguration{
        @Bean
        IWarehouseService warehouseServiceTest()
        {
            ///nie do końca wiem jak tutaj wstrzyknąc te obiekty
            return new WarehouseServiceImplementation(null, null);
        }
    }

    @MockBean
    private WarehouseItemRepository warehouseItemRepositoryMock;

    @Autowired
    @Qualifier("warehouseServiceTest")
    private IWarehouseService warehouseService;


    @Test
    public void givenItemService_WhenGettingAllItems_ThenCheckItemListSize()
    {
        when(warehouseItemRepositoryMock.findAll()).thenReturn(this.givenAllWarehouseItem());
        List<WarehouseItem> items = warehouseItemRepositoryMock.findAll();

        assert(items.size() == 7);
    }


    @Test
    public void givenItemId_WhenGetItem_thenReturnItem()
    {
        ///Given
        Long testId = 0L;
        WarehouseItem testItem= this.cleaningItem();

        ///When
        when(warehouseItemRepositoryMock.getOne(argThat(aLong -> aLong.equals(testId))))
                .thenAnswer((Answer)invocation ->testItem);

        WarehouseItem item = this.warehouseService.getWarehouseItem(testId);

        ///Then
        assert(testItem.getItemName().equals(item.getItemName()));
    }

    @Test
    public void givenItemType_WhenGetListOfItemWithType_ThenContainsItem()
    {
        ItemType testType = ItemType.Cleaning;
        WarehouseItem testItem= this.cleaningItem();

        ///When
        when(warehouseItemRepositoryMock.findByItemType(argThat(aItemType -> aItemType == testType)))
                .thenAnswer((Answer)invocation ->testItem);

        List<WarehouseItem> items = this.warehouseService.getItemsByType(testType);

        ///Then
        assert(items.contains(testItem));
    }

    @Test
    public void givenOrder_WhenGetListOfOrder_ThenContainsOrder()
    {
        ItemType testType = ItemType.Cleaning;
        WarehouseItem testItem= this.cleaningItem();

        ///When
        when(warehouseItemRepositoryMock.findByItemType(argThat(aItemType -> aItemType == testType)))
                .thenAnswer((Answer)invocation ->testItem);

        List<WarehouseItem> items = this.warehouseService.getItemsByType(testType);

        ///Then
        assert(items.contains(testItem));
    }


    @Test
    public void givenItemList_WhenItem_ItemListWithdecrementedCount()
    {
        ///Given

        when(warehouseItemRepositoryMock.findAll()).thenReturn(this.givenAllWarehouseItem());

        List<WarehouseItem> allItems = warehouseItemRepositoryMock.findAll();
        Integer itemListBeforeDeleting = warehouseItemRepositoryMock.findAll().size();

        Long itemIndex = allItems.get(0).getId();

        ///When
        warehouseItemRepositoryMock.deleteById(itemIndex);
        ///Then

        Integer itemListAfterDelete = warehouseItemRepositoryMock.findAll().size();

        assert (itemListAfterDelete.intValue() == itemListBeforeDeleting.intValue());

    }

    private List<WarehouseItem> givenAllWarehouseItem(){
        List <WarehouseItem> itemsList = new ArrayList<>();

        WarehouseItem item1 = cleaningItem();
        WarehouseItem item2 = clotheItem();
        WarehouseItem item3 = electronicItem();
        WarehouseItem item4 = foodItem();
        WarehouseItem item5 = furinitureItem();
        WarehouseItem item6 = gardeningItem();
        WarehouseItem item7 = sportItem();

        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        itemsList.add(item4);
        itemsList.add(item5);
        itemsList.add(item6);
        itemsList.add(item7);

        return itemsList;
    }

    private WarehouseItem sportItem()
    {
        String itemName = "Sport stuff";
        ItemType itemType = ItemType.Sport;
        String itemDescription = "item  password";
        Double itemPrice = 0.77;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }

    private WarehouseItem gardeningItem()
    {
        String itemName = "Gardening stuff";
        ItemType itemType = ItemType.Gardening;
        String itemDescription = "item  password";
        Double itemPrice = 6.66;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }
    private WarehouseItem furinitureItem()
    {
        String itemName = "Furniture";
        ItemType itemType = ItemType.Furniture;
        String itemDescription = "item  password";
        Double itemPrice = 555.55;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }


    private WarehouseItem foodItem()
    {
        String itemName = "food";
        ItemType itemType = ItemType.Food;
        String itemDescription = "item  password";
        Double itemPrice = 44.44;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }


    private WarehouseItem electronicItem(){
        String itemName = "electronic stuff";
        ItemType itemType = ItemType.Electronics;
        String itemDescription = "item  password";
        Double itemPrice = 133.13;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }

    private WarehouseItem clotheItem(){
        String itemName = "clothes";
        ItemType itemType = ItemType.Clothes;
        String itemDescription = "item  password";
        Double itemPrice = 122.12;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }

    private WarehouseItem cleaningItem(){
        String itemName = "cleaning stuff";
        ItemType itemType = ItemType.Cleaning;
        String itemDescription = "item  password";
        Double itemPrice = 111.11;

        return new WarehouseItem(itemName,itemType,itemDescription,itemPrice);
    }

    private Delivery del1()
    {
        UserServiceTests serviceTests = new UserServiceTests();


        Delivery d1 = new Delivery(serviceTests.givenEmployeeUser(),
                serviceTests.givenCustomerUser(),
                "address",
                DeliveryStatus.Accepted,this.orderedProduct1(),
                this.countOveralPriceFor(null));
        return d1;
    }

    private Map<WarehouseItem,Integer> orderedProduct1()
    {
        Map<WarehouseItem,Integer> amp = new HashMap<>();
        amp.put(this.cleaningItem(),12);
        amp.put(this.clotheItem(),6);
        return amp;
    }

    private Double countOveralPriceFor(Map<WarehouseItem,Integer> amp)
    {
        return 10.0;
    }



}
