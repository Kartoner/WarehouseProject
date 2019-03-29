package BOT.warehouseProject.Website.Controllers;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Authentication.Values.AuthContainer;
import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Enums.DeliveryStatus;
import BOT.warehouseProject.Domain.Enums.ItemType;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WebController
{

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    private final IUserService userService;
    private final IWarehouseService warehouseService;

    @Autowired
    public WebController(@Qualifier("userService") IUserService userService,
                         @Qualifier("warehouseService") IWarehouseService warehouseService)
    {
        this.userService = userService;
        this.warehouseService = warehouseService;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ResponseEntity<?> authenticate(@RequestBody AuthContainer authContainer){
        Optional<User> authUser = userService.authenticate(authContainer.getUsername(), authContainer.getPassword());

        if(authUser.isPresent()){
            User user = authUser.get();

            log.info("User authenticated");
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            log.error("Invalid username or password");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id")long id)
    {
        Optional<User> user = userService.getUser(id);

        if(user.isEmpty())
        {
            log.error("User with ID = " + id + " not found");
            return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }

        log.info("User with ID = " + id + " found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDelivery(@PathVariable("id")long id)
    {
        Optional<Delivery> delivery = warehouseService.getDelivery(id);

        if(delivery.isEmpty())
        {
            log.error("Delivery with ID = " + id + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        log.info("Delivery with ID = " + id + " found");
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }


    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getWarehouseItem(@PathVariable("id")long id)
    {
        Optional<WarehouseItem> warehouseItem = warehouseService.getWarehouseItem(id);
        if(warehouseItem.isEmpty())
        {
            log.error("Item with ID = " + id + " not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        log.info("Item with ID = " + id + " found");
        return new ResponseEntity<>(warehouseItem, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers()
    {
        List<User> users = userService.getAllUsers();

        if(users.isEmpty())
        {
            log.info("List of users is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        log.info("Retrieved " + users.size() + " users");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDeliveries()
    {
        List<Delivery> deliveries = warehouseService.getAllDeliveries();

        if(deliveries.isEmpty())
        {
            log.info("List of deliveries is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        log.info("Retrieved " + deliveries.size() + " deliveries");
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ResponseEntity<?> getAllItems()
    {
        List<WarehouseItem> items = warehouseService.getAllItems();

        if(items.isEmpty())
        {
            log.info("List of items is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        log.info("Retrieved " + items.size() + " items");
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/status/{status}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsersByStatus(@PathVariable("status")String status)
    {
        UserStatus userStatus = UserStatus.valueOf(status);

        List<User> users = userService.getUsersByStatus(userStatus);

        if(users.isEmpty())
        {
            log.info("List of users with " + userStatus.toString() + " status is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        log.info("Retrieved " + users.size() + " users with " + userStatus.toString() + " status");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/delivery/status/{status}", method = RequestMethod.GET)
    public ResponseEntity<?> getDeliveriesByStatus(@PathVariable("status")String status)
    {
        DeliveryStatus deliveryStatus = DeliveryStatus.valueOf(status);

        List<Delivery> deliveries = warehouseService.getDeliveriesByStatus(deliveryStatus);

        if(deliveries.isEmpty())
        {
            log.info("List of deliveries with " + deliveryStatus.toString() + " status is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        log.info("Retrieved " + deliveries.size() + " deliveries with " + deliveryStatus.toString() + " status");
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemByType(@PathVariable("type")String type)
    {
        ItemType itemType = ItemType.valueOf(type);

        List<WarehouseItem> items = warehouseService.getItemsByType(itemType);

        if(items.isEmpty())
        {
            log.info("List of items of type " + itemType.toString() + " is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        log.info("Retrieved " + items.size() + " items of type " + itemType.toString());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // CREATE
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
    {
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());

        if(existingUser.isPresent()){
            log.error("User already exists");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        Boolean isCreated = userService.createUser(user);

        if(isCreated){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getUserId()).toUri());

            log.info("User created successfully");
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            log.error("User not created");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/delivery/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createDelivery(@RequestBody Delivery delivery, UriComponentsBuilder ucBuilder)
    {
        Delivery populatedDelivery = populateDeliveryWithExisting(delivery);

        Boolean isCreated = warehouseService.createDelivery(populatedDelivery);

        if(isCreated){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/delivery/{id}")
                    .buildAndExpand(populatedDelivery.getDeliveryId())
                    .toUri());

            log.info("Delivery created successfully");
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            log.error("Delivery not created");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/item/", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@RequestBody WarehouseItem item, UriComponentsBuilder ucBuilder)
    {
        Optional<WarehouseItem> existingItem = warehouseService.getItemByName(item.getItemName());

        if(existingItem.isPresent()){
            log.error("Item already exists");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        Boolean isCreated = warehouseService.createWarehouseItem(item);

        if(isCreated){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/item/{id}").buildAndExpand(item.getItemId()).toUri());

            log.info("Item created successfully");
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            log.error("Item not created");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //DELETE
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id )
    {
        Optional<User> user = userService.getUser(id);

        if(user.isEmpty())
        {
            log.error("User with given ID doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Boolean isDeleted = userService.deleteUser(id);

        if(isDeleted){
            log.info("User deleted successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("User not deleted");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDelivery(@PathVariable("id") long id )
    {
        Optional<Delivery> delivery = warehouseService.getDelivery(id);

        if(delivery.isEmpty())
        {
            log.error("Delivery with given ID doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Boolean isDeleted = warehouseService.deleteDelivery(id);

        if(isDeleted){
            log.info("Delivery deleted successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("Delivery not deleted");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@PathVariable("id") long id )
    {
        Optional<WarehouseItem> item = warehouseService.getWarehouseItem(id);

        if(item.isEmpty())
        {
            log.error("Item with given ID doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Boolean isDeleted = warehouseService.deleteWarehouseItem(id);

        if(isDeleted){
            log.info("Item deleted successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("Item not deleted");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user)
    {
        Optional<User> searchedUser = userService.getUser(id);

        if(searchedUser.isEmpty())
        {
            log.error("User with given ID doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User currentUser = searchedUser.get();

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserStatus(user.getUserStatus());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        Boolean isUpdated = userService.updateUser(currentUser);

        if(isUpdated){
            log.info("User updated successfully");
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            log.error("User not updated");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDelivery(@PathVariable("id") long id, @RequestBody Delivery delivery)
    {
        Optional<Delivery> searchedDelivery = warehouseService.getDelivery(id);

        if(searchedDelivery.isEmpty())
        {
            log.error("Delivery with given ID doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Delivery currentDelivery = searchedDelivery.get();

        currentDelivery.setEmployeeAccepting(delivery.getEmployeeAccepting());
        currentDelivery.setCustomerOrdering(delivery.getCustomerOrdering());
        currentDelivery.setDeliveryAddress(delivery.getDeliveryAddress());
        currentDelivery.setDeliveryStatus(delivery.getDeliveryStatus());
        currentDelivery.setItemsOrdered(delivery.getItemsOrdered());
        currentDelivery.setOverallPrice(delivery.getOverallPrice());
        currentDelivery.setPaid(delivery.getPaid());

        Boolean isUpdated = warehouseService.updateDelivery(currentDelivery);

        if(isUpdated){
            log.info("Delivery updated successfully");
            return new ResponseEntity<>(currentDelivery, HttpStatus.OK);
        } else {
            log.error("Delivery not updated");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateItem(@PathVariable("id") long id, @RequestBody WarehouseItem item)
    {
        Optional<WarehouseItem> searchedItem = warehouseService.getWarehouseItem(id);

        if(searchedItem.isEmpty())
        {
            log.error("Item with given ID doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        WarehouseItem currentItem = searchedItem.get();

        currentItem.setItemName(item.getItemName());
        currentItem.setItemType(item.getItemType());
        currentItem.setItemDescription(item.getItemDescription());
        currentItem.setPrice(item.getPrice());

        Boolean isUpdated = warehouseService.updateItemInfo(currentItem);

        if(isUpdated){
            log.info("Item updated successfully");
            return new ResponseEntity<>(currentItem, HttpStatus.OK);
        } else {
            log.error("Item not updated");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Delivery populateDeliveryWithExisting(Delivery delivery){
        User employee = userService.getUserByUsername(delivery.getEmployeeAccepting().getUsername()).get();
        User customer = userService.getUserByUsername(delivery.getCustomerOrdering().getUsername()).get();

        delivery.setEmployeeAccepting(employee);
        delivery.setCustomerOrdering(customer);

        Map<WarehouseItem, Integer> items = new HashMap<>();

        for (Map.Entry<WarehouseItem, Integer> entry : delivery.getItemsOrdered().entrySet()){
            WarehouseItem item = warehouseService.getItemByName(entry.getKey().getItemName()).get();

            items.put(item, entry.getValue());
        }

        delivery.setItemsOrdered(items);

        return delivery;
    }
}
