package BOT.warehouseProject.Website.Controllers;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Domain.Entities.Delivery;
import BOT.warehouseProject.Domain.Entities.WarehouseItem;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
