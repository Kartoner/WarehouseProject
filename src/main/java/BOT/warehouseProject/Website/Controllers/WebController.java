package BOT.warehouseProject.Website.Controllers;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Enums.ItemType;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class WebController
{

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    private final IUserService userService;
    private final IWarehouseService warehouseService;

    @Autowired
    public WebController(IUserService userService, IWarehouseService warehouseService)
    {
        this.userService = userService;
        this.warehouseService = warehouseService;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id")long id)
    {
        User user = userService.getUser(id);
        if(user == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.GET)
    public ResponseEntity<Delivery> getDelivery(@PathVariable("id")long id)
    {
        Delivery delivery = warehouseService.getDelivery(id);
        if(delivery == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }


    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity<WarehouseItem> getWarehouseItem(@PathVariable("id")long id)
    {
        WarehouseItem warehouseItem = warehouseService.getWarehouseItem(id);
        if(warehouseItem == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(warehouseItem, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users = userService.getAllUsers();
        if(users.isEmpty())
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    public ResponseEntity<List<Delivery>> getAllDeliveries()
    {
        List<Delivery> deliveries = warehouseService.getAllDeliveries();
        if(deliveries.isEmpty())
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ResponseEntity<List<WarehouseItem>> getAllItems()
    {
        List<WarehouseItem> items = warehouseService.getAllItems();
        if(items.isEmpty())
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<UserStatus>> getUsersByStatus(@PathVariable("userStatus")UserStatus status)
    {
        List<User> users = userService.getUsersByStatus(status);
        if(users.isEmpty())
        {
            ///todo dorobic loggera
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<DeliveryStatus>> getDeliveriesByStatus(@PathVariable("deliveryStatus")DeliveryStatus status)
    {
        List<Delivery> deliveries = warehouseService.getDeliveriesByStatus(status);
        if(deliveries.isEmpty())
        {
            ///todo dorobic loggera
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<ItemType>> getItemByType(@PathVariable("itemType")ItemType status)
    {
        List<WarehouseItem> items = warehouseService.getItemsByType(status);
        if(items.isEmpty())
        {
            ///todo dorobic loggera
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // CREATE
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
    {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delivery/", method = RequestMethod.POST)
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery, UriComponentsBuilder ucBuilder)
    {
        warehouseService.createDelivery(delivery);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/item/", method = RequestMethod.POST)
    public ResponseEntity<WarehouseItem> createItem(@RequestBody WarehouseItem item, UriComponentsBuilder ucBuilder)
    {
        warehouseService.createWarehouseItem(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //DELETE
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id )
    {
        User user = userService.getUser(id);
        if(user == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Delivery> deleteDelivery(@PathVariable("id") long id )
    {
        Delivery delivery = warehouseService.getDelivery(id);
        if(delivery == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        warehouseService.deleteDelivery(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<WarehouseItem> deleteItem(@PathVariable("id") long id )
    {
        WarehouseItem item = warehouseService.getWarehouseItem(id);
        if(item == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        warehouseService.deleteWarehouseItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //UPDATE
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user)
    {
        User currentUser = userService.getUser(id);
        if(currentUser == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserStatus(user.getUserStatus());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        userService.updateUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") long id, @RequestBody Delivery delivery)
    {
        Delivery currentDelivery = warehouseService.getDelivery(id);
        if(currentDelivery == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentDelivery.setEmployeeAccepting(delivery.getEmployeeAccepting());
        currentDelivery.setCustomerOrdering(delivery.getCustomerOrdering());
        currentDelivery.setDeliveryAddress(delivery.getDeliveryAddress());
        currentDelivery.setDeliveryStatus(delivery.getDeliveryStatus());
        currentDelivery.setItemsOrdered(delivery.getItemsOrdered());
        currentDelivery.setOverallPrice(delivery.getOverallPrice());

        warehouseService.updateDelivery(currentDelivery);
        return new ResponseEntity<>(currentDelivery, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WarehouseItem> updateItem(@PathVariable("id") long id, @RequestBody WarehouseItem item)
    {
        WarehouseItem currentItem = warehouseService.getWarehouseItem(id);
        if(currentItem == null)
        {
            ///todo dorobić loggera
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentItem.setItemName(item.getItemName());
        currentItem.setItemType(item.getItemType());
        currentItem.setItemDescription(item.getItemDescription());
        currentItem.setPrice(item.getPrice());

        warehouseService.updateItemInfo(currentItem);
        return new ResponseEntity<>(currentItem, HttpStatus.OK);
    }
}
