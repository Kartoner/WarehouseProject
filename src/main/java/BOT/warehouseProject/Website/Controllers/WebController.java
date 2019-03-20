package BOT.warehouseProject.Website.Controllers;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController
{

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
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }




}
