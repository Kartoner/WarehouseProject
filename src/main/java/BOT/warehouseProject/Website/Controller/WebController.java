package BOT.warehouseProject.Website.Controller;

import BOT.warehouseProject.Authentication.Entity.User;
import BOT.warehouseProject.Authentication.Service.IUserService;
import BOT.warehouseProject.Authentication.Value.AuthContainer;
import BOT.warehouseProject.Authentication.Value.UserData;
import BOT.warehouseProject.Domain.Entity.Delivery;
import BOT.warehouseProject.Domain.Entity.WarehouseItem;
import BOT.warehouseProject.Domain.Enum.DeliveryStatus;
import BOT.warehouseProject.Domain.Enum.ItemType;
import BOT.warehouseProject.Domain.Service.IWarehouseService;
import BOT.warehouseProject.Domain.Session.ICart;
import BOT.warehouseProject.Domain.Session.Value.CartData;
import BOT.warehouseProject.Domain.Value.WarehouseItemData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class WebController
{

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    private final IUserService userService;
    private final IWarehouseService warehouseService;
    private final ICart cart;

    @Autowired
    public WebController(@Qualifier("userService") IUserService userService,
                         @Qualifier("warehouseService") IWarehouseService warehouseService,
                         @Qualifier("cart") ICart cart)
    {
        this.userService = userService;
        this.warehouseService = warehouseService;
        this.cart = cart;
    }

    @GetMapping({"/", "/index"})
    public ModelAndView index(){
        return new ModelAndView("index");
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

    @RequestMapping(value = "/cart/{id}/{quantity}", method = RequestMethod.GET)
    public ResponseEntity<?> addToCart(@PathVariable("id")long id,
                                       @PathVariable("quantity")int quantity)
    {
        Optional<WarehouseItem> warehouseItem = warehouseService.getWarehouseItem(id);

        if(warehouseItem.isEmpty())
        {
            log.error("Item with ID = " + id + " not found");
            return new ResponseEntity(warehouseItem, HttpStatus.NOT_FOUND);
        }

        WarehouseItemData warehouseItemData = new WarehouseItemData(warehouseItem.get().getItemId(),
                                                                    warehouseItem.get().getItemName(),
                                                                    warehouseItem.get().getItemType(),
                                                                    warehouseItem.get().getPrice(),
                                                                    quantity);

        cart.addToCart(warehouseItemData);

        return new ResponseEntity<>(warehouseItemData, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    public ModelAndView removeFromCart(@PathVariable("id")long id){

        WarehouseItemData itemData = new WarehouseItemData();

        for(WarehouseItemData item : cart.getItemsInCart()){
            if(item.getItemDataId() == id){
                itemData = item;
            }
        }

        Boolean isDeleted = cart.removeFromCart(itemData);

        if(!isDeleted){
            log.error("Failed removing item from cart");
            return  new ModelAndView("404");
        }
        log.info("Item removed from cart");
        return getCart();
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView getCart(){

        CartData cartData = new CartData(cart);
        Set<WarehouseItemData> itemsInCart = cartData.getItemsInCart();

        if(itemsInCart.isEmpty())
        {
            log.info("Cart is empty");
        }

        ModelAndView cartView = new ModelAndView("/views/cart");
        cartView.addObject("items", itemsInCart);
        cartView.addObject("overallPrice", cartData.getOverallPrice());

        log.info("Retrieved " + itemsInCart.size() + " items in cart");
        return cartView;
    }

    @RequestMapping(value = "/user/delivery", method = RequestMethod.GET)
    public ModelAndView getDeliveriesForCurrentUser(){

        List<Delivery> deliveries = warehouseService.getDeliveriesForUser(1L); //TODO zmienić na id zalogowanego użytkownika

        if(deliveries.isEmpty())
        {
            log.info("List of deliveries for user with ID = " + 1L + " is empty"); //TODO j.w.
        }

        ModelAndView userDeliveriesView = new ModelAndView("/lists/userDeliveryList");
        userDeliveriesView.addObject("deliveries", deliveries);

        log.info("Retrieved " + deliveries.size() + " deliveries for user with ID = " + 1L); //TODO j.w.
        return userDeliveriesView;
    }

    @RequestMapping(value = "/user/details", method = RequestMethod.GET)
    public ModelAndView getCurrentUserDetails(){
        Optional<User> currentUser = userService.getUser(1L); //TODO zmienić na id zalogowanego użytkownika

        ModelAndView userView = new ModelAndView("/views/profile");
        userView.addObject("user", currentUser.get());

        return userView;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id")long id)
    {
        Optional<User> user = userService.getUser(id);

        if(user.isEmpty())
        {
            log.error("User with ID = " + id + " not found");
            return new ModelAndView("404");
        }

        ModelAndView userView = new ModelAndView("/views/user");
        userView.addObject("user", user.get());

        log.info("User with ID = " + id + " found");
        return userView;
    }

    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.GET)
    public ModelAndView getDelivery(@PathVariable("id")long id)
    {
        Optional<Delivery> delivery = warehouseService.getDelivery(id);

        if(delivery.isEmpty())
        {
            log.error("Delivery with ID = " + id + " not found");
            return new ModelAndView("404");
        }

        ModelAndView deliveryView = new ModelAndView("/views/delivery");
        deliveryView.addObject("delivery", delivery);

        log.info("Delivery with ID = " + id + " found");
        return deliveryView;
    }


    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public ModelAndView getWarehouseItem(@PathVariable("id")long id)
    {
        Optional<WarehouseItem> warehouseItem = warehouseService.getWarehouseItem(id);

        if(warehouseItem.isEmpty())
        {
            log.error("Item with ID = " + id + " not found");
            return new ModelAndView("404");
        }

        ModelAndView itemView = new ModelAndView("/views/item");
        itemView.addObject("item", warehouseItem);

        log.info("Item with ID = " + id + " found");
        return itemView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getAllUsers()
    {
        List<User> users = userService.getAllUsers();
        List<String> roles = userService.getRolesList();

        if(users.isEmpty())
        {
            log.info("List of users is empty");
        }

        ModelAndView userListView = new ModelAndView("/lists/userList");
        userListView.addObject("users", users);
        userListView.addObject("roles", roles);

        log.info("Retrieved " + users.size() + " users");
        return userListView;
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    public ModelAndView getAllDeliveries()
    {
        List<Delivery> deliveries = warehouseService.getAllDeliveries();

        if(deliveries.isEmpty())
        {
            log.info("List of deliveries is empty");
        }

        ModelAndView deliveryListView = new ModelAndView("/lists/deliveryList");
        deliveryListView.addObject("deliveries", deliveries);
        deliveryListView.addObject("deliveryStatuses", DeliveryStatus.values());

        log.info("Retrieved " + deliveries.size() + " deliveries");
        return deliveryListView;
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ModelAndView getAllItems()
    {
        List<WarehouseItem> items = warehouseService.getAllItems();

        if(items.isEmpty())
        {
            log.info("List of items is empty");
        }

        ModelAndView itemListView = new ModelAndView("/lists/itemList");
        itemListView.addObject("items", items);
        itemListView.addObject("itemTypes", ItemType.values());

        log.info("Retrieved " + items.size() + " items");
        return itemListView;
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.GET)
    public ModelAndView getUsersByRole(@RequestParam("role")String role)
    {
        List<User> users = userService.getUsersByRole(role);
        List<String> roles = userService.getRolesList();

        if(users.isEmpty())
        {
            log.info("List of users with " + role + " status is empty");
        }

        ModelAndView userListView = new ModelAndView("/lists/userList");
        userListView.addObject("users", users);
        userListView.addObject("roles", roles);

        log.info("Retrieved " + users.size() + " users with " + role + " status");
        return userListView;
    }

    @RequestMapping(value = "/delivery/status", method = RequestMethod.GET)
    public ModelAndView getDeliveriesByStatus(@RequestParam("status")String status)
    {
        DeliveryStatus deliveryStatus = DeliveryStatus.valueOf(status);

        List<Delivery> deliveries = warehouseService.getDeliveriesByStatus(deliveryStatus);

        if(deliveries.isEmpty())
        {
            log.info("List of deliveries with " + deliveryStatus.toString() + " status is empty");
        }
        ModelAndView deliveryListView = new ModelAndView("/lists/deliveryList");
        deliveryListView.addObject("deliveries", deliveries);
        deliveryListView.addObject("deliveryStatuses", DeliveryStatus.values());

        log.info("Retrieved " + deliveries.size() + " deliveries with " + deliveryStatus.toString() + " status");
        return deliveryListView;
    }

    @RequestMapping(value = "/item/type", method = RequestMethod.GET)
    public ModelAndView getItemByType(@RequestParam("type")String type)
    {
        ItemType itemType = ItemType.valueOf(type);

        List<WarehouseItem> items = warehouseService.getItemsByType(itemType);

        if(items.isEmpty())
        {
            log.info("List of items of type " + itemType.toString() + " is empty");
        }

        ModelAndView itemListView = new ModelAndView("/lists/itemList");
        itemListView.addObject("items", items);
        itemListView.addObject("itemTypes", ItemType.values());

        log.info("Retrieved " + items.size() + " items of type " + itemType.toString());
        return itemListView;
    }

    // CREATE
    @RequestMapping(value = "/userAdd", method = RequestMethod.GET)
    public ModelAndView getUserForm(){
        return new ModelAndView("/forms/userAdd");
    }

    @RequestMapping(value = "/userSave", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestBody User user)
    {
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());

        if(existingUser.isPresent()){
            log.error("User already exists");
            return new ModelAndView("404");
        }

        Boolean isCreated = userService.createUser(user);

        if(isCreated){

            Optional<User> createdUser = userService.getUserByUsername(user.getUsername());
            ModelAndView userView = new ModelAndView("/views/user");
            userView.addObject("user", createdUser);

            log.info("User created successfully");
            return userView;
        } else {
            log.error("User not created");
            return new ModelAndView("404");
        }

    }

    @RequestMapping(value = "/deliveryAdd", method = RequestMethod.GET)
    public ModelAndView getDeliveryForm(){
        return new ModelAndView("/forms/deliveryAdd");
    }

    @RequestMapping(value = "/deliverySave", method = RequestMethod.POST)
    public ModelAndView createDelivery(@RequestBody Delivery delivery)
    {
        Optional<User> customerOrdering = userService.getUser(1L); //TODO zmienić na zalogowanego usera
        UserData customerData = new UserData(customerOrdering.get().getUserId(),
                customerOrdering.get().getFullname(),
                customerOrdering.get().getUserRole().getName());

        delivery.setCustomerOrdering(customerData);
        delivery.setItemsOrdered(cart.getItemsInCart());
        delivery.setOverallPrice(cart.getOverallPrice());
        delivery.setPaid(Boolean.FALSE);
        delivery.setDeliveryStatus(DeliveryStatus.Accepted);

        Boolean isCreated = warehouseService.createDelivery(delivery);

        if(isCreated){

            List<Delivery> deliveries = warehouseService.getDeliveriesForUser(customerData.getUserId());
            ModelAndView userDeliveryView = new ModelAndView("/lists/userDeliveryList");
            userDeliveryView.addObject("deliveries", deliveries);

            log.info("Delivery created successfully");
            cart.resetCart();
            return userDeliveryView;
        } else {
            log.error("Delivery not created");
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/itemAdd", method = RequestMethod.GET)
    public ModelAndView getItemForm(){
        return new ModelAndView("/forms/itemAdd");
    }

    @RequestMapping(value = "/itemSave", method = RequestMethod.POST)
    public ModelAndView createItem(@RequestBody WarehouseItem item)
    {
        Optional<WarehouseItem> existingItem = warehouseService.getItemByName(item.getItemName());

        if(existingItem.isPresent()){
            log.error("Item already exists");
            return new ModelAndView("404");
        }

        Boolean isCreated = warehouseService.createWarehouseItem(item);

        if(isCreated){

            Optional<WarehouseItem> createdItem = warehouseService.getItemByName(item.getItemName());
            ModelAndView itemView = new ModelAndView("/views/item");
            itemView.addObject("item", createdItem);

            log.info("Item created successfully");
            return itemView;
        } else {
            log.error("Item not created");
            return new ModelAndView("404");
        }
    }

    //DELETE
    @RequestMapping(value = "/user/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") long id )
    {
        Optional<User> user = userService.getUser(id);

        if(user.isEmpty())
        {
            log.error("User with given ID doesn't exist");
            return new ModelAndView("404");
        }

        Boolean isDeleted = userService.deleteUser(id);

        if(isDeleted){
            log.info("User deleted successfully");
            return getAllUsers();
        } else {
            log.error("User not deleted");
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/delivery/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteDelivery(@PathVariable("id") long id )
    {
        Optional<Delivery> delivery = warehouseService.getDelivery(id);

        if(delivery.isEmpty())
        {
            log.error("Delivery with given ID doesn't exist");
            return new ModelAndView("404");
        }

        Boolean isDeleted = warehouseService.deleteDelivery(id);

        if(isDeleted){
            log.info("Delivery deleted successfully");
            return getAllDeliveries();
        } else {
            log.error("Delivery not deleted");
            return new ModelAndView("404");
        }

    }

    @RequestMapping(value = "/item/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteItem(@PathVariable("id") long id )
    {
        Optional<WarehouseItem> item = warehouseService.getWarehouseItem(id);

        if(item.isEmpty())
        {
            log.error("Item with given ID doesn't exist");
            return new ModelAndView("404");
        }

        Boolean isDeleted = warehouseService.deleteWarehouseItem(id);

        if(isDeleted){
            log.info("Item deleted successfully");
            return getAllItems();
        } else {
            log.error("Item not deleted");
            return new ModelAndView("404");
        }
    }

    //UPDATE
    @RequestMapping(value = "/user/{id}/update", method = RequestMethod.GET)
    public ModelAndView getUserUpdateForm(@PathVariable("id") long id){
        Optional<User> user = userService.getUser(id);

        if(user.isEmpty())
        {
            log.error("User with given ID doesn't exist");
            return new ModelAndView("404");
        }

        ModelAndView updateForm = getUserForm();
        updateForm.addObject("user", user);

        return updateForm;
    }

    @RequestMapping(value = "/user/{id}/save", method = RequestMethod.PUT)
    public ModelAndView updateUser(@PathVariable("id") long id, @RequestBody User user)
    {
        Optional<User> searchedUser = userService.getUser(id);

        if(searchedUser.isEmpty())
        {
            log.error("User with given ID doesn't exist");
            return new ModelAndView("404");
        }

        User currentUser = searchedUser.get();

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword()); //TODO dodać BCrypt
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        Boolean isUpdated = userService.updateUser(currentUser);

        if(isUpdated){
            log.info("User updated successfully");
            return getUser(id);
        } else {
            log.error("User not updated");
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/delivery/{id}/update", method = RequestMethod.GET)
    public ModelAndView getDeliveryUpdateForm(@PathVariable("id") long id){
        Optional<Delivery> delivery = warehouseService.getDelivery(id);

        if(delivery.isEmpty())
        {
            log.error("Delivery with given ID doesn't exist");
            return new ModelAndView("404");
        }

        ModelAndView updateForm = getDeliveryForm();
        updateForm.addObject("delivery", delivery);
        updateForm.addObject("deliveryStatuses", DeliveryStatus.values());

        return updateForm;
    }

    @RequestMapping(value = "/delivery/{id}/save", method = RequestMethod.PUT)
    public ModelAndView updateDelivery(@PathVariable("id") long id, @RequestBody Delivery delivery)
    {
        Optional<Delivery> searchedDelivery = warehouseService.getDelivery(id);

        if(searchedDelivery.isEmpty())
        {
            log.error("Delivery with given ID doesn't exist");
            return new ModelAndView("404");
        }
        Delivery currentDelivery = searchedDelivery.get();

        currentDelivery.setEmployeeAccepting(delivery.getEmployeeAccepting());
        currentDelivery.setCustomerOrdering(delivery.getCustomerOrdering());
        currentDelivery.setDeliveryAddress(delivery.getDeliveryAddress());
        currentDelivery.setDeliveryStatus(delivery.getDeliveryStatus());
        currentDelivery.setPaid(delivery.getPaid());

        Boolean isUpdated = warehouseService.updateDelivery(currentDelivery);

        if(isUpdated){
            log.info("Delivery updated successfully");
            return getDelivery(id);
        } else {
            log.error("Delivery not updated");
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/item/{id}/update", method = RequestMethod.GET)
    public ModelAndView getItemUpdateForm(@PathVariable("id") long id){
        Optional<WarehouseItem> item = warehouseService.getWarehouseItem(id);

        if(item.isEmpty())
        {
            log.error("Item with given ID doesn't exist");
            return new ModelAndView("404");
        }

        ModelAndView updateForm = getItemForm();
        updateForm.addObject("item", item);
        updateForm.addObject("itemTypes", ItemType.values());

        return updateForm;
    }

    @RequestMapping(value = "/item/{id}/save", method = RequestMethod.PUT)
    public ModelAndView updateItem(@PathVariable("id") long id, @RequestBody WarehouseItem item)
    {
        Optional<WarehouseItem> searchedItem = warehouseService.getWarehouseItem(id);

        if(searchedItem.isEmpty())
        {
            log.error("Item with given ID doesn't exist");
            return new ModelAndView("404");
        }

        WarehouseItem currentItem = searchedItem.get();

        currentItem.setItemName(item.getItemName());
        currentItem.setItemType(item.getItemType());
        currentItem.setItemDescription(item.getItemDescription());
        currentItem.setPrice(item.getPrice());

        Boolean isUpdated = warehouseService.updateItemInfo(currentItem);

        if(isUpdated){
            log.info("Item updated successfully");
            return getWarehouseItem(id);
        } else {
            log.error("Item not updated");
            return new ModelAndView("404");
        }
    }
}
