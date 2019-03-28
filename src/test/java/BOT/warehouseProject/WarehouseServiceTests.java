package BOT.warehouseProject;

import BOT.warehouseProject.Database.Repositories.WarehouseItemRepository;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
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
import java.util.List;

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
    public void givenUserService_WhenGettingAllUsers_ThenCheckUserListSize()
    {
        when(warehouseItemRepositoryMock.findAll()).thenReturn(this.givenAllWarehouseItem());
        List<WarehouseItem> users = warehouseItemRepositoryMock.findAll();

        assert(users.size() == 7);
    }


    @Test
    public void givenUserId_WhenGetItem_thenReturnItem()
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
        List <WarehouseItem> usersList = new ArrayList<>();

        WarehouseItem item1 = cleaningItem();
        WarehouseItem item2 = clotheItem();
        WarehouseItem item3 = electronicItem();
        WarehouseItem item4 = foodItem();
        WarehouseItem item5 = furinitureItem();
        WarehouseItem item6 = gardeningItem();
        WarehouseItem item7 = sportItem();

        usersList.add(item1);
        usersList.add(item2);
        usersList.add(item3);
        usersList.add(item4);
        usersList.add(item5);
        usersList.add(item6);
        usersList.add(item7);

        return usersList;
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
}
